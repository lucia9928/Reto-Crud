/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.Gestiona;
import eus.tartangalh.crud.create.GestionaId;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author 2dam
 */
@Stateless
@Path("eus.tartangalh.crud.create.gestiona")
public class GestionaFacadeREST {

    private GestionaId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;dni=dniValue;idProducto=idProductoValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        eus.tartangalh.crud.create.GestionaId key = new eus.tartangalh.crud.create.GestionaId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> dni = map.get("dni");
        if (dni != null && !dni.isEmpty()) {
            key.setDni(dni.get(0));
        }
        java.util.List<String> idProducto = map.get("idProducto");
        if (idProducto != null && !idProducto.isEmpty()) {
            key.setIdProducto(new java.lang.Integer(idProducto.get(0)));
        }
        return key;
    }

    public GestionaFacadeREST() {

    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Gestiona entity) {
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Gestiona entity) {
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        eus.tartangalh.crud.create.GestionaId key = getPrimaryKey(id);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Gestiona find(@PathParam("id") PathSegment id) {
        eus.tartangalh.crud.create.GestionaId key = getPrimaryKey(id);
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Gestiona> findAll() {
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Gestiona> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return null;
    }

}
