package org.menosprezo.systembarber.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.menosprezo.systembarber.dto.CadastroRequestDTO;
import org.menosprezo.systembarber.model.Usuario;
import org.menosprezo.systembarber.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!BCrypt.verifyer().verify(senha.toCharArray(), usuario.getSenha()).verified) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        return usuario;
    }

    public void cadastrarUsuario(CadastroRequestDTO cadastroRequestDTO) {
        if (cadastroRequestDTO.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(cadastroRequestDTO.getName());
        usuario.setEmail(cadastroRequestDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(cadastroRequestDTO.getPassword()));

        usuarioRepository.save(usuario);
    }
}