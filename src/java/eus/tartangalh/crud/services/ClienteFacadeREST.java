/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.Cliente;
import eus.tartangalh.crud.create.RecetaMedica;
import eus.tartangalh.crud.create.Trabajador;
import eus.tartangalh.crud.ejb.ClienteInterface;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
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
import javax.ws.rs.core.Response;

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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    public Response crearCliente(Cliente cliente) { 
        try { LOGGER.log(Level.INFO, "Creando cliente {0}", cliente.getDni()); 
        ejb.crearCliente(cliente);  
        return Response.status(Response.Status.CREATED).build(); 
        } catch (CrearException ex) { 
         LOGGER.severe(ex.getMessage());     
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build(); 
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modificarCliente(Cliente cliente) {
        try {
            LOGGER.log(Level.INFO,"Modificando el cliente{0}",cliente.getDni());
            ejb.modificarCliente(cliente);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    
    @GET
    @Path("/busqueda/{userEmail}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente buscar(@PathParam("userEmail") String email) {
        try {
            LOGGER.info("entrando a buscar " + email);
            Cliente cliente = ejb.buscarCliente(email);
            return cliente;
        } catch (LeerException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     *
     * @param id
     */
    @DELETE
    @Path("{id}")
    public void eliminarCliente(@PathParam("id") String id) {
    try {
        LOGGER.log(Level.INFO,"Elimianddo cliente {0}",id);
        ejb.eliminarCliente(ejb.encontrarClienteId(id));
    } catch (LeerException | BorrarException ex) {
          LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());          
     }
    }

    @GET
    @Path("{id}")
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
     @GET
    @Path("fecha/{fechaInicio}/{fechaFin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> buscarClientesPorFecha(
            @PathParam("fechaInicio") String fechaInicioStr,
            @PathParam("fechaFin") String fechaFinStr) {
        try {
            Date fechaInicio = convertirStringAFecha(fechaInicioStr);
            Date fechaFin = convertirStringAFecha(fechaFinStr);

            LOGGER.log(Level.INFO, "Buscando clientes entre {0} y {1}", new Object[]{fechaInicio, fechaFin});
            return ejb.buscarClientesPorFecha(fechaInicio, fechaFin);
        }catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    @PUT
    @Path("recoverPassword")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void recoverPassword(Cliente entity) {
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
    public void editPassword(Cliente entity) {
        try {
            LOGGER.log(Level.INFO, "cliente", entity.getDni());
            ejb.actualizarContrasena(entity);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    @GET
    @Path("/{Clidni}/{contrasenaCli}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente iniciarSesion(@PathParam("Clidni") String id, @PathParam("contrasenaCli") String passwd) {
        try {
            LOGGER.log(Level.INFO, "Intentando iniciar sesion");
            Cliente cliente = ejb.iniciarSesion(id, passwd);
            LOGGER.log(Level.INFO, "Buscando todos los trabajadores");
            //usuario.setContrasenia(null);
            return cliente;
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    private Date convertirStringAFecha(String fechaStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(fechaStr);
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, "Error al convertir la fecha: {0}", fechaStr);
            throw new BadRequestException("Formato de fecha inválido. Debe ser 'yyyy-MM-dd'");
        }
    }
}