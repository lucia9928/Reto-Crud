package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Almacen;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz que define las operaciones CRUD para la entidad Almacen en el
 * sistema. Proporciona métodos para crear, leer, actualizar y eliminar
 * almacenes. Ademas incluye una busqueda por id, por ciudad, por pais, por fecha y por rango de fechas.
 *
 * @author Andoni
 */
@Local
public interface AlmacenInterface {

    /**
     * Crea un nuevo almacén en el sistema.
     *
     * @param almacen Objeto de la entidad Almacen con los datos del nuevo
     * almacén.
     * @throws CrearException Si ocurre un error durante la creación del
     * almacén.
     */
    public void crearAlmacen(Almacen almacen) throws CrearException;

    /**
     * Encuentra un almacén por su identificador único.
     *
     * @param id Identificador del almacén a buscar.
     * @return El almacén encontrado.
     * @throws LeerException Si ocurre un error durante la búsqueda del almacén.
     */
    public Almacen encontrarAlmacen(Integer id) throws LeerException;

    /**
     * Busca almacenes por país.
     *
     * @param pais Nombre del país.
     * @return Lista de almacenes ubicados en el país especificado.
     * @throws LeerException Si ocurre un error durante la búsqueda.
     */
    public List<Almacen> encontrarAlmacenPorPais(String pais) throws LeerException;

    /**
     * Busca almacenes por ciudad.
     *
     * @param ciudad Nombre de la ciudad.
     * @return Lista de almacenes ubicados en la ciudad especificada.
     * @throws LeerException Si ocurre un error durante la búsqueda.
     */
    public List<Almacen> encontrarAlmacenPorCiudad(String ciudad) throws LeerException;

    /**
     * Actualiza la información de un almacén existente.
     *
     * @param almacen Objeto de la entidad Almacen con los datos actualizados.
     * @throws ActualizarException Si ocurre un error durante la actualización.
     */
    public void actualizarAlmacen(Almacen almacen) throws ActualizarException;

    /**
     * Elimina un almacén del sistema.
     *
     * @param almacen Objeto de la entidad Almacen a eliminar.
     * @throws BorrarException Si ocurre un error durante la eliminación.
     */
    public void borrarAlmacen(Almacen almacen) throws BorrarException;

    /**
     * Obtiene una lista de todos los almacenes registrados en el sistema.
     *
     * @return Lista de todos los almacenes.
     * @throws LeerException Si ocurre un error durante la búsqueda.
     */
    public List<Almacen> encontrarTodosAlmacenes() throws LeerException;

    /**
     * Busca almacenes por una fecha de adquisición específica.
     *
     * @param fechaLimite Fecha de adquisición a buscar.
     * @return Lista de almacenes con la fecha especificada.
     * @throws LeerException Si ocurre un error durante la búsqueda.
     */
    public List<Almacen> encontrarAlmacenPorFecha(Date fechaLimite) throws LeerException;

    /**
     * Busca almacenes dentro de un rango de fechas de adquisición.
     *
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin Fecha de fin del rango.
     * @return Lista de almacenes adquiridos en el rango especificado.
     * @throws LeerException Si ocurre un error durante la búsqueda.
     */
    public List<Almacen> encontrarAlmacenPorFechaDesdeHasta(Date fechaInicio, Date fechaFin) throws LeerException;
}
