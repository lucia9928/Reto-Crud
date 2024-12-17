/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author 2dam
 */
public class GestionaId implements Serializable{
    
    private String dni;
    private Integer idProducto;
    private LocalDate fechaCompra;

    public GestionaId(String dni, Integer idProducto, LocalDate fechaCompra) {
        this.dni = dni;
        this.idProducto = idProducto;
        this.fechaCompra = fechaCompra;
    }

    public GestionaId() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    
    
    
}
