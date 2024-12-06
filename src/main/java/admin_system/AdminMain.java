package admin_system;

import javax.swing.SwingUtilities;


public class AdminMain 
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(adminui::new);
    }
}
