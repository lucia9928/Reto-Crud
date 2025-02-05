/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import eus.tartangalh.crud.create.Cliente;
import eus.tartangalh.crud.create.Trabajador;
import eus.tartangalh.crud.crypto.Asymmetric;
import eus.tartangalh.crud.crypto.EmailServicio;
import eus.tartangalh.crud.crypto.Hash;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author melany
 */
@Stateless
public class EJBTrabajador implements TrabajadorInterface {

    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    @Override
    public void crearTrabajador(Trabajador trabajador) throws CrearException {
        try {
            // Load the private key using the Asymmetric class
            Asymmetric asymmetric = new Asymmetric();

            // Decrypt the password
            byte[] encryptedPassword = DatatypeConverter.parseHexBinary(trabajador.getContrasena());
            byte[] decryptedPassword = asymmetric.decrypt(encryptedPassword);

            // Check if decryption was successful
            if (decryptedPassword == null) {
                throw new CrearException("Failed to decrypt the password.");
            }

            // Hash the decrypted password
            String hashedPassword = Hash.hashText(new String(decryptedPassword));

            // Set the hashed password in the worker entity
            trabajador.setContrasena(hashedPassword);

            // Persist the worker entity
            em.persist(trabajador);
        } catch (Exception e) {
            // Handle exceptions and wrap them in a custom exception
            throw new CrearException(e.getMessage());
        }
    }

