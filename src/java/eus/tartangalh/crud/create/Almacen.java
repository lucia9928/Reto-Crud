/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name="Almacen", schema="farmaciabd")
@NamedQuery(name="encontrarAlmacenes",
            query="SELECT c FROM Almacen c"
)
@XmlRootElement
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAlmacen;
    private String pais;
    private String ciudad;
    private Integer metrosCuadrados;
    private LocalDate fechaAdquisicion;
    @OneToMany(mappedBy="almacen")
    private  Set<ProductoFarmaceutico> producto;

    public Almacen(Integer idAlmacen, String pais, String ciudad, Integer metrosCuadrados, LocalDate fechaAdquisicion) {
        this.idAlmacen = idAlmacen;
        this.pais = pais;
        this.ciudad = ciudad;
        this.metrosCuadrados = metrosCuadrados;
        this.fechaAdquisicion = fechaAdquisicion;
    }
    
    public Almacen() {
    }

    @XmlTransient    
    public Set<ProductoFarmaceutico> getProducto() {
        return producto;
    }

    public void setProducto(Set<ProductoFarmaceutico> producto) {
        this.producto = producto;
    }
    
    
    
    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(Integer metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlmacen != null ? idAlmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.idAlmacen == null && other.idAlmacen != null) || (this.idAlmacen != null && !this.idAlmacen.equals(other.idAlmacen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eus.tartangalh.crud.create.Almacen[ id=" + idAlmacen + " ]";
    }

}
