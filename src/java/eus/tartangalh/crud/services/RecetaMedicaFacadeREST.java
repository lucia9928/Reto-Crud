/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.RecetaMedica;
import eus.tartangalh.crud.ejb.RecetaMedicaInterface;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
 * @author Melany
 */
    @Path("eus.tartangalh.crud.create.recetamedica")
    public class RecetaMedicaFacadeREST {
    @EJB
    private RecetaMedicaInterface ejb;
     /**
     * Logger for this class.
     */
    private Logger LOGGER=Logger.getLogger(RecetaMedicaFacadeREST.class.getName());
    /**
     *
     * @param receta
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearRecetaMedica(RecetaMedica receta) {
         try {
         LOGGER.log(Level.INFO,"creando receta {0}", receta.getIdReceta());
        ejb.crearRecetaMedica(receta);
        } catch (CrearException ex){
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
     
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public void modificarRecetaMedica(RecetaMedica receta) {
        try {
            LOGGER.log(Level.INFO,"Updating account {0}",receta.getIdReceta());
            ejb.modificarRecetaMedica(receta);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @DELETE
    @Path("{id}")
    public void eliminarRecetamedica(@PathParam("id") Integer id) {
     try {
            LOGGER.log(Level.INFO,"Elimianddo Receta {0}",id);
            ejb.eliminarRecetaMedica(ejb.encontrarRecetasPorId(id));
        } catch (LeerException|BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public RecetaMedica encontrarRecetaPorId(@PathParam("id") Integer id) {
     try {
            LOGGER.log(Level.INFO,"Leer las recetas por id {0}",id);
            return ejb.encontrarRecetasPorId(id);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
       
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RecetaMedica> encontrarTodasLasRecetas() {
     try {
            LOGGER.log(Level.INFO,"Reading data for all accounts");
            return ejb.encontrarTodasLasRecetas();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
   
}
