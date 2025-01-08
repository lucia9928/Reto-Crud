/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import eus.tartangalh.crud.create.ProductoFarmaceutico;
import eus.tartangalh.crud.ejb.EJBProductoFarmaceutico;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
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
 * @author oscar
 */
@Stateless
@Path("eus.tartangalh.crud.create.productofarmaceutico")
public class ProductoFarmaceuticoFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private EJBProductoFarmaceutico ejb;

    /**
     * Logger for this class.
     */

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearProducto(ProductoFarmaceutico productoFarmaceutico) {
        try {
            LOGGER.log(Level.INFO, "Creando producto farmaceutico {0}",
                    productoFarmaceutico.getId());
            ejb.crearProducto(productoFarmaceutico);
        } catch (CrearException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizarProducto(ProductoFarmaceutico producto) {
        try {
            LOGGER.log(Level.INFO, "Actualizando producto {0}",
                    producto.getId());
            ejb.actualizarProducto(producto);
        } catch (ActualizarException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void borrarProducto(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Borrando producto {0}", id);
            ejb.borrarProducto(ejb.encontrarProductoFarmaceutico(id));
        } catch (LeerException | BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ProductoFarmaceutico encontrarProducto(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Buscando productos {0}", id);
            return ejb.encontrarProductoFarmaceutico(id);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductoFarmaceutico> encontrarTodos() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los productos");
            return ejb.encontrarTodosProductoFarmaceuticos();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("caducidad/{fechaLimite}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductoFarmaceutico> encontrarPorFechaCaducidad(@PathParam("fechaLimite") String fechaLimite) {
        try {
            LocalDate fecha = LocalDate.parse(fechaLimite);
            LOGGER.log(Level.INFO, "Buscando productos con fecha de caducidad anterior a {0}", fecha);
            return ejb.encontrarProductosFarmaceuticosFechaCaducidad(fecha);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("nombre/{nombre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductoFarmaceutico> encontrarPorNombre(@PathParam("nombre") String nombre) {
        try {
            LOGGER.log(Level.INFO, "Buscando productos con nombre {0}", nombre);
            return ejb.encontrarProductoPorNombre(nombre);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
