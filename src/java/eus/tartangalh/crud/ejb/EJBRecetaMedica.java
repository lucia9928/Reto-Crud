/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.RecetaMedica;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author melany
 */
@Stateless
public class EJBRecetaMedica implements RecetaMedicaInterface{
  @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;
  
    @Override
    public void crearRecetaMedica(RecetaMedica receta)throws CrearException {
        try{
            em.persist(receta);
        }catch(Exception e){
            throw new CrearException(e.getMessage());
        }  
   }

   @Override
    public List<RecetaMedica> encontrarTodasLasRecetas() throws LeerException {
          List<RecetaMedica> recetas;
        try{
            recetas=em.createNamedQuery("encontrarTodasLasRecetas").getResultList();
        }catch(Exception e){
            throw new LeerException(e.getMessage());
        }
        return recetas;
    }

    @Override
    public RecetaMedica encontrarRecetasPorId(Integer id) throws LeerException {
      RecetaMedica receta;
        try{
            receta=em.find(RecetaMedica.class, id);
        }catch(Exception e){
            throw new LeerException(e.getMessage());
        }
        return receta;
    }
    @Override
    public void eliminarRecetaMedica(RecetaMedica receta) throws BorrarException {
 try{
            em.remove(em.merge(receta));
        }catch(Exception e){
            throw new BorrarException(e.getMessage());
        }
    }

    @Override
    public void modificarRecetaMedica(RecetaMedica receta) throws ActualizarException {
 try{
            if(!em.contains(receta))
                em.merge(receta);
            em.flush();
        }catch(Exception e){
            throw new ActualizarException(e.getMessage());
        } 
    }

    @Override
    public List<RecetaMedica> encontrarRecetasPorFecha(Date fechaInicio, Date fechaFin) throws LeerException {
 try {
            if (fechaInicio == null || fechaFin == null) {
                throw new IllegalArgumentException("Las fechas no pueden ser nulas.");
            }
            return em.createNamedQuery("buscarRecetasPorFecha", RecetaMedica.class)
                     .setParameter("fechaInicio", fechaInicio)
                     .setParameter("fechaFin", fechaFin)
                     .getResultList();
        } catch (Exception ex) {
            throw new LeerException("Error al buscar recetas en el rango de fechas: " + ex.getMessage());
        }
    
    }
 
    
}