    @Override
    public List<Trabajador> encontraTodosLosTrabajadores() throws LeerException {
        List<Trabajador> trabajadores;
        try {
            trabajadores = em.createNamedQuery("encontrarTodosLosTrabajdores").getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return trabajadores;
    }

    @Override
    public Trabajador encontrarTrabajdorId(String id) throws LeerException {
        Trabajador trabajador;
        try {
            trabajador = em.find(Trabajador.class, id);
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return trabajador;
    }

    @Override
    public void eliminarTrabajador(Trabajador trabajador) throws BorrarException {

        try {
            em.remove(em.merge(trabajador));
        } catch (Exception e) {
            throw new BorrarException(e.getMessage());
        }
    }

    @Override
    public void modificarTrabajador(Trabajador trabajador) throws ActualizarException {
        try {
            if (!em.contains(trabajador)) {
                em.merge(trabajador);
            }
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException(e.getMessage());
        }
    }

    @Override
    public List<Trabajador> encontrarTrabajdorEmail(String email) throws LeerException {
        List<Trabajador> trabajador;
        try {
            trabajador = em.createNamedQuery("encontrarTrabajdorEmail").getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return trabajador;
    }

    @Override
    public Trabajador buscarTrabajador(String email) throws LeerException {
        try {
            Trabajador trabajador = em.createNamedQuery("buscarTrabajador", Trabajador.class)
                    .setParameter("userEmail", email)
                    .getSingleResult();
            LOGGER.info("Trabajador encontrado: " + trabajador.toString());
            return trabajador;
        } catch (NoResultException e) {
            LOGGER.warning("No se encontró un trabajador con el email: " + email);
            return null; // Devuelve null en lugar de lanzar una excepción
        } catch (Exception e) {
            throw new LeerException("Error al buscar trabajador: " + e.getMessage());
        }
    }

    @Override
    public void recuperarContrasena(Trabajador trabajador) throws ActualizarException {
        try {
            if (trabajador == null || trabajador.getDni() == null) {
                throw new ActualizarException("El objeto Trabajador o su DNI es nulo.");
            }

            // Buscar el trabajador en la base de datos
            Trabajador trabajadorBD = em.find(Trabajador.class, trabajador.getDni());
            if (trabajadorBD == null) {
                throw new ActualizarException("El trabajador con DNI " + trabajador.getDni() + " no existe en la base de datos.");
            }

            // Verificar que el email no sea nulo antes de enviar
            if (trabajadorBD.getEmail() == null || trabajadorBD.getEmail().isEmpty()) {
                throw new ActualizarException("El email del trabajador es nulo o vacío.");
            }

            // Generar nueva contraseña aleatoria
            EmailServicio emailService = new EmailServicio();
            String nuevaContrasena = emailService.generateRandomPassword().toString();

            // Enviar correo de recuperación
            String body = "Has solicitado una nueva contraseña:\n" + nuevaContrasena;
            String subject = "Recuperación de contraseña";

            try {
                emailService.sendEmail(trabajadorBD.getEmail(), nuevaContrasena, body, subject);
            } catch (Exception e) {
                LOGGER.warning("Error enviando email: " + e.getMessage());
            }

            // Guardar la nueva contraseña hasheada en la base de datos
            trabajadorBD.setContrasena(Hash.hashText(nuevaContrasena));
            LOGGER.info("Nueva contraseña generada y hasheada correctamente.");

            // Persistir cambios en la base de datos
            em.merge(trabajadorBD);
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException("Error al recuperar la contraseña: " + e.getMessage());
        }
    }

    @Override
    public void actualizarContrasena(Trabajador trabajador) throws ActualizarException {
        try {
            if (trabajador == null || trabajador.getDni() == null) {
                throw new ActualizarException("El objeto Trabajador o su DNI es nulo.");
            }

            // Buscar el trabajador en la base de datos
            Trabajador trabajadorBD = em.find(Trabajador.class, trabajador.getDni());
            if (trabajadorBD == null) {
                throw new ActualizarException("El trabajador con DNI " + trabajador.getDni() + " no existe en la base de datos.");
            }

            // Verificar que el email no sea nulo antes de enviar
            if (trabajadorBD.getEmail() == null || trabajadorBD.getEmail().isEmpty()) {
                throw new ActualizarException("El email del trabajador es nulo o vacío.");
            }

            // Enviar correo de confirmación
            EmailServicio emailService = new EmailServicio();
            String body = "Sr/a " + trabajadorBD.getApellido() + ",\n"
                    + "Hemos realizado el cambio de contraseña solicitado por usted exitosamente!"
                    + " Gracias por seguir usando nuestra app.";
            String subject = "Cambio de contraseña";

            try {
                emailService.sendEmail(trabajadorBD.getEmail(), null, body, subject);
            } catch (Exception e) {
                LOGGER.warning("Error enviando email: " + e.getMessage());
            }

            // Desencriptar y hashear la nueva contraseña
            byte[] passwordBytes = new Asymmetric().decrypt(DatatypeConverter.parseHexBinary(trabajador.getContrasena()));
            trabajadorBD.setContrasena(Hash.hashText(new String(passwordBytes)));

            // Guardar cambios en la base de datos
            em.merge(trabajadorBD);
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException("Error al actualizar la contraseña: " + e.getMessage());
        }
    }

    @Override
    public Trabajador iniciarSesion(String Tradni, String contrasenaTra) throws LeerException {
        Trabajador trabajador;

        try {
            LOGGER.info("Contraseña recibida: " + contrasenaTra);

            String passwdHash;

            // Si la contraseña está en formato hexadecimal (usando una expresión regular para validar)
            if (contrasenaTra.matches("^[0-9A-Fa-f]+$") && contrasenaTra.length() % 2 == 0) {
                LOGGER.info("La contraseña está en formato cifrado, procediendo a desencriptar.");

                // Desencriptar la contraseña
                Asymmetric asymmetric = new Asymmetric();
                byte[] encryptedPassword = DatatypeConverter.parseHexBinary(contrasenaTra);
                byte[] decryptedPassword = asymmetric.decrypt(encryptedPassword);

                // Verificar si la desencriptación fue exitosa
                if (decryptedPassword == null) {
                    throw new LeerException("Error al desencriptar la contraseña.");
                }

                passwdHash = Hash.hashText(new String(decryptedPassword));
            } else {
                // Si no está en formato hexadecimal, asumimos que es una contraseña en texto plano y la hasheamos directamente
                LOGGER.info("La contraseña no está en formato cifrado, se procederá a hashearla directamente.");
                passwdHash = Hash.hashText(contrasenaTra);
            }

            LOGGER.info("Contraseña hasheada: " + passwdHash);

            // Consulta en la base de datos
            trabajador = (Trabajador) em.createNamedQuery("iniciarSesion")
                    .setParameter("Tradni", Tradni)
                    .setParameter("contrasenaTra", passwdHash)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new LeerException("Usuario o contraseña incorrectos.");
        } catch (Exception e) {
            throw new LeerException("Error al iniciar sesión: " + e.getMessage());
        }
        return trabajador;
    }
}
