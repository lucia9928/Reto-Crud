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
     * EntityManager para la unidad de persistencia "CRUDWebApplicationPU".
     */
    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    /**
     * Crea un nuevo ProductoFarmaceutico en la base de datos.
     * @param producto El objeto ProductoFarmaceutico que se desea crear.
     * @throws CrearException Lanzada si ocurre un error durante la operación de persistencia.
     */
    @Override
    public void crearProducto(ProductoFarmaceutico producto) throws CrearException {
        try {
            em.persist(producto);
        } catch (Exception e) {
            throw new CrearException(e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un ProductoFarmaceutico en la base de datos.
     * @param producto El objeto ProductoFarmaceutico que contiene los datos actualizados.
     * @throws ActualizarException Lanzada si ocurre un error durante la operación de actualización.
     */
    @Override
    public void actualizarProducto(ProductoFarmaceutico producto) throws ActualizarException {
        try {
            if (!em.contains(producto)) {
                em.merge(producto); // Si el producto no está en el contexto de persistencia, se realiza un merge.
            }
            em.flush(); // Se sincronizan los cambios con la base de datos.
        } catch (Exception e) {
            throw new ActualizarException(e.getMessage());
        }
    }

    /**
     * Elimina un ProductoFarmaceutico de la base de datos.
     * @param producto El objeto ProductoFarmaceutico que se desea eliminar.
     * @throws BorrarException Lanzada si ocurre un error durante la operación de eliminación.
     */
    @Override
    public void borrarProducto(ProductoFarmaceutico producto) throws BorrarException {
        try {
            em.remove(em.merge(producto)); // Se realiza un merge si el objeto no está en el contexto de persistencia.
        } catch (Exception e) {
            throw new BorrarException(e.getMessage());
        }
    }

    /**
     * Busca un ProductoFarmaceutico en la base de datos utilizando su ID.
     * @param id El ID del producto a buscar.
     * @return El objeto ProductoFarmaceutico encontrado.
     * @throws LeerException Lanzada si ocurre un error durante la operación de búsqueda.
     */
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

    /**
     * Recupera todos los ProductoFarmaceutico de la base de datos.
     * @return Una lista con todos los productos.
     * @throws LeerException Lanzada si ocurre un error durante la operación de búsqueda.
     */
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

    /**
     * Recupera todos los ProductoFarmaceutico cuya fecha de caducidad es anterior a una fecha límite.
     * @param fechaLimite La fecha límite para filtrar los productos.
     * @return Una lista de productos que cumplen con el criterio.
     * @throws LeerException Lanzada si ocurre un error durante la operación de búsqueda.
     */
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

    /**
     * Recupera todos los ProductoFarmaceutico que coinciden con un nombre específico.
     * @param nombre El nombre del producto a buscar.
     * @return Una lista de productos que coinciden con el nombre.
     * @throws LeerException Lanzada si ocurre un error durante la operación de búsqueda.
     */
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
