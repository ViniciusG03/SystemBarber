package org.menosprezo.systembarber.dto;

import lombok.Setter;

public class LoginResponseDTO {
    @Setter
    String token;
    @Setter
    String mensagem;

    public LoginResponseDTO(String token, String mensagem) {
        this.token = token;
        this.mensagem = mensagem;
    }

    public String getToken() {
        return token;
    }

    public String getMensagem() {
        return mensagem;
    }
}
