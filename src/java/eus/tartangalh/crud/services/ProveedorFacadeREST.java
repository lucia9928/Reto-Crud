/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.ejb.ProveedorInterfaz;
import eus.tartangalh.crud.create.Proveedor;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author markel
 */
@Stateless
@Path("eus.tartangalh.crud.create.proveedor")
public class ProveedorFacadeREST {

    @EJB
    private ProveedorInterfaz ejb;

    public ProveedorFacadeREST() {

    }
    private Logger LOGGER = Logger.getLogger(ProveedorFacadeREST.class.getName());

    @POST
    @Path("{proveedor}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearProveedor(@PathParam("proveedor") Proveedor proveedor) throws CrearException {
        try {
            LOGGER.log(Level.INFO, "Creando producto farmacéutico {0}", proveedor.getCif());
            ejb.crearProveedor(proveedor);
        } catch (CrearException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @PUT
    @Path("{proveedor}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizarProveedor(@PathParam("proveedor") Proveedor proveedor) throws ActualizarException {
        try {
            LOGGER.log(Level.INFO, "Actualizando producto {0}", proveedor.getCif());
            ejb.actualizarProveedor(proveedor);
        } catch (ActualizarException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @DELETE
    @Path("{id}")
    public void borrarProveedor(@PathParam("proveedor") Proveedor proveedor) throws BorrarException {
        try {
            LOGGER.log(Level.INFO, "Actualizando producto {0}", proveedor.getCif());
            ejb.borrarProveedor(proveedor);
        } catch (BorrarException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Proveedor mostrarProveedor(@PathParam("id") Integer id) throws LeerException {
        try {
            LOGGER.log(Level.INFO, "Buscando proveedor {0}", id);
            return ejb.encontrarProveedor(id);
        } catch (LeerException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Proveedor> mostrarTodosProveedores() throws LeerException {

        try {
            LOGGER.log(Level.INFO, "Buscando todos los proveedores");
            return ejb.mostrarTodosProveedores();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @GET
    @Path("mostrarsProveedoresFecha/{fecha}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Proveedor> mostrarsProveedoresFecha(@PathParam("fecha") String fecha) throws LeerException {

        try {
            LOGGER.log(Level.INFO, "Buscando todos los proveedores");
            return ejb.mostrarsProveedoresFecha(fecha);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

}
