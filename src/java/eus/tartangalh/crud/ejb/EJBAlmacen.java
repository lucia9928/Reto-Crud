/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Almacen;
import eus.tartangalh.crud.ejb.AlmacenInterface;
import java.io.Serializable;
import javax.ejb.CreateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andoni
 */
@Stateless
public class EJBAlmacen implements AlmacenInterface {

    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    /**
     * Este método crea un nuevo almacén en el sistema.
     *
     * @param almacen El objeto de la entidad Almacen que contiene los datos del
     * nuevo almacén.
     * @throws CreateException Lanzada cuando ocurre un error o excepción
     * durante la creación.
     */
    public void crearAlmacen(Almacen almacen) {
        try {
            em.persist(almacen);
        } catch (Exception e) {
            // Manejar la excepción según sea necesario.
        }
    }

    /**
     * Este método actualiza los datos de un almacén existente en el sistema.
     *
     * @param almacen El objeto de la entidad Almacen que contiene los datos
     * modificados del almacén.
     * @throws UpdateException Lanzada cuando ocurre un error o excepción
     * durante la actualización.
     */
    public void actualizarAlmacen(Almacen almacen) {
        try {
            if (!em.contains(almacen)) {
                em.merge(almacen);
            }
            em.flush();
        } catch (Exception e) {
            // Manejar la excepción según sea necesario.
        }
    }

    /**
     * Este método elimina un almacén del sistema.
     *
     * @param almacen El objeto de la entidad Almacen que se desea eliminar.
     * @throws DeleteException Lanzada cuando ocurre un error o excepción
     * durante la eliminación.
     */
    public void borrarAlmacen(Almacen almacen) {
        try {
            em.remove(em.merge(almacen));
        } catch (Exception e) {
            // Manejar la excepción según sea necesario.
        }
    }

}
