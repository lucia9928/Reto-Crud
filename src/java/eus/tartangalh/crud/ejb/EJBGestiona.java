/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Gestiona;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author markel
 */
public class EJBGestiona implements GestionaInterfaz {

    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    @Override
    public void crearGestion(Gestiona gestiona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarGestiona(Gestiona gestiona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
