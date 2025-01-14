/*
 * Para cambiar esta cabecera de licencia, elige "Propiedades del proyecto".
 * Para cambiar este archivo de plantilla, selecciona "Herramientas | Plantillas"
 * y abre la plantilla en el editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Almacen;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase Stateless para la gestión de la entidad Almacen en el sistema.
 * Proporciona métodos para crear, actualizar, eliminar y buscar registros de
 * almacenes.
 *
 * @author Andoni
 */
@Stateless
public class EJBAlmacen implements AlmacenInterface {

    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    /**
     * Crea un nuevo almacén en el sistema.
     *
     * @param almacen El objeto de la entidad Almacen que contiene los datos del
     * nuevo almacén.
     * @throws CrearException Lanzada cuando ocurre un error durante la
     * creación.
     */
    @Override
    public void crearAlmacen(Almacen almacen) throws CrearException {
        try {
            em.persist(almacen);
        } catch (Exception e) {
            throw new CrearException("Error al crear el almacén: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un almacén existente.
     *
     * @param almacen El objeto de la entidad Almacen que contiene los datos
     * modificados.
     * @throws ActualizarException Lanzada cuando ocurre un error durante la
     * actualización.
     */
    @Override
    public void actualizarAlmacen(Almacen almacen) throws ActualizarException {
        try {
            if (!em.contains(almacen)) {
                em.merge(almacen);
            }
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException("Error al actualizar el almacén: " + e.getMessage());
        }
    }

    /**
     * Elimina un almacén del sistema.
     *
     * @param almacen El objeto de la entidad Almacen que se desea eliminar.
     * @throws BorrarException Lanzada cuando ocurre un error durante la
     * eliminación.
     */
    @Override
    public void borrarAlmacen(Almacen almacen) throws BorrarException {
        try {
            em.remove(em.merge(almacen));
        } catch (Exception e) {
            throw new BorrarException("Error al borrar el almacén: " + e.getMessage());
        }
    }

    /**
     * Encuentra un almacén por su ID.
     *
     * @param id El ID del almacén a buscar.
     * @return El objeto Almacen encontrado.
     * @throws LeerException Lanzada cuando ocurre un error durante la búsqueda.
     */
    @Override

    public Almacen encontrarAlmacen(Integer id) throws LeerException {
        try {
            return em.find(Almacen.class, id);
        } catch (Exception e) {
            throw new LeerException("Error al leer el almacén: " + e.getMessage());
        }
    }

    /**
     * Encuentra todos los almacenes registrados en el sistema.
     *
     * @return Una lista de objetos Almacen.
     * @throws LeerException Lanzada cuando ocurre un error durante la búsqueda.
     */
    @Override
    public List<Almacen> encontrarTodosAlmacenes() throws LeerException {
        try {
            return em.createNamedQuery("encontrarTodosAlmacenes", Almacen.class).getResultList();
        } catch (Exception e) {
            throw new LeerException("Error al leer todos los almacenes: " + e.getMessage());
        }
    }
}
