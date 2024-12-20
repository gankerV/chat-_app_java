package chat_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chat_system.bus.MessageFriendBUS;
import chat_system.bus.MessageGroupBUS;
import chat_system.dao.GroupChatDAO;
import chat_system.dao.UserAccountDAO;
import chat_system.dto.MessageFriendDTO;
import chat_system.dto.MessageGroupDTO;
import chat_system.dto.User;
import chat_system.dto.UserAccount;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());
    private static MessageFriendBUS messageFriendBUS = new MessageFriendBUS();
    private static MessageGroupBUS messageGroupBUS = new MessageGroupBUS();  

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
        if (messageFriendBUS.saveMessage(message)) {
            System.out.println("[Database] Saved message from user " + senderId + " to user " + receiverId + ": " + content);
        } else {
            System.err.println("[Database] Failed to save message.");
        }
    }

    public static void sendMessageToGroup(String message, int senderId, int groupId) throws SQLException {
        // Lấy danh sách thành viên của nhóm
        List<User> users = new GroupChatDAO().getUsersByGroupId(groupId);
        
        // Lấy danh sách ID thành viên từ danh sách users
        Set<Integer> userIds = new HashSet<>();
        for (User user : users) {
            userIds.add(user.getId());
        }

        // Lấy tên người gửi từ cơ sở dữ liệu
        UserAccount sender = new UserAccountDAO().getUserByID(String.valueOf(senderId));
        
        // Format tin nhắn
        String formattedMessage = sender.getUsername() + ": " + message;
        
        synchronized (clientHandlers) {
            for (ClientHandler client : clientHandlers) {
                // Chỉ gửi tin nhắn nếu client là chat nhóm, có ID trùng với thành viên trong nhóm và không phải người gửi
                if (client.isChatGroup() && userIds.contains(client.getClientId()) && client.getClientId() != senderId) {
                    client.sendMessage(formattedMessage); // Gửi tin nhắn đã định dạng username : MessageCongtent
                }
            }
        }
        
        // Lưu tin nhắn (không định dạng ) vào cơ sở dữ liệu
        saveGroupMessageToDatabase(senderId, groupId, message);
    }

    
    private static void saveGroupMessageToDatabase(int senderId, int groupId, String content) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    
        // Lưu tin nhắn nhóm
        MessageGroupDTO message = new MessageGroupDTO(senderId, groupId, currentTimestamp, content);
        if (messageGroupBUS.saveGroupMessage(message)) {
            System.out.println("[Database] Saved group message from user " + senderId + " to group " + groupId + ": " + content);
        } else {
            System.err.println("[Database] Failed to save group message.");
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
    private boolean isChatGroup = false;

    public boolean isChatGroup() {
        return isChatGroup;
    }

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
                if (message.startsWith("ID:")) {
                    clientId = Integer.parseInt(message.split(":")[1]);  // Lấy clientId
                    System.out.println("Client with ID " + clientId + " has connected.");
                } else if (message.equalsIgnoreCase("chatGroup:true")) {
                    isChatGroup = true;  // Đánh dấu kết nối là nhóm
                    System.out.println("Client " + clientId + " marked as chat group.");
                } else {
                    // Xử lý tin nhắn
                    handleMessage(message);
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
    
    private void handleMessage(String message) {
        try {
            // Parse tin nhắn
            String[] parts = message.split(":", 3);
            int senderId = Integer.parseInt(parts[0]);
            int receiverOrGroupId = Integer.parseInt(parts[1]);
            String msgContent = parts[2];
    
            if (isChatGroup) {
                // Nếu là tin nhắn nhóm
                ChatServer.sendMessageToGroup(msgContent, senderId, receiverOrGroupId);
            } else {
                // Nếu là tin nhắn cá nhân
                ChatServer.sendMessageToReceiver(msgContent, senderId, receiverOrGroupId);
            }
        } catch (Exception e) {
            System.err.println("Error parsing or handling message: " + e.getMessage());
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
