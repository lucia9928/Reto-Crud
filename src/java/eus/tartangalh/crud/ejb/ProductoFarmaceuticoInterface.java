/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

/**
 *
 * @author 2dam
 */
import eus.tartangalh.crud.create.ProductoFarmaceutico;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProductoFarmaceuticoInterface {

    /**
     * Crea un nuevo ProductoFarmaceutico en la base de datos.
     * @param producto El objeto ProductoFarmaceutico que contiene los datos del nuevo producto.
     * @throws CrearException Lanzada cuando ocurre un error o excepción durante la creación.
     */
    public void crearProducto(ProductoFarmaceutico producto) throws CrearException;

    /**
     * Actualiza los datos de un ProductoFarmaceutico en la base de datos.
     * @param producto El objeto ProductoFarmaceutico que contiene los datos modificados del producto.
     * @throws ActualizarException Lanzada cuando ocurre un error o excepción durante la actualización.
     */
    public void actualizarProducto(ProductoFarmaceutico producto) throws ActualizarException;

    /**
     * Elimina un ProductoFarmaceutico de la base de datos.
     * @param producto El objeto ProductoFarmaceutico que se desea eliminar.
     * @throws BorrarException Lanzada cuando ocurre un error o excepción durante la eliminación.
     */
    public void borrarProducto(ProductoFarmaceutico producto) throws BorrarException;

    /**
     * Obtiene un ProductoFarmaceutico de la base de datos utilizando su ID.
     * @param id El ID del producto a recuperar.
     * @return Un objeto ProductoFarmaceutico con los datos del producto.
     * @throws LeerException Lanzada cuando ocurre un error o excepción durante la lectura.
     */
    public ProductoFarmaceutico encontrarProductoFarmaceutico(Integer id) throws LeerException;

    /**
     * Obtiene una lista con todos los ProductoFarmaceutico almacenados en la base de datos.
     * @return Una lista de objetos ProductoFarmaceutico.
     * @throws LeerException Lanzada cuando ocurre un error o excepción durante la lectura.
     */
    public List<ProductoFarmaceutico> encontrarTodosProductoFarmaceuticos() throws LeerException;

    /**
     * Obtiene una lista con todos los ProductoFarmaceutico cuya fecha de caducidad es anterior a una fecha específica.
     * @param fechaLimite La fecha límite para filtrar los productos.
     * @return Una lista de objetos ProductoFarmaceutico.
     * @throws LeerException Lanzada cuando ocurre un error o excepción durante la lectura.
     */
    public List<ProductoFarmaceutico> encontrarProductosFarmaceuticosFechaCaducidad(LocalDate fechaLimite) throws LeerException;

    /**
     * Obtiene una lista de ProductoFarmaceutico según su nombre.
     * @param nombre El nombre del producto a buscar.
     * @return Una lista de objetos ProductoFarmaceutico que coinciden con el nombre proporcionado.
     * @throws LeerException Lanzada cuando ocurre un error o excepción durante la lectura.
     */
    public List<ProductoFarmaceutico> encontrarProductoPorNombre(String nombre) throws LeerException;
}

