/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author melany
 */
@Entity
@Table(name="Trabajador", schema="farmaciabd")
public class Trabajador extends Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  private LocalDate fechaContratacion;
  @Enumerated(EnumType.STRING)
  private TipoCargo tipoCargo;
  //@OneToMany(cascade=All, mappedBy ="trabajador")
//private Set<Gestiona>gestionaProducto;
    public Trabajador(LocalDate fechaContratacion, TipoCargo tipoCargo) {
        this.fechaContratacion = fechaContratacion;
        this.tipoCargo = tipoCargo;
    }
  
    public Trabajador() {
        super();
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public TipoCargo getTipoCargo() {
        return tipoCargo;
    }

    public void setTipoCargo(TipoCargo tipoCargo) {
        this.tipoCargo = tipoCargo;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (super.getDni() != null ? super.getDni().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
      super.equals(object);
        return true;
    }

    @Override
    public String toString() {
        return "eus.tartangalh.crud.create.Trabajador[ id=" + super.getDni() + " ]";
    }
    
}
