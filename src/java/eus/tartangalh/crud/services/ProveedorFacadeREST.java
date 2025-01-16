/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.ProductoFarmaceutico;
import eus.tartangalh.crud.ejb.ProveedorInterfaz;
import eus.tartangalh.crud.create.Proveedor;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.ArrayList;
import java.util.Date;
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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearProveedor(Proveedor proveedor) {
        try {
            LOGGER.log(Level.INFO, "Creando producto farmacéutico {0}", proveedor.getCif());
            ejb.crearProveedor(proveedor);
        } catch (CrearException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizarProveedor(Proveedor proveedor) {
        try {
            LOGGER.log(Level.INFO, "Actualizando producto {0}", proveedor.getIdProveedor());
            ejb.actualizarProveedor(proveedor);
        } catch (ActualizarException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @DELETE
    @Path("proveedorPorId/{id}")
    public void borrarProducto(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Borrando producto {0}", id);
            ejb.borrarProveedor(ejb.encontrarProveedor(id));
        } catch (LeerException | BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("proveedorPorId/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Proveedor mostrarProveedor(@PathParam("id") Integer id) {
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
    public List<Proveedor> mostrarTodosProveedores() {

        try {
            LOGGER.log(Level.INFO, "Buscando todos los proveedores");
            return ejb.mostrarTodosProveedores();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @GET
    @Path("mostrarProveedoresPorFecha/{fecha}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Proveedor> mostrarsProveedoresFecha(@PathParam("fecha") String fecha) {

        try {
            LOGGER.log(Level.INFO, "Buscando todos los proveedores");
            return ejb.mostrarsProveedoresFecha(fecha);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

}
