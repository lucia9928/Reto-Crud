package eus.tartangalh.crud.services;

import eus.tartangalh.crud.create.Almacen;
import eus.tartangalh.crud.ejb.AlmacenInterface;
import eus.tartangalh.crud.ejb.EJBAlmacen;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Servicio RESTful para la gestión de almacenes. Este servicio proporciona
 * métodos para realizar operaciones CRUD sobre entidades de tipo Almacen.
 * Permite la creación, actualización, eliminación y consulta de almacenes
 * mediante los métodos HTTP POST, PUT, DELETE y GET.
 *
 * @author Andoni
 */
@Path("eus.tartangalh.crud.create.almacen")
public class AlmacenFacadeREST {

    /**
     * Objeto EJB que implementa la lógica de negocio relacionada con Almacen.
     */
    @EJB
    private AlmacenInterface ejb;

    /**
     * Logger para registrar información y errores de la clase.
     */
    private Logger LOGGER = Logger.getLogger(AlmacenFacadeREST.class.getName());

    /**
     * Crea un nuevo almacén en la base de datos. Llama al método "crearAlmacen"
     * del EJB.
     *
     * @param almacen Objeto Almacen que contiene los datos a crear.
     * @throws InternalServerErrorException Si ocurre un error durante la
     * creación del almacén.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void crearAlmacen(Almacen almacen) {
        try {
            LOGGER.log(Level.INFO, "Creando almacén {0}", almacen.getIdAlmacen());
            ejb.crearAlmacen(almacen);
        } catch (CrearException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Actualiza un almacén existente en la base de datos. Llama al método
     * "actualizarAlmacen" del EJB.
     *
     * @param almacen Objeto Almacen con los datos actualizados.
     * @throws InternalServerErrorException Si ocurre un error durante la
     * actualización del almacén.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizarAlmacen(Almacen almacen) {
        try {
            LOGGER.log(Level.INFO, "Actualizando almacén {0}", almacen.getIdAlmacen());
            ejb.actualizarAlmacen(almacen);
        } catch (ActualizarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Elimina un almacén de la base de datos. Llama al método "borrarAlmacen"
     * del EJB.
     *
     * @param id Identificador del almacén a eliminar.
     * @throws InternalServerErrorException Si ocurre un error durante la
     * eliminación del almacén.
     */
    @DELETE
    @Path("{id}")
    public void borrarAlmacen(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Eliminando almacén {0}", id);
            ejb.borrarAlmacen(ejb.encontrarAlmacen(id));
        } catch (LeerException | BorrarException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Obtiene un almacén por su identificador.
     *
     * @param id Identificador del almacén a consultar.
     * @return Objeto Almacen correspondiente al identificador proporcionado.
     * @throws InternalServerErrorException Si ocurre un error al leer el
     * almacén.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Almacen encontrar(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Leyendo datos del almacén {0}", id);
            return ejb.encontrarAlmacen(id);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Obtiene todos los almacenes registrados en la base de datos.
     *
     * @return Lista de objetos Almacen.
     * @throws InternalServerErrorException Si ocurre un error al leer los
     * almacenes.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> findAll() {
        try {
            LOGGER.log(Level.INFO, "Leyendo datos de todos los almacenes");
            return ejb.encontrarTodosAlmacenes();
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Obtiene los almacenes registrados en un país específico.
     *
     * @param pais Nombre del país a consultar.
     * @return Lista de objetos Almacen del país especificado.
     * @throws InternalServerErrorException Si ocurre un error al leer los
     * almacenes por país.
     */
    @GET
    @Path("pais/{pais}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> encontrarPorPais(@PathParam("pais") String pais) {
        try {
            LOGGER.log(Level.INFO, "Buscando almacenes por pais {0}", pais);
            return ejb.encontrarAlmacenPorPais(pais);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Obtiene los almacenes registrados en una ciudad específica.
     *
     * @param ciudad Nombre de la ciudad a consultar.
     * @return Lista de objetos Almacen de la ciudad especificada.
     * @throws InternalServerErrorException Si ocurre un error al leer los
     * almacenes por ciudad.
     */
    @GET
    @Path("ciudad/{ciudad}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> encontrarPorCiudad(@PathParam("ciudad") String ciudad) {
        try {
            LOGGER.log(Level.INFO, "Buscando almacenes por ciudad {0}", ciudad);
            return ejb.encontrarAlmacenPorCiudad(ciudad);
        } catch (LeerException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Obtiene los almacenes cuya fecha de adquisición sea anterior a la fecha
     * especificada.
     *
     * @param fechaLimite Fecha límite en formato yyyy-MM-dd.
     * @return Lista de objetos Almacen con fecha de adquisición anterior a la
     * fecha límite.
     * @throws InternalServerErrorException Si ocurre un error al leer los
     * almacenes por fecha.
     */
    @GET
    @Path("fechaAdquisicion/{fechaLimite}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> encontrarAlmacenPorFecha(@PathParam("fechaLimite") String fechaLimite) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localdate = LocalDate.parse(fechaLimite, formatter);
            Date fecha = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            LOGGER.log(Level.INFO, "Buscando almacenes con fecha de adquisicion anterior a {0}", fecha);
            return ejb.encontrarAlmacenPorFecha(fecha);
        } catch (LeerException ex) {
            LOGGER.severe("Error al leer los almacenes: " + ex.getMessage());
            throw new InternalServerErrorException("Error al procesar la solicitud.");
        }
    }

    /**
     * Obtiene los almacenes cuya fecha de adquisición esté dentro del rango de
     * fechas especificado.
     *
     * @param fechaInicio Fecha de inicio del rango en formato yyyy-MM-dd.
     * @param fechaFin Fecha de fin del rango en formato yyyy-MM-dd.
     * @return Lista de objetos Almacen cuyo rango de adquisición se encuentra
     * entre las fechas proporcionadas.
     * @throws InternalServerErrorException Si ocurre un error al leer los
     * almacenes por rango de fechas.
     * @throws BadRequestException Si las fechas proporcionadas no están en
     * formato válido.
     */
    @GET
    @Path("{fechaInicio}/{fechaFin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> encontrarPorRangoDeFechas(
            @PathParam("fechaInicio") String fechaInicio,
            @PathParam("fechaFin") String fechaFin) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convertir las fechas recibidas a objetos LocalDate
            LocalDate localDateInicio = LocalDate.parse(fechaInicio, formatter);
            LocalDate localDateFin = LocalDate.parse(fechaFin, formatter);

            // Convertir LocalDate a Date
            Date fechaInicioDate = Date.from(localDateInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date fechaFinDate = Date.from(localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());

            LOGGER.log(Level.INFO, "Buscando productos con fecha de adquisicion entre {0} y {1}",
                    new Object[]{fechaInicioDate, fechaFinDate});

            return ejb.encontrarAlmacenPorFechaDesdeHasta(fechaInicioDate, fechaFinDate);
        } catch (LeerException ex) {
            LOGGER.severe("Error al leer los almacenes: " + ex.getMessage());
            throw new InternalServerErrorException("Error al procesar la solicitud.");
        } catch (DateTimeParseException ex) {
            LOGGER.severe("Formato de fecha inválido: " + ex.getMessage());
            throw new BadRequestException("Las fechas deben estar en formato yyyy-MM-dd.");
        }
    }
}
