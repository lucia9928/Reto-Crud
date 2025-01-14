/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;
import eus.tartangalh.crud.create.ProductoFarmaceutico;
import eus.tartangalh.crud.ejb.ProductoFarmaceuticoInterface;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase REST para gestionar las operaciones relacionadas con productos farmacéuticos.
 * Proporciona métodos para crear, actualizar, eliminar y buscar productos.
 * 
 * @author oscar
 */
@Stateless
@Path("Producto_Farmaceutico")
public class ProductoFarmaceuticoFacadeREST {

    /**
     * Objeto EJB para la lógica de negocio de los productos farmacéuticos.
     */
    @EJB
    private ProductoFarmaceuticoInterface ejb;

    /**
     * Logger para registrar mensajes y errores de la clase.
     */
    private Logger LOGGER = Logger.getLogger(ProductoFarmaceuticoFacadeREST.class.getName());

    /**
     * Método para crear un producto farmacéutico.
     * @param productoFarmaceutico Objeto con los datos del producto a crear.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearProducto(ProductoFarmaceutico productoFarmaceutico) {
        try {
            LOGGER.log(Level.INFO, "Creando producto farmacéutico {0}", productoFarmaceutico.getIdProducto());
            ejb.crearProducto(productoFarmaceutico);
        } catch (CrearException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método para actualizar un producto farmacéutico existente.
     * @param producto Objeto con los datos actualizados del producto.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizarProducto(ProductoFarmaceutico producto) {
        try {
            LOGGER.log(Level.INFO, "Actualizando producto {0}", producto.getIdProducto());
            ejb.actualizarProducto(producto);
        } catch (ActualizarException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método para eliminar un producto farmacéutico por su ID.
     * @param id Identificador del producto a eliminar.
     */
    @DELETE
    @Path("{id}")
    public void borrarProducto(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Borrando producto {0}", id);
            ejb.borrarProducto(ejb.encontrarProductoFarmaceutico(id));
        } catch (LeerException | BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método para encontrar un producto farmacéutico por su ID.
     * @param id Identificador del producto a buscar.
     * @return Producto farmacéutico encontrado.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ProductoFarmaceutico encontrarProducto(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Buscando producto {0}", id);
            return ejb.encontrarProductoFarmaceutico(id);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método para obtener todos los productos farmacéuticos.
     * @return Lista de todos los productos farmacéuticos.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductoFarmaceutico> encontrarTodos() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los productos");
            return ejb.encontrarTodosProductoFarmaceuticos();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método para buscar productos farmacéuticos con fecha de caducidad anterior a una fecha dada.
     * @param fechaLimite Fecha límite en formato String.
     * @return Lista de productos farmacéuticos que cumplen el criterio.
     */
    @GET
    @Path("caducidad/{fechaLimite}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductoFarmaceutico> encontrarPorFechaCaducidad(@PathParam("fechaLimite") String fechaLimite) {
        try {
            LocalDate fecha = LocalDate.parse(fechaLimite);
            LOGGER.log(Level.INFO, "Buscando productos con fecha de caducidad anterior a {0}", fecha);
            return ejb.encontrarProductosFarmaceuticosFechaCaducidad(fecha);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método para buscar productos farmacéuticos por su nombre.
     * @param nombre Nombre del producto.
     * @return Lista de productos farmacéuticos que coinciden con el nombre.
     */
    @GET
    @Path("nombre/{nombre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductoFarmaceutico> encontrarPorNombre(@PathParam("nombre") String nombre) {
        try {
            LOGGER.log(Level.INFO, "Buscando productos con nombre {0}", nombre);
            return ejb.encontrarProductoPorNombre(nombre);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
