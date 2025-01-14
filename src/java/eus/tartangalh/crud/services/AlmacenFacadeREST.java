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
 * Autor: Javier Martín Uría
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
    public void borrarAlmacen(@PathParam("id") Long id) {
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
    public Almacen encontrar(@PathParam("id") Long id) {
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
}
