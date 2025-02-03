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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author melany
 */
@Entity
@Table(name = "Receta_Medica", schema = "farmaciabd")
@NamedQueries({
    @NamedQuery(
            name = "encontrarTodasLasRecetas",
            query = "SELECT a FROM RecetaMedica a ORDER BY a.idReceta DESC"
    ),
     @NamedQuery(
            name = "buscarRecetasMedicasPorDeFechas",
            query = "SELECT r FROM RecetaMedica r WHERE r.fechaReceta BETWEEN :fechaInicio AND :fechaFin"
    )
})
@XmlRootElement
public class RecetaMedica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idReceta;
    @ManyToOne
   private Cliente cliente;
    private Date fechaReceta;
    private String descripcion;
    private Integer cantidad;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Producto_Receta", schema = "farmaciabd")
    private List<ProductoFarmaceutico> productos;

    public RecetaMedica() {

    }

    public RecetaMedica(Integer idReceta, Date fechaReceta, String descripcion, Integer cantidad) {
        this.idReceta = idReceta;
        this.fechaReceta = fechaReceta;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    private static final long serialVersionUID = 1L;
    @XmlTransient
    public List<ProductoFarmaceutico> getProductos() {
        return productos;
    }

    public void setListaProductos(List<ProductoFarmaceutico> productos) {
        this.productos = productos;
    }
    public Date getFechaReceta() {
        return fechaReceta;
    }

    public void setFechaReceta(Date fechaReceta) {
        this.fechaReceta = fechaReceta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setId(Integer idReceta) {
        this.idReceta = idReceta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReceta != null ? idReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaMedica)) {
            return false;
        }
        RecetaMedica other = (RecetaMedica) object;
        if ((this.idReceta == null && other.idReceta != null) || (this.idReceta != null && !this.idReceta.equals(other.idReceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eus.tartangalh.crud.create.RecetaMedica[ id=" + idReceta + " ]";
    }

}