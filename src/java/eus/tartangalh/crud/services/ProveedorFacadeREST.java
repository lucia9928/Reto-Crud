/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.ejb.ProveedorInterfaz;
import eus.tartangalh.crud.create.Proveedor;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
 * @author markel
 */
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
        ejb.crearProveedor(proveedor);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizarProveedor(@PathParam("id") Integer id, Proveedor proveedor) {
        ejb.actualizarProveedor(id, proveedor);
    }

    @DELETE
    @Path("{id}")
    public void borrarProveedor(@PathParam("id") Integer id) {
        ejb.borrarProveedor(id);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Proveedor mostrarProveedor(@PathParam("id") Integer id) {
        return ejb.encontrarProveedor(id);

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Proveedor> mostrarTodosProveedores() {
        return ejb.mostrarTodosProveedores();

    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Proveedor> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return null;

    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return null;

    }

}
