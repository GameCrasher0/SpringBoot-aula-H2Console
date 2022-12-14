package com.example.Aula.entity;

import javax.persistence.*;
import java.util.List;

@Entity

public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String endereco;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;


    public Cliente(String nome, String endereco, List<Produto> prodtos) {
        this.nome = nome;
        this.endereco = endereco;
        this.produtos = prodtos;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
