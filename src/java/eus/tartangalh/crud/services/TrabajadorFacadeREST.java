/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.Cliente;
import eus.tartangalh.crud.create.Trabajador;
import eus.tartangalh.crud.ejb.TrabajadorInterface;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author melany
 */
@Stateless
@Path("Trabajador")
public class TrabajadorFacadeREST {

    @EJB
    private TrabajadorInterface ejb;

    public TrabajadorFacadeREST() {
    }

    private Logger LOGGER = Logger.getLogger(TrabajadorFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response crearTrabajador(Trabajador trabajador) {
        try {
            LOGGER.log(Level.INFO, "creando trabajador {0}", trabajador.getDni());
            ejb.crearTrabajador(trabajador);
            return Response.status(Response.Status.CREATED).build();

        } catch (CrearException ex) {
            LOGGER.severe(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @PUT

    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modificarTrabajador(Trabajador trabajador) {
        try {
            LOGGER.log(Level.INFO, "Modificando el trabajador {0}", trabajador.getDni());
            ejb.modificarTrabajador(trabajador);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void eliminarTrabajador(@PathParam("id") String id) {
        try {
            LOGGER.log(Level.INFO, "Elimianddo trabajador {0}", id);
            ejb.eliminarTrabajador(ejb.encontrarTrabajdorId(id));
        } catch (LeerException | BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Trabajador encontrarPorId(@PathParam("id") String id) {
        try {
            LOGGER.log(Level.INFO, "Leer los trabajadores por id {0}", id);
            return ejb.encontrarTrabajdorId(id);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trabajador> encontrarTodosLosTrabajdores() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los trabajadores");
            return ejb.encontraTodosLosTrabajadores();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("/busqueda/{userEmail}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Trabajador buscar(@PathParam("userEmail") String email) {
        try {
            LOGGER.info("entrando a buscar " + email);
            Trabajador trabajador = ejb.buscarTrabajador(email);
            return trabajador;
        } catch (LeerException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Path("recoverPassword")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void recoverPassword(Trabajador entity) {
        try {
            LOGGER.log(Level.INFO, "Updating client {0}", entity.getDni());
            ejb.recuperarContrasena(entity);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Path("editPassword")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editPassword(Trabajador entity) {
        try {
            LOGGER.log(Level.INFO, "cliente", entity.getDni());
            ejb.actualizarContrasena(entity);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{Tradni}/{contrasenaTra}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Trabajador iniciarSesion(@PathParam("Tradni") String id, @PathParam("contrasenaTra") String passwd) {
        try {
            LOGGER.log(Level.INFO, "Intentando iniciar sesión para usuario: {0}", id);

            Trabajador usuario = ejb.iniciarSesion(id, passwd);

            LOGGER.log(Level.INFO, "Inicio de sesión exitoso para usuario: {0}", id);
            return usuario;
        } catch (LeerException ex) {
            LOGGER.warning("Credenciales incorrectas para usuario: " + id);
            throw new NotAuthorizedException("Usuario o contraseña incorrectos");
        } catch (NoResultException ex) {
            LOGGER.warning("Usuario no encontrado: " + id);
            throw new NotFoundException("Usuario no encontrado");
        } catch (Exception ex) {
            LOGGER.severe("Error inesperado en autenticación: " + ex.getMessage());
            throw new InternalServerErrorException("Error en el servidor");
        }
    }

}
