/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.Cliente;
import eus.tartangalh.crud.ejb.ClienteInterface;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@Path("Cliente")
public class ClienteFacadeREST {
@EJB
    private ClienteInterface ejb;
    private Logger LOGGER=Logger.getLogger(ClienteFacadeREST.class.getName());
        
    @POST
    @Path("Crear_Cliente")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearCliente(Cliente cliente) {
       try {
         LOGGER.log(Level.INFO,"creando cliente {0}", cliente.getDni());
        ejb.crearCliente(cliente);
        } catch (CrearException ex){
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modificarCliente(@PathParam("id") String id, Cliente cliente) {
try {
            LOGGER.log(Level.INFO,"Modificando el cliente{0}",cliente.getDni());
            ejb.modificarCliente(cliente);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @DELETE
    @Path("{id}")
    public void eliminarCliente(@PathParam("id") String id) {
 try {
            LOGGER.log(Level.INFO,"Elimianddo cliente {0}",id);
            ejb.eliminarCliente(ejb.encontrarClienteId(id));
        } catch (LeerException|BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @GET
    @Path("encontrar_cliente")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente encontrarPorId(@PathParam("id") String id) {
   try {
            LOGGER.log(Level.INFO,"Leer los clientes por id {0}",id);
            return ejb.encontrarClienteId(id);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
       

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> encontrarTodosLosClientes() {
          try {
            LOGGER.log(Level.INFO,"Buscar todos los clientes");
            return ejb.encontrarTodosCliente();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    
}
