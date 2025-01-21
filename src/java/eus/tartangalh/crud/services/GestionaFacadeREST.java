/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.Gestiona;
import eus.tartangalh.crud.create.GestionaId;
import eus.tartangalh.crud.ejb.GestionaInterfaz;
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
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author markel
 */
@Stateless
@Path("eus.tartangalh.crud.create.gestiona")
public class GestionaFacadeREST {

    @EJB
    private GestionaInterfaz ejb;

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

    private Logger LOGGER = Logger.getLogger(ProveedorFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearGestiona(Gestiona gestiona) throws CrearException {
        try {
            LOGGER.log(Level.INFO, "Creando producto farmacéutico {0}", gestiona.getGestionaId());
            ejb.crearGestion(gestiona);
        } catch (CrearException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizarGestiona(Gestiona gestiona) {
        try {
            LOGGER.log(Level.INFO, "Creando producto farmacéutico {0}", gestiona.getGestionaId());
            ejb.actualizarGestiona(gestiona);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @DELETE
    @Path("gestionaPorId/{id}")
    public void borrarGestiona(@PathParam("id") Gestiona gestiona) throws BorrarException {
        try {
            LOGGER.log(Level.INFO, "Actualizando producto {0}", gestiona.getGestionaId());
            ejb.borrarGestiona(gestiona);
        } catch (BorrarException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("{dni}/{idProducto}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Gestiona encontrarGestiona(@PathParam("dni") String dni, @PathParam("idProducto") Integer idProducto){

        try {
            LOGGER.log(Level.INFO, "Buscando proveedor {0}", idProducto);
            return ejb.encontrarGestiona(dni, idProducto);
        } catch (LeerException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Gestiona> mostrarTodosGestiona() throws LeerException {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los proveedores");
            return ejb.mostrarTodosGestiona();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
/*
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Gestiona> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws LeerException {
        return null;
    }
*/
}
