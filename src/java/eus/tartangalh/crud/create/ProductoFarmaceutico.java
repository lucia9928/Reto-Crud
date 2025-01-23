/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.create;

import java.io.Serializable;
import static java.sql.Date.valueOf;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad JPA que representa un producto farmacéutico. Define las propiedades,
 * relaciones y consultas asociadas a los productos farmacéuticos.
 *
 * @author Oscar
 */
@Entity
@Table(name = "Producto_Farmaceutico", schema = "farmaciabd")
@NamedQueries({
    @NamedQuery(
            name = "buscarTodosLosProductos",
            query = "SELECT a FROM ProductoFarmaceutico a"
    )
    ,
    @NamedQuery(
            name = "buscarProductosPorCategoria",
            query = "SELECT a FROM ProductoFarmaceutico a WHERE a.categoria = :categoria"
    )
    ,
    @NamedQuery(
            name = "buscarProductoPorNombre",
            query = "SELECT a FROM ProductoFarmaceutico a WHERE a.nombreProducto = :nombre"
    )
    ,
    @NamedQuery(
            name = "buscarProductosPorRangoDeFechas",
            query = "SELECT a FROM ProductoFarmaceutico a WHERE a.fechaCaducidad BETWEEN :fechaInicio AND :fechaFin"
    )
    ,
    @NamedQuery(
            name = "buscarProductosPorFechaCaducidad",
            query = "SELECT a FROM ProductoFarmaceutico a WHERE a.fechaCaducidad = :fechaLimite"
    )
})
@XmlRootElement
public class ProductoFarmaceutico implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Identificador único del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProducto;

    /*
     * Nombre del producto farmacéutico.
     */
    private String nombreProducto;

    /*
     * Lote al que pertenece el producto.
     */
    private String loteProducto;

    /*
     * Fecha de caducidad del producto.
     */
    private Date fechaCaducidad = valueOf(LocalDate.now());

    /*
     * Descripción del producto.
     */
    private String Description;

    /*
     * Categoría del producto farmacéutico (enum).
     */
    @Enumerated(EnumType.ORDINAL)
    private CategoriaProducto categoria;

    /*
     * Precio del producto.
     */
    private Float precio;

    /*
     * Relación con el almacén al que pertenece el producto.
     */
    @ManyToOne
    private Almacen almacen;

    /*
     * Relación con las entidades de gestión del producto.
     */
    @OneToMany(mappedBy = "productoFarmaceutico", fetch = FetchType.EAGER)
    private List<Gestiona> gestiona;

    /*
     * Relación con las recetas médicas que incluyen el producto.
     */
    @ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    private List<RecetaMedica> receta;

    /*
     * Relación con el proveedor del producto.
     */
    @ManyToOne
    private Proveedor proveedor;

    /*
     * Constructor vacío.
     */
    public ProductoFarmaceutico() {
    }

    /*
     * Constructor con parámetros para inicializar las propiedades del producto.
     */
    public ProductoFarmaceutico(Integer idProducto, String nombreProducto, String loteProducto, Date fechaCaducidad, String Description, CategoriaProducto categoria, Float precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.loteProducto = loteProducto;
        this.fechaCaducidad = fechaCaducidad;
        this.Description = Description;
        this.categoria = categoria;
        this.precio = precio;
    }

    /*
     * Métodos getter y setter para las propiedades.
     */
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

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getLoteProducto() {
        return loteProducto;
    }

    public void setLoteProducto(String loteProducto) {
        this.loteProducto = loteProducto;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
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

    /*
     * Métodos de utilidad para comparar objetos y convertirlos a String.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProductoFarmaceutico)) {
            return false;
        }
        ProductoFarmaceutico other = (ProductoFarmaceutico) object;
        return (this.idProducto != null || other.idProducto == null) && (this.idProducto == null || this.idProducto.equals(other.idProducto));
    }

    @Override
    public String toString() {
        return "eus.tartangalh.crud.create.ProductoFarmaceutico[ id=" + idProducto + " ]";
    }
}
