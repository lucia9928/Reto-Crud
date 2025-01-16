/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Cliente;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;

/**
 *
 * @author melany
 */
public interface ClienteInterface {

    /**
     *
     * @param cliente
     * @throws CrearException
     */
    public void crearCliente (Cliente  cliente) throws CrearException;
     public List<Cliente> encontrarTodosCliente()throws LeerException;
     public Cliente encontrarClienteId(String id) throws LeerException;
      public void eliminarCliente(Cliente  cliente)throws BorrarException;
     public void modificarCliente(Cliente cliente)throws ActualizarException;
}
