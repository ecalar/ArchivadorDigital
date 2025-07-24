package com.archivador.services;

import com.archivador.dao.UsuarioDAO;
import com.archivador.models.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    public static Usuario login(String username, String password){
        Usuario usuario = UsuarioDAO.buscarPorUsername(username);
        if (usuario  != null && BCrypt.checkpw(password, usuario.getPassword())){
            return usuario;
        }
        return null;
    }
}
