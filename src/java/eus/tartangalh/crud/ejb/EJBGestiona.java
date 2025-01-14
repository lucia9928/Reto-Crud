/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Gestiona;
import eus.tartangalh.crud.create.Proveedor;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author markel
 */
@Stateless

public class EJBGestiona implements GestionaInterfaz {

    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    @Override
    public void crearGestion(Gestiona gestiona) throws CrearException {
        try {
            em.persist(gestiona);
        } catch (Exception e) {
            throw new CrearException(e.getMessage());
        }
    }

    @Override
    public void actualizarGestiona(Gestiona gestiona) throws ActualizarException {
        try {
            if (!em.contains(gestiona)) {
                em.merge(gestiona);
            }
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException(e.getMessage());
        }
    }

    @Override
    public Gestiona encontrarGestiona(PathSegment id) throws LeerException {
        Gestiona gestiona;
        try {
            gestiona = em.find(Gestiona.class,
                    id);
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return gestiona;

    }

    @Override
    public void borrarGestiona(Gestiona gestiona) throws BorrarException {
        try {
            em.remove(em.merge(gestiona));
        } catch (Exception e) {
            throw new BorrarException(e.getMessage());
        }
    }

    @Override
    public List<Gestiona> mostrarTodosGestiona() throws LeerException {
        List<Gestiona> gestiones;
        try {
            
            gestiones
                    = em.createNamedQuery("buscarTodasLasGestiones").getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return gestiones;
    }

    @Override
    public List<Gestiona> findRange(Integer from, Integer to) throws LeerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
