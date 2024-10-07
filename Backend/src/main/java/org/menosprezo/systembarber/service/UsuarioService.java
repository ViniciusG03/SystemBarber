package org.menosprezo.systembarber.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.menosprezo.systembarber.model.Usuario;
import org.menosprezo.systembarber.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!BCrypt.verifyer().verify(senha.toCharArray(), usuario.getSenha()).verified) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        return usuario;
    }
}