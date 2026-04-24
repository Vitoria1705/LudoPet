package br.com.ludopet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "adocoes")//ABSTRAÇÃO(do mundo real em forma de classe, mostrando apenas o que é importante)
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idAnimal;

    private String nomeAdotante;

    private String emailAdotante;

    private String telefoneAdotante;

    private String motivo;

    public Long getId() {
        return id;
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomeAdotante() {
        return nomeAdotante;
    }

    public void setNomeAdotante(String nomeAdotante) {
        this.nomeAdotante = nomeAdotante;
    }

    public String getEmailAdotante() {
        return emailAdotante;
    }

    public void setEmailAdotante(String emailAdotante) {
        this.emailAdotante = emailAdotante;
    }

    public String getTelefoneAdotante() {
        return telefoneAdotante;
    }

    public void setTelefoneAdotante(String telefoneAdotante) {
        this.telefoneAdotante = telefoneAdotante;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

}