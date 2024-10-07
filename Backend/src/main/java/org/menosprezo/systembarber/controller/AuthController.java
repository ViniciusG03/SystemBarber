package org.menosprezo.systembarber.controller;

import org.menosprezo.systembarber.dto.LoginRequestDTO;
import org.menosprezo.systembarber.dto.LoginResponseDTO;
import org.menosprezo.systembarber.model.Usuario;
import org.menosprezo.systembarber.security.JwtUtil;
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
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "http://localhost:5500")
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
}
