/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.Trabajador;
import eus.tartangalh.crud.ejb.TrabajadorInterface;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 *
 * @author melany
 */
@Stateless
@Path("eus.tartangalh.crud.create.trabajador")
public class TrabajadorFacadeREST {

    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private TrabajadorInterface ejb;
    private Logger LOGGER=Logger.getLogger(TrabajadorFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearTrabajador(Trabajador trabajador) {
         try {
         LOGGER.log(Level.INFO,"creando receta {0}", trabajador.getDni());
        ejb.crearTrabajador(trabajador);
        } catch (CrearException ex){
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modificarTrabajador(@PathParam("id") String id, Trabajador trabajador) {
      try {
            LOGGER.log(Level.INFO,"Modificando el trabajador {0}",trabajador.getDni());
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
            LOGGER.log(Level.INFO,"Elimianddo trabajador {0}",id);
            ejb.eliminarTrabajador(ejb.encontrarTrabajdorId(id));
        } catch (LeerException|BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Trabajador encontrarPorId(@PathParam("id") String id) {
        try {
            LOGGER.log(Level.INFO,"Leer los trabajadores por id {0}",id);
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
            LOGGER.log(Level.INFO,"Buscando todos los trabajadores");
            return ejb.encontraTodosLosTrabajadores();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    
}
