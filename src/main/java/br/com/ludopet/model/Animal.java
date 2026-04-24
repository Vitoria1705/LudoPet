package br.com.ludopet.model;
//animal.java e a orientação objeto E MVC
//MVC DIVIDIDO EM ETAPAS:MODEL (Dados): Animal.java
//VIEW (Tela): adocao.html
//CONTROLLER (Classe que recebe ações do usuário.): AnimalController.java
import jakarta.persistence.*;

@Entity
@Table(name = "animais")//ORM : Transformar classes Java em tabelas do banco.
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;//ENCAPSULAMENTO

    private String idade;

    private String raca;

    private String sexo;

    private String porte;

    private String descricao;

    private String foto; // ← usamos este

    private String status;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}