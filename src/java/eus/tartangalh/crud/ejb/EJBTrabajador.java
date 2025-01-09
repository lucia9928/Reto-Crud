/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Trabajador;
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
 */
@Stateless
public class EJBTrabajador implements TrabajadorInterface{
    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    @Override
    public void crearTrabajador(Trabajador trabajador) throws CrearException {
 try{
            em.persist(trabajador);
        }catch(Exception e){
            throw new CrearException(e.getMessage());
        }     
    }

    @Override
    public List<Trabajador> encontraTodosLosTrabajadores() throws LeerException {
 List<Trabajador> trabajadores;
        try{
            trabajadores=em.createNamedQuery("encontrarTodasLasRecetas").getResultList();
        }catch(Exception e){
            throw new LeerException(e.getMessage());
        }
        return trabajadores;
    }

    @Override
    public Trabajador encontrarTrabajdorId(String id) throws LeerException {
  Trabajador trabajador;
        try{
            trabajador=em.find(Trabajador.class, id);
        }catch(Exception e){
            throw new LeerException(e.getMessage());
        }
        return trabajador;    }

    @Override
    public void eliminarTrabajador(Trabajador trabajador) throws BorrarException {
  try{
            em.remove(em.merge(trabajador));
        }catch(Exception e){
            throw new BorrarException(e.getMessage());
        }    }

    @Override
    public void modificarTrabajador(Trabajador trabajador) throws ActualizarException {
        try{
            if(!em.contains(trabajador))
                em.merge(trabajador);
            em.flush();
        }catch(Exception e){
            throw new ActualizarException(e.getMessage());
        }   
    }
  
    
}
