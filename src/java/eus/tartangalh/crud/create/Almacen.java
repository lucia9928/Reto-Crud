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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que representa un almacén en la base de datos de la farmacia.
 * Contiene información sobre la ubicación, tamaño y fecha de adquisición.
 * 
 * @author Andoni
 */
@Entity
@Table(name = "Almacen", schema = "farmaciabd")
@NamedQueries({
    @NamedQuery(
            name = "encontrarTodosAlmacenes",
            query = "SELECT c FROM Almacen c"
    ),
    @NamedQuery(
            name = "encontrarAlmacenPorId",
            query = "SELECT c FROM Almacen c WHERE c.idAlmacen = :id"
    ),
    @NamedQuery(
            name = "buscarAlmacenPorPais",
            query = "SELECT c FROM Almacen c WHERE c.pais = :pais"
    ),
    @NamedQuery(
            name = "buscarAlmacenPorCiudad",
            query = "SELECT c FROM Almacen c WHERE c.ciudad = :ciudad"
    ),
    @NamedQuery(
            name = "buscarAlmacenPorRangoDeFechas",
            query = "SELECT c FROM Almacen c WHERE c.fechaAdquisicion BETWEEN :fechaInicio AND :fechaFin"
    ),
    @NamedQuery(
            name = "buscarAlmacenPorFecha",
            query = "SELECT c FROM Almacen c WHERE c.fechaAdquisicion = :fechaLimite"
    )
})
@XmlRootElement
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAlmacen;
    private String pais;
    private String ciudad;
    private Integer metrosCuadrados;
    private Date fechaAdquisicion = valueOf(LocalDate.now());
    @OneToMany(mappedBy = "almacen", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    private List<ProductoFarmaceutico> producto;

    /**
     * Constructor con parámetros.
     * 
     * @param idAlmacen Identificador del almacén.
     * @param pais País del almacén.
     * @param ciudad Ciudad del almacén.
     * @param metrosCuadrados Metros cuadrados del almacén.
     * @param fechaAdquisicion Fecha de adquisición del almacén.
     */
    public Almacen(Integer idAlmacen, String pais, String ciudad, Integer metrosCuadrados, Date fechaAdquisicion) {
        this.idAlmacen = idAlmacen;
        this.pais = pais;
        this.ciudad = ciudad;
        this.metrosCuadrados = metrosCuadrados;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    /**
     * Constructor vacío.
     */
    public Almacen() {
    }

    /**
     * Obtiene la lista de productos farmacéuticos asociados al almacén.
     * 
     * @return Lista de productos farmacéuticos.
     */
    @XmlTransient
    public List<ProductoFarmaceutico> getProducto() {
        return producto;
    }

    /**
     * Establece la lista de productos farmacéuticos asociados al almacén.
     * 
     * @param producto Lista de productos farmacéuticos.
     */
    public void setProducto(List<ProductoFarmaceutico> producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el identificador del almacén.
     * 
     * @return Identificador del almacén.
     */
    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    /**
     * Establece el identificador del almacén.
     * 
     * @param idAlmacen Identificador del almacén.
     */
    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    /**
     * Obtiene el país del almacén.
     * 
     * @return País del almacén.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país del almacén.
     * 
     * @param pais País del almacén.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Obtiene la ciudad del almacén.
     * 
     * @return Ciudad del almacén.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad del almacén.
     * 
     * @param ciudad Ciudad del almacén.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene los metros cuadrados del almacén.
     * 
     * @return Metros cuadrados del almacén.
     */
    public Integer getMetrosCuadrados() {
        return metrosCuadrados;
    }

    /**
     * Establece los metros cuadrados del almacén.
     * 
     * @param metrosCuadrados Metros cuadrados del almacén.
     */
    public void setMetrosCuadrados(Integer metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    /**
     * Obtiene la fecha de adquisición del almacén.
     * 
     * @return Fecha de adquisición.
     */
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    /**
     * Establece la fecha de adquisición del almacén.
     * 
     * @param fechaAdquisicion Fecha de adquisición.
     */
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
}
