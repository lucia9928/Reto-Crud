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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
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
 * Clase REST para gestionar las operaciones relacionadas con productos
 * farmacéuticos. Proporciona métodos para crear, actualizar, eliminar y buscar
 * productos.
 *
 * @author Oscar
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
     *
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
     *
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
     *
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
     *
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
     *
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
     * Método para buscar productos farmacéuticos con fecha de caducidad
     * anterior a una fecha dada.
     *
     * @param fechaLimite Fecha límite en formato String.
     * @return Lista de productos farmacéuticos que cumplen el criterio.
     */
    @GET
    @Path("caducidad/{fechaLimite}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductoFarmaceutico> encontrarPorFechaCaducidad(@PathParam("fechaLimite") String fechaLimite) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localdate = LocalDate.parse(fechaLimite, formatter);
            Date fecha = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            LOGGER.log(Level.INFO, "Buscando productos con fecha de caducidad anterior a {0}", fecha);
            return ejb.encontrarProductosFarmaceuticosFechaCaducidad(fecha);
        } catch (LeerException ex) {
            LOGGER.severe("Error al leer los productos: " + ex.getMessage());
            throw new InternalServerErrorException("Error al procesar la solicitud.");
        }
    }

    /**
     * Método para buscar productos farmacéuticos por su nombre.
     *
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
     /**
     * Método para buscar productos farmacéuticos comprendidas entre un rango de
     * fechas de caducidades
     * @param fechaInicio Fecha inicio en formato String.
     * @param fechaFin Fecha fin en formato String.
     * @return Lista de productos farmacéuticos que cumplen el criterio.
     */
    @GET
    @Path("{fechaInicio}/{fechaFin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductoFarmaceutico> encontrarPorRangoDeFechas(
            @PathParam("fechaInicio") String fechaInicio,
            @PathParam("fechaFin") String fechaFin) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convertir las fechas recibidas a objetos LocalDate
            LocalDate localDateInicio = LocalDate.parse(fechaInicio, formatter);
            LocalDate localDateFin = LocalDate.parse(fechaFin, formatter);

            // Convertir LocalDate a Date
            Date fechaInicioDate = Date.from(localDateInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date fechaFinDate = Date.from(localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());

            LOGGER.log(Level.INFO, "Buscando productos con fecha de caducidad entre {0} y {1}",
                    new Object[]{fechaInicioDate, fechaFinDate});

            // Llamar al método EJB para obtener los productos dentro del rango de fechas
            return ejb.encontrarProductosFarmaceuticosFechaCaducidadDesdeHasta(fechaInicioDate, fechaFinDate);
        } catch (LeerException ex) {
            LOGGER.severe("Error al leer los productos: " + ex.getMessage());
            throw new InternalServerErrorException("Error al procesar la solicitud.");
        } catch (DateTimeParseException ex) {
            LOGGER.severe("Formato de fecha inválido: " + ex.getMessage());
            throw new BadRequestException("Las fechas deben estar en formato yyyy-MM-dd.");
        }
    }
}