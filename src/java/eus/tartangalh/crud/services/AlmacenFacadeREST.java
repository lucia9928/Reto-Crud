/*
 * Servicio RESTful para la gestión de almacenes.
 * Este servicio permite realizar operaciones CRUD sobre entidades de tipo Almacen
 * mediante métodos HTTP como POST, PUT, GET y DELETE.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.Almacen;
import eus.tartangalh.crud.ejb.AlmacenInterface;
import eus.tartangalh.crud.ejb.EJBAlmacen;
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
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Servicio REST para gestionar entidades de tipo Almacen. Proporciona endpoints
 * para crear, actualizar, eliminar y consultar almacenes.
 *
 * Autor: Andoni
 */
@Path("eus.tartangalh.crud.create.almacen")
public class AlmacenFacadeREST {

    /**
     * Objeto EJB que implementa la lógica de negocio relacionada con Almacen.
     */
    @EJB
    private AlmacenInterface ejb;

    /**
     * Logger para registrar información y errores de la clase.
     */
    private Logger LOGGER = Logger.getLogger(AlmacenFacadeREST.class.getName());

    /**
     * Método POST para crear un nuevo almacén. Llama al método de negocio
     * "crearAlmacen" del EJB.
     *
     * @param almacen Objeto Almacen que contiene los datos a crear.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearAlmacen(Almacen almacen) {
        try {
            LOGGER.log(Level.INFO, "Creando almacén {0}", almacen.getIdAlmacen());
            ejb.crearAlmacen(almacen);
        } catch (CrearException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método PUT para actualizar un almacén existente. Llama al método de
     * negocio "actualizarAlmacen" del EJB.
     *
     * @param almacen Objeto Almacen con los datos actualizados.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizarAlmacen(Almacen almacen) {
        try {
            LOGGER.log(Level.INFO, "Actualizando almacén {0}", almacen.getIdAlmacen());
            ejb.actualizarAlmacen(almacen);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método DELETE para eliminar un almacén. Llama al método de negocio
     * "borrarAlmacen" del EJB.
     *
     * @param id Identificador del almacén a eliminar.
     */
    @DELETE
    @Path("{id}")
    public void borrarAlmacen(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Eliminando almacén {0}", id);
            ejb.borrarAlmacen(ejb.encontrarAlmacen(id));
        } catch (LeerException | BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método GET para consultar un almacén por su identificador. Llama al
     * método de negocio "encontrarAlmacen" del EJB.
     *
     * @param id Identificador del almacén a consultar.
     * @return Objeto Almacen correspondiente al identificador proporcionado.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Almacen encontrar(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Leyendo datos del almacén {0}", id);
            return ejb.encontrarAlmacen(id);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Método GET para consultar todos los almacenes. Llama al método de negocio
     * "encontrarTodosAlmacenes" del EJB.
     *
     * @return Lista de objetos Almacen.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> findAll() {
        try {
            LOGGER.log(Level.INFO, "Leyendo datos de todos los almacenes");
            return ejb.encontrarTodosAlmacenes();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("pais/{pais}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> encontrarPorPais(@PathParam("pais") String pais) {
        try {
            LOGGER.log(Level.INFO, "Buscando almacenes por pais {0}", pais);
            return ejb.encontrarAlmacenPorPais(pais);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("ciudad/{ciudad}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> encontrarPorCiudad(@PathParam("ciudad") String ciudad) {
        try {
            LOGGER.log(Level.INFO, "Buscando almacenes por ciudad {0}", ciudad);
            return ejb.encontrarAlmacenPorCiudad(ciudad);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("caducidad/{fechaLimite}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> encontrarAlmacenPorFecha(@PathParam("fechaLimite") String fechaLimite) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localdate = LocalDate.parse(fechaLimite, formatter);
            Date fecha = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            LOGGER.log(Level.INFO, "Buscando productos con fecha de caducidad anterior a {0}", fecha);
            return ejb.encontrarAlmacenPorFecha(fecha);
        } catch (LeerException ex) {
            LOGGER.severe("Error al leer los productos: " + ex.getMessage());
            throw new InternalServerErrorException("Error al procesar la solicitud.");
        }
    }

    @GET
    @Path("{fechaInicio}/{fechaFin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> encontrarPorRangoDeFechas(
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
            return ejb.encontrarAlmacenPorFechaDesdeHasta(fechaInicioDate, fechaFinDate);
        } catch (LeerException ex) {
            LOGGER.severe("Error al leer los productos: " + ex.getMessage());
            throw new InternalServerErrorException("Error al procesar la solicitud.");
        } catch (DateTimeParseException ex) {
            LOGGER.severe("Formato de fecha inválido: " + ex.getMessage());
            throw new BadRequestException("Las fechas deben estar en formato yyyy-MM-dd.");
        }
    }
}
