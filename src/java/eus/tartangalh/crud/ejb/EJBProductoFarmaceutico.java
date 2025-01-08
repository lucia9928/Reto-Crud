/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.ProductoFarmaceutico;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
public class EJBProductoFarmaceutico implements ProductoFarmaceuticoInterface {

    /**
     * EntityManager for DataModelExamplePU persistence unit.
     */
    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    @Override
    public void crearProducto(ProductoFarmaceutico producto) throws CrearException {

        try {
            em.persist(producto);
        } catch (Exception e) {
            throw new CrearException(e.getMessage());
        }

    }

    @Override
    public void actualizarProducto(ProductoFarmaceutico producto) throws ActualizarException {
        try {
            if (!em.contains(producto)) {
                em.merge(producto);
            }
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException(e.getMessage());
        }
    }

    @Override
    public void borrarProducto(ProductoFarmaceutico producto) throws BorrarException {
        try {
            em.remove(em.merge(producto));
        } catch (Exception e) {
            throw new BorrarException(e.getMessage());
        }
    }

    @Override
    public ProductoFarmaceutico encontrarProductoFarmaceutico(Integer id) throws LeerException {
        ProductoFarmaceutico producto;
        try {
            producto = em.find(ProductoFarmaceutico.class, id);
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return producto;
    }

    @Override
    public List<ProductoFarmaceutico> encontrarTodosProductoFarmaceuticos() throws LeerException {
        List<ProductoFarmaceutico> productos;
        try {
            productos = em.createNamedQuery("buscarTodosLosProductos").getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return productos;
    }

    @Override
    public List<ProductoFarmaceutico> encontrarProductosFarmaceuticosFechaCaducidad(LocalDate fechaLimite) throws LeerException {
               List<ProductoFarmaceutico> productos;
        try {
            productos = em.createNamedQuery("buscarProductosPorFechaCaducidad").getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return productos;
    }

    @Override
    public List<ProductoFarmaceutico> encontrarProductoPorNombre(String nombre) throws LeerException {
               List<ProductoFarmaceutico> productos;
        try {
            productos = em.createNamedQuery("buscarProductoPorNombre").getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return productos;
    }
}
