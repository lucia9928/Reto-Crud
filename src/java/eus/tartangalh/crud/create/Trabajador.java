/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author melany
 */
@Entity
@Table(name = "Trabajador", schema = "farmaciabd")
@NamedQueries({
    @NamedQuery(
            name = "encontrarTodosLosTrabajdores",
            query = "SELECT t FROM Trabajador t ORDER BY t.dni DESC"
    )
    ,
    @NamedQuery(
            name = "iniciarSesionTra", query = "SELECT u FROM Trabajador u WHERE u.dni = :Tradni AND u.contrasena = :contrasenaTra"
    )
    ,
    @NamedQuery(
            name = "buscarTrabajador",
            query = "SELECT u from Trabajador u WHERE u.email = :userEmail "
    )
        
})
@XmlRootElement
public class Trabajador extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaContratacion;
    @Enumerated(EnumType.STRING)
    private TipoCargo tipoCargo;
    @OneToMany(mappedBy = "trabajador", fetch = FetchType.EAGER)
    private List<Gestiona> gestiona;

    public Trabajador(Date fechaContratacion, TipoCargo tipoCargo) {
        this.fechaContratacion = fechaContratacion;
        this.tipoCargo = tipoCargo;
    }

    public Trabajador() {
        super();
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
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
