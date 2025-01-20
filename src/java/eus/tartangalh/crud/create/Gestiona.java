/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author markel
 */
@Entity
@Table(name = "Gestiona", schema = "farmaciabd")
@NamedQueries({
    @NamedQuery(
            name = "buscarTodasLasGestiones",
            query = "SELECT G FROM Gestiona G"
    ),
 @NamedQuery(
            name = "buscarGestion",
            query = "SELECT g FROM Gestiona g WHERE g.trabajador.dni = :dni AND g.productoFarmaceutico.idProducto = :idProducto"
    )

})

@XmlRootElement
public class Gestiona implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private GestionaId gestionaId;

    @ManyToOne
    @MapsId("dni")
    private Trabajador trabajador;

    @ManyToOne
    @MapsId("idProducto")
    private ProductoFarmaceutico productoFarmaceutico;
    private Date fechaCompra;

    private Integer cantidad;

    public Gestiona(GestionaId gestionaId, Trabajador trabajador, ProductoFarmaceutico productoFarmaceutico, Integer cantidad, Date fechaCompra) {
        this.gestionaId = gestionaId;
        this.trabajador = trabajador;
        this.productoFarmaceutico = productoFarmaceutico;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
    }

    public Gestiona() {
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public GestionaId getGestionaId() {
        return gestionaId;
    }

    public void setGestionaId(GestionaId gestionaId) {
        this.gestionaId = gestionaId;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public ProductoFarmaceutico getProductoFarmaceutico() {
        return productoFarmaceutico;
    }

    public void setProductoFarmaceutico(ProductoFarmaceutico productoFarmaceutico) {
        this.productoFarmaceutico = productoFarmaceutico;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gestionaId != null ? gestionaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gestiona)) {
            return false;
        }
        Gestiona other = (Gestiona) object;
        if ((this.gestionaId == null && other.gestionaId != null) || (this.gestionaId != null && !this.gestionaId.equals(other.gestionaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gestiona[ id=" + gestionaId + " ]";
    }

}
