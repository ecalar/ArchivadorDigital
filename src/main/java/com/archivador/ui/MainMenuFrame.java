package com.archivador.ui;

import com.archivador.models.Usuario;
import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame(Usuario usuario) {
        setTitle("Menú Principal - Hermandad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Barra superior (Bienvenida)
        JLabel lblBienvenida = new JLabel("Bienvenido: " + usuario.getUsername() + " | Rol: " + usuario.getRol());
        add(lblBienvenida, BorderLayout.NORTH);

        // Panel central (ejemplo)
        JPanel panel = new JPanel();
        panel.add(new JLabel("Contenido principal aquí"));
        add(panel, BorderLayout.CENTER);

        // Menú lateral (implementar según tu diseño)
    }
}