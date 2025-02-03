/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import eus.tartangalh.crud.create.Cliente;
import eus.tartangalh.crud.crypto.Asymmetric;
import eus.tartangalh.crud.crypto.EmailServicio;
import eus.tartangalh.crud.crypto.Hash;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author melany
 *
 */
@Stateless
public class EJBCliente implements ClienteInterface {

    @PersistenceContext(unitName = "CRUDWebApplicationPU")
    private EntityManager em;

    @Override
    public void crearCliente(Cliente cliente) throws CrearException {
    try {
        // Load the private key using the Asymmetric class
        Asymmetric asymmetric = new Asymmetric();
        
        // Decrypt the password
        byte[] encryptedPassword = DatatypeConverter.parseHexBinary(cliente.getContrasena());
        byte[] decryptedPassword = asymmetric.decrypt(encryptedPassword);

        // Check if decryption was successful
        if (decryptedPassword == null) {
            throw new CrearException("Failed to decrypt the password.");
        }

        // Hash the decrypted password
        String hashedPassword = Hash.hashText(new String(decryptedPassword));
            cliente.setContrasena(Hash.hashText(new String(hashedPassword)));
            em.persist(cliente);
        } catch (Exception e) {
            throw new CrearException(e.getMessage());
        }
    }

    @Override
    public List<Cliente> encontrarTodosCliente() throws LeerException {
        List<Cliente> clientes;
        try {
            clientes = em.createNamedQuery("encontrarTodosLosClientes").getResultList();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return clientes;
    }

    @Override
    public Cliente encontrarClienteId(String id) throws LeerException {
        Cliente cliente;
        try {
            cliente = em.find(Cliente.class, id);
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return cliente;
    }
    
    @Override
    public Cliente buscarCliente(String email) throws LeerException {
        Cliente cliente;
        try {
            cliente = (Cliente) em.createNamedQuery("buscarCliente").setParameter("userEmail", email).getSingleResult();
            LOGGER.info("Cliente encontrado: " + cliente.toString());
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return cliente;
    }

    /**
     *
     * @param cliente
     * @throws BorrarException
     */
    @Override
    public void eliminarCliente(Cliente cliente) throws BorrarException {
        try {
            em.remove(em.merge(cliente));
        } catch (Exception e) {
            throw new BorrarException(e.getMessage());
        }
    }

    @Override
    public void modificarCliente(Cliente cliente) throws ActualizarException {
        try {
            if (!em.contains(cliente)) {
                em.merge(cliente);
            }
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException(e.getMessage());
        }
    }

    @Override
    public List<Cliente> buscarClientesPorFecha(Date fechaInicio, Date fechaFin) throws LeerException {
        try {
            if (fechaInicio == null || fechaFin == null) {
                throw new IllegalArgumentException("Las fechas no pueden ser nulas.");
            }
            return em.createNamedQuery("buscarClientesPorFecha")
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("fechaFin", fechaFin)
                    .getResultList();
        } catch (Exception ex) {
            throw new LeerException("Error al buscar recetas en el rango de fechas: " + ex.getMessage());
        }

    }

    @Override
    public void recuperarContrasena(Cliente cliente) throws ActualizarException {
        try {
            if (!em.contains(cliente)) {
                EmailServicio emailService = new EmailServicio();
                String password = emailService.generateRandomPassword().toString();
                String body = "Has solicitado una nueva contraseña:\n"
                        + password;
                String subject = "Recuperar contraseña";
                emailService.sendEmail(cliente.getEmail(), password, body, subject);

                cliente.setContrasena(Hash.hashText(password));
                LOGGER.info(cliente.getContrasena());
                em.merge(cliente);
            }
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException(e.getMessage());
        }
    }

    @Override
    public void actualizarContrasena(Cliente cliente) throws ActualizarException {
        try {
            if (!em.contains(cliente)) {
                EmailServicio emailService = new EmailServicio();
                String body = "Sr/a " + cliente.getApellido() + ",\n"
                        + "Hemos realizado el cambio de contraseña solicitado por usted exitosamente!"
                        + "Gracias por seguir usando nuestra app";
                String subject = "Cambio de contraseña";
                emailService.sendEmail(cliente.getEmail(), null, body, subject);
                byte[] passwordBytes = new Asymmetric().decrypt(DatatypeConverter.parseHexBinary(cliente.getContrasena()));
                cliente.setContrasena(Hash.hashText(new String(passwordBytes)));
                em.merge(cliente);
            }
            em.flush();
        } catch (Exception e) {
            throw new ActualizarException(e.getMessage());
        }
    }
    
    @Override
    public Cliente iniciarSesion(String id, String passwd) throws LeerException {
        Cliente cliente;

        try {
            LOGGER.info("Contraseña que llega: " + passwd);
            byte[] passwordBytes = new Asymmetric().decrypt(DatatypeConverter.parseHexBinary(passwd));
            LOGGER.info(Hash.hashText(new String(passwordBytes)));
            cliente = (Cliente) em.createNamedQuery("iniciarSesionCli").setParameter("Clidni", id).setParameter("contrasenaCli", Hash.hashText(new String(passwordBytes))).getSingleResult();
        } catch (Exception e) {
            throw new LeerException(e.getMessage());
        }
        return cliente;
    }

}
