package org.menosprezo.systembarber.model;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "barbearia")
public class Barbearia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O telefone deve estar no formato (XX) XXXXX-XXXX")
    private String telefone;

    @Size(max = 255, message = "Formas de pagamento devem ter no máximo 255 caracteres")
    private String formasPagamento;

    @NotBlank(message = "Quantidade de funcionários é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(name = "quantidade_funcionarios")
    private QuantidadeFuncionarios quantidadeFuncionarios;

    @NotBlank(message = "Proprietário é obrigatório")
    @Size(max = 100, message = "O nome do proprietário deve ter no máximo 100 caracteres")
    private String proprietario;

    @NotBlank(message = "Localização é obrigatória")
    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
    private String localizacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "dias_servico", nullable = false)

    private DiasServico diasServico;

    @Size(max = 20, message = "O horário de funcionamento deve ter no máximo 20 caracteres")
    private String horarioFuncionamento;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(String formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public QuantidadeFuncionarios getQuantidadeFuncionarios() {
        return quantidadeFuncionarios;
    }

    public void setQuantidadeFuncionarios(QuantidadeFuncionarios quantidadeFuncionarios) {
        this.quantidadeFuncionarios = quantidadeFuncionarios;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public DiasServico getDiasServico() {
        return diasServico;
    }

    public void setDiasServico(DiasServico diasServico) {
        this.diasServico = diasServico;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    // Enums para Quantidade de Funcionários e Dias de Serviço
    public enum QuantidadeFuncionarios {
        UM_A_DOIS_FUNCIONARIOS("1 a 2 Funcionarios"),
        UM_A_QUATRO_FUNCIONARIOS("1 a 4 Funcionarios"),
        UM_A_SEIS_OU_MAIS("1 a 6 ou +");

        private final String descricao;

        QuantidadeFuncionarios(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

        public static QuantidadeFuncionarios fromDescricao(String descricao) {
            for (QuantidadeFuncionarios quantidade : QuantidadeFuncionarios.values()) {
                if (quantidade.descricao.equalsIgnoreCase(descricao)) {
                    return quantidade;
                }
            }
            throw new IllegalArgumentException("No enum constant for description: " + descricao);
        }
    }

    public enum DiasServico {
        SEGUNDA_A_SEXTA,
        TODOS_OS_DIAS
    }

}
