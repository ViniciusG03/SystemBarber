package org.menosprezo.systembarber.controller;

import org.menosprezo.systembarber.dto.*;
import org.menosprezo.systembarber.model.Usuario;
import org.menosprezo.systembarber.security.JwtUtil;
import org.menosprezo.systembarber.service.MailgunEmailService;
import org.menosprezo.systembarber.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MailgunEmailService mailgunEmailService;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "https://system-barber.vercel.app/")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            Usuario usuario = usuarioService.autenticarUsuario(loginRequest.getEmail(), loginRequest.getSenha());
            String token = jwtUtil.generateToken(usuario.getEmail());
            return ResponseEntity.ok(new LoginResponseDTO(token, "Login bem-sucedido"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(new LoginResponseDTO(null, "Usuário não encontrado"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new LoginResponseDTO(null, "Credenciais inválidas"));
        }
    }

    @CrossOrigin(origins = "https://system-barber.vercel.app/")
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody CadastroRequestDTO cadastroRequestDTO) {
        try {
            if (cadastroRequestDTO.getPassword() == null) {
                return ResponseEntity.status(400).body(new MessageResponseDTO("A senha não pode ser nula"));
            }
            usuarioService.cadastrarUsuario(cadastroRequestDTO);
            return ResponseEntity.ok().body(new MessageResponseDTO("Usuário cadastrado com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new MessageResponseDTO("Erro ao cadastrar usuário: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "https://system-barber.vercel.app/")
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        try {
            String email = forgotPasswordRequestDTO.getEmail();
            String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + "token-gerado";

            String subject = "Redefinição de Senha";
            String text = "Olá,\n\nClique no link abaixo para redefinir sua senha:\n" + resetLink;

            mailgunEmailService.enviarEmail(email, subject, text);

            return ResponseEntity.ok().body(new MessageResponseDTO("Link de recuperação de senha enviado para o email"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new MessageResponseDTO("Erro ao enviar email: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "https://system-barber.vercel.app/")
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        try {
            usuarioService.redefinirSenha(resetPasswordRequestDTO);
            return ResponseEntity.ok().body(new MessageResponseDTO("Senha redefinida com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao redefinir senha: " + e.getMessage());
        }
    }

}
