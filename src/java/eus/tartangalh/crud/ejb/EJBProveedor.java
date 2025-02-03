/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Proveedor;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author markel
 */
@Stateless
public class EJBProveedor implements ProveedorInterfaz {

    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    @Override
    public void crearProveedor(Proveedor proveedor) throws CrearException {
        try {
            em.persist(proveedor);
        } catch (Exception e) {
            throw new CrearException(e.getMessage());
        }

    }

    @Override
    public Proveedor encontrarProveedor(Integer id) throws LeerException {
        Proveedor proveedor;
        try {
            proveedor = em.find(Proveedor.class,
                    id);
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return proveedor;

    }

    @Override
    public void borrarProveedor(Proveedor proveedor) throws BorrarException {
        try {

            em.remove(em.merge(proveedor));
        } catch (Exception e) {
            throw new BorrarException(e.getMessage());
        }

    }

    @Override
    public void actualizarProveedor(Proveedor proveedor) throws ActualizarException {
        try {
            if (!em.contains(proveedor)) {
                em.merge(proveedor);
            }
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException(e.getMessage());
        }
    }

    @Override
    public List<Proveedor> mostrarTodosProveedores() throws LeerException {

        
        try {
            return em.createNamedQuery("buscarTodosLosProveedores").getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
       
    }

    @Override
    public List<Proveedor> mostrarsProveedoresFecha(String fecha) throws LeerException {
        List<Proveedor> proveedores;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(fecha);
            proveedores
                    = em.createNamedQuery("buscarProveedorPorFecha").setParameter("date", date).getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return proveedores;

    }

}
