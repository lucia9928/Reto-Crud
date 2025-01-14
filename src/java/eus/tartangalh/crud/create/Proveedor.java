/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
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
@Table(name = "Proveedor", schema = "farmaciabd")
@NamedQueries({
    @NamedQuery(
            name = "buscarTodosLosProveedores",
            query = "SELECT pr FROM Proveedor pr ORDER BY pr.idProveedor DESC"
    ),
})
@XmlRootElement
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProveedor;
    private String cif;
    private String nombreProveedor;
    private String calle;
    private Integer codPostal;
    private String ciudad;
    private Date fechaContratacion;

    @OneToMany(mappedBy = "proveedor")
    private List<ProductoFarmaceutico> productoFarmaceutico;

    public Proveedor(Integer idProveedor, String cif, String nombreProveedor, String calle, Integer codPostal, String ciudad, Date fechaContratacion, List<ProductoFarmaceutico> productoFarmaceutico) {
        this.idProveedor = idProveedor;
        this.cif = cif;
        this.nombreProveedor = nombreProveedor;
        this.calle = calle;
        this.codPostal = codPostal;
        this.ciudad = ciudad;
        this.fechaContratacion = fechaContratacion;
        this.productoFarmaceutico = productoFarmaceutico;
    }

    public Proveedor() {
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(Integer codPostal) {
        this.codPostal = codPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @XmlTransient
    public List<ProductoFarmaceutico> getProductoFarmaceutico() {
        return productoFarmaceutico;
    }

    public void setProductoFarmaceutico(List<ProductoFarmaceutico> productoFarmaceutico) {
        this.productoFarmaceutico = productoFarmaceutico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eus.tartangalh.crud.create.Proveedor[ id=" + idProveedor + " ]";
    }

}
