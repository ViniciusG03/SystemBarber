package org.menosprezo.systembarber.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.menosprezo.systembarber.dto.CadastroRequestDTO;
import org.menosprezo.systembarber.dto.ResetPasswordRequestDTO;
import org.menosprezo.systembarber.model.Usuario;
import org.menosprezo.systembarber.repository.UsuarioRepository;
import org.menosprezo.systembarber.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private MailgunEmailService mailgunEmailService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, MailgunEmailService mailgunEmailService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = new JwtUtil();
        this.mailgunEmailService = mailgunEmailService;
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

    public String solicitarRedefinicaoSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        String token = jwtUtil.generateToken(email);
        String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + token;

        String subject = "Redefinição de Senha";
        String message = "Clique no link para redefinir sua senha: " + resetLink;

        // Enviar o e-mail usando o MailgunEmailService
        mailgunEmailService.enviarEmail(usuario.getEmail(), subject, message);

        return token;
    }

    public void redefinirSenha(ResetPasswordRequestDTO resetPasswordRequestDTO) {
        String email = jwtUtil.extractUsername(resetPasswordRequestDTO.getToken());

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(resetPasswordRequestDTO.getNewPassword()));
        usuarioRepository.save(usuario);
    }
}
