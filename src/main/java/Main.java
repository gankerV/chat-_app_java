import admin_system.AdminMain;
import chat_system.UserMain;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select UI to launch:");
        System.out.println("1. Admin UI");
        System.out.println("2. User UI");
        System.out.println("3. Swing UI");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> AdminMain.main(new String[]{});
            case 2 -> UserMain.main(new String[]{});
            default -> System.out.println("Invalid choice. Exiting...");
        }
    }
}
