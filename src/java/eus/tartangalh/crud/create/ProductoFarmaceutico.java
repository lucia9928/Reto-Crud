/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name="Producto_Farmaceutico", schema="farmaciabd")
@XmlRootElement
public class ProductoFarmaceutico implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
    *Identificador del producto
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    
    private Integer idProducto;
    /*
    *Nombre del producto
    */
    private String nombreProducto;
     /*
    *lote del producto
    */
    private String loteProducto;
    /*
    *Fecha de caducidad del producto
    */
    private LocalDate fechaCaducidad;
    /*
    *Descripcion del producto
    */
    private String Description;
    /*
    *Categoria del producto
    */
    @Enumerated(EnumType.ORDINAL)
    private CategoriaProducto categoria;
    /*
    *Precio del producto
    */
    private Float precio;

    public ProductoFarmaceutico() {
    }

    public ProductoFarmaceutico(Integer idProducto, String nombreProducto, String loteProducto, LocalDate fechaCaducidad, String Description, CategoriaProducto categoria, Float precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.loteProducto = loteProducto;
        this.fechaCaducidad = fechaCaducidad;
        this.Description = Description;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getLoteProducto() {
        return loteProducto;
    }

    public void setLoteProducto(String loteProducto) {
        this.loteProducto = loteProducto;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    
    public Integer getId() {
        return idProducto;
    }

    public void setId(Integer id) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoFarmaceutico)) {
            return false;
        }
        ProductoFarmaceutico other = (ProductoFarmaceutico) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eus.tartangalh.crud.create.ProductoFarmaceutico[ id=" + idProducto + " ]";
    }
    
}
