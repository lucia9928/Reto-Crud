/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Cliente;
import eus.tartangalh.crud.create.RecetaMedica;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author melany
 * 
 */
@Stateless
public class EJBCliente implements ClienteInterface{
    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    @Override
    public void crearCliente(Cliente cliente) throws CrearException{
  try{
            em.persist(cliente);
        }catch(Exception e){
            throw new CrearException(e.getMessage());
        }      
    }

    @Override
    public List<Cliente> encontrarTodosCliente() throws LeerException {
 List<Cliente> clientes;
        try{
            clientes=em.createNamedQuery("encontrarTodasLasRecetas").getResultList();
        }catch(Exception e){
            throw new LeerException(e.getMessage());
        }
        return clientes;    }

    @Override
    public Cliente encontrarClienteId(Long id) throws LeerException {
  Cliente cliente;
        try{
            cliente=em.find(Cliente.class, id);
        }catch(Exception e){
            throw new LeerException(e.getMessage());
        }
        return cliente;
    }

    @Override
    public void eliminarCliente(Cliente cliente) throws BorrarException {
        try{
            em.remove(em.merge(cliente));
        }catch(Exception e){
            throw new BorrarException(e.getMessage());
        } throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarCliente(Cliente cliente) throws ActualizarException {
try{
            if(!em.contains(cliente))
                em.merge(cliente);
            em.flush();
        }catch(Exception e){
            throw new ActualizarException(e.getMessage());
        }    }
  
}
