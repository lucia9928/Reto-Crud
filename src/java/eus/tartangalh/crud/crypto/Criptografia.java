/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.crypto;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */

public class Criptografia {

    private static final Logger LOGGER = Logger.getLogger(Criptografia.class.getName());

    public PrivateKey Asim() {
        byte[] privateKeyBytes;

        try (InputStream keyInputStream = Criptografia.class.getResourceAsStream("/EjemploRSA_Private.key");
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            if (keyInputStream == null) {
                throw new FileNotFoundException("No se encontró el archivo de clave privada en el classpath.");
            }

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = keyInputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            privateKeyBytes = baos.toByteArray();

            // Convertir los bytes en un objeto PrivateKey
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            return keyFactory.generatePrivate(keySpec);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error al leer el archivo de clave privada.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al procesar la clave privada.", ex);
        }

        return null; // Devolver null si hubo un problema
    }
}
