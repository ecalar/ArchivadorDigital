package com.archivador;


import com.archivador.ui.LoginFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Inicializar base de datos
        DataBaseManager.initDatabase();

        // Mostrar ventana de login
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}