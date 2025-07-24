package com.archivador.services;

import com.archivador.dao.UsuarioDAO;
import com.archivador.models.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    public static Usuario login(String username, String password) {
        try {
            Usuario usuario = UsuarioDAO.buscarPorUsername(username);

            if (usuario == null) {
                System.out.println("Usuario no encontrado: " + username);
                return null;
            }

            System.out.println("Usuario encontrado: " + usuario.getUsername());
            System.out.println("Hash almacenado: " + usuario.getPassword());

            boolean passwordMatch = BCrypt.checkpw(password, usuario.getPassword());
            System.out.println("Resultado de verificación: " + passwordMatch);

            return passwordMatch ? usuario : null;

        } catch (Exception e) {
            System.err.println("Error en autenticación:");
            e.printStackTrace();
            return null;
        }
    }
}