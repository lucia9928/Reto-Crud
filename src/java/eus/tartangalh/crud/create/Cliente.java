/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author melany
 */
@Entity
@Table(name="Cliente", schema="farmaciabd")
@NamedQueries({
    @NamedQuery(
        name = "encontrarTodosLosClientes",
        query = "SELECT c FROM Cliente c ORDER BY c.dni DESC"
    )
})
@XmlRootElement
public class Cliente extends Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaRegistro;
  @OneToMany( mappedBy ="cliente")
  private List<RecetaMedica>recetas;

    public Cliente() {
        super();
    }

    @XmlTransient
    public List<RecetaMedica> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<RecetaMedica> recetas) {
        this.recetas = recetas;
    }

    public Cliente(Date fechaRegistro) {
         super();
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.fechaRegistro);
        hash = 83 * hash + Objects.hashCode(this.recetas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.fechaRegistro, other.fechaRegistro)) {
            return false;
        }
        if (!Objects.equals(this.recetas, other.recetas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "fechaRegistro=" + fechaRegistro + ", recetas=" + recetas + '}';
    }
    
}
