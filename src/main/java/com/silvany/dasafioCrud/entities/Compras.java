package com.silvany.dasafioCrud.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_compras") // create table tb_cliente
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // incremento
    private int id;
    private Double totalCompra;
    private LocalDate dataCompra;

    @ManyToOne // cada compra pode ser feito apenas por um cliente
    @JoinColumn(name = "cliente_id") // chave estrangeira
    // criar relacionamento de cliente com compras
    private Cliente cliente;

    public Compras(int id, Double totalCompra, LocalDate dataCompra, Cliente cliente) {//add
        this.id = id;
        this.totalCompra = totalCompra;
        this.dataCompra = dataCompra;
        this.cliente = cliente; //add
    }

    // CRIANDO CONSTRUTOR VAZIO
    public Compras() {

    }

    // PARA ATUALIZAR A UMA COMPRA
    public Compras(int id) {//add

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    //criado getters e setters do cliente
    public Cliente getCliente() { //add
        return cliente;
    }

    public void setCliente(Cliente cliente) { //add
        this.cliente = cliente;
    }
}