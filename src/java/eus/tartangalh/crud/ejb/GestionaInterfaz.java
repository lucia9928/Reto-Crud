/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Gestiona;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Local;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author markel
 */
@Local
public interface GestionaInterfaz {

    public void crearGestion(Gestiona gestiona) throws CrearException;

    public void actualizarGestiona(Gestiona gestiona) throws ActualizarException;

    public Gestiona encontrarGestiona(@PathParam("id") PathSegment id) throws LeerException;

    public void borrarGestiona(@PathParam("id") Gestiona gestiona) throws BorrarException;

    public List<Gestiona> mostrarTodosGestiona() throws LeerException;

    public List<Gestiona> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws LeerException;

}
