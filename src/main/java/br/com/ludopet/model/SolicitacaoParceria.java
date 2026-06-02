package br.com.ludopet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacao_parceria")
public class SolicitacaoParceria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nomeEmpresa;

    @Column(nullable = false, length = 100)
    private String responsavel;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    // Clínica Veterinária | Pet Shop | Hospital Veterinário | ONG | Outro
    @Column(nullable = false, length = 80)
    private String tipoEmpresa;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 255)
    private String endereco;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    // PENDENTE | APROVADO | RECUSADO
    @Column(nullable = false, length = 20)
    private String status = "PENDENTE";

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    // ── Getters & Setters ──────────────────────────────────

    public Long getId() { return id; }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getTipoEmpresa() { return tipoEmpresa; }
    public void setTipoEmpresa(String tipoEmpresa) { this.tipoEmpresa = tipoEmpresa; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}