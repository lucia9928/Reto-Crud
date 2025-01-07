/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Cliente;
import java.util.List;

/**
 *
 * @author melany
 */
public interface ClienteInterface {
     public void crearCliente (Cliente  account);
         public List<Cliente> encontrarTodosCliente();
       public Cliente encontrarClienteId(Long id) ;
           public void eliminarCliente(Cliente cliente);
     public void modificarCliente(Cliente cliente);
}
