package chat_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import chat_system.bus.MessageFriendBUS;
import chat_system.dto.MessageFriendDTO;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());
    private static MessageFriendBUS messageBUS = new MessageFriendBUS();  // Khởi tạo MessageFriendBUS để lưu tin nhắn

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    // Send message to a specific receiver by receiverId
    public static void sendMessageToReceiver(String message, int senderId, int receiverId) {
        synchronized (clientHandlers) {
            for (ClientHandler client : clientHandlers) {
                if (client.getClientId() == receiverId) {
                    client.sendMessage(message);
                    break; // Only send to the first matching receiverId
                }
            }
        }
        saveMessageToDatabase(senderId, receiverId, message);
    }

    private static void saveMessageToDatabase(int senderId, int receiverId, String content) {
        // Lấy thời gian hiện tại
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        
        // visibleOnly = 0 (chưa được đánh dấu là chỉ hiển thị cho người gửi)
        int visibleOnly = 0;
        
        // Tạo đối tượng MessageFriendDTO với thông tin cần thiết
        MessageFriendDTO message = new MessageFriendDTO(senderId, receiverId, currentTimestamp, content, visibleOnly);
        
        // Gọi MessageFriendBUS để lưu tin nhắn
        if (messageBUS.saveMessage(message)) {
            System.out.println("[Database] Saved message from user " + senderId + " to user " + receiverId + ": " + content);
        } else {
            System.err.println("[Database] Failed to save message.");
        }
    }

    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
        System.out.println("Client disconnected: " + clientHandler.getClientName());
    }
}


class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;
    private int clientId;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                // Kiểm tra nếu message là ID client
                if (message.startsWith("ID:")) {
                    clientId = Integer.parseInt(message.split(":")[1]);  // Lấy clientId từ message
                    System.out.println("Client with ID " + clientId + " has connected.");
                } else {
                    // Parse the message format: "senderId:receiverId:message"
                    String[] parts = message.split(":", 3);
                    int senderId = Integer.parseInt(parts[0]);
                    int receiverId = Integer.parseInt(parts[1]);
                    String msgContent = parts[2];

                    // Gửi tin nhắn đến client nhận
                    ChatServer.sendMessageToReceiver(msgContent, senderId, receiverId);
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
            ChatServer.removeClient(this);
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }
}
