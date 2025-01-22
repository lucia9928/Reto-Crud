/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.crypto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author bayron
 */
public class EmailServicio {

    private static final Logger LOGGER = Logger.getLogger(EmailServicio.class.getName());
    private Symmetric sym = new Symmetric();
    private String transmitter;
    private String emailkey;

    /**
     * Metodo que envia el correo al usuario
     *
     * @param receiver el usuario que recibe el correo
     * @param password la contraseña generada
     * @param body el mensaje en si.
     * @param subject
     */
    public void sendEmail(String receiver, String password, String body, String subject) {
        try {
            Properties properties = new Properties();
            InputStream input = new ByteArrayInputStream(sym.decryptText("abcd*1234"));
            properties.load(input);
            transmitter = properties.getProperty("TRANSMITTER");
            emailkey = properties.getProperty("EMAILKEY");
        } catch (IOException ex) {
            Logger.getLogger(EmailServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        Properties prop = System.getProperties();

        prop.put("mail.smtp.auth", "true");
        //To connect securely to the SMTP server
        prop.put("mail.smtp.starttls.enable", "true");
        //Google SMTP server
        prop.put("mail.smtp.host", "smtp.gmail.com");
        //Google's secure SMTP port
        prop.put("mail.smtp.port", "587");
        //Account mail
        prop.put("mail.smtp.user", transmitter);
        //Account key
        prop.put("mail.smtp.clave", emailkey);

        Session session = Session.getDefaultInstance(prop);
        MimeMessage message = new MimeMessage(session);

        try {
            LOGGER.info("Enviando correo...");
            message.setFrom(new InternetAddress(transmitter));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", transmitter, emailkey);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            LOGGER.severe(me.getMessage());
        }
    }

    /**
     * Generador de contraseñas para los usuarios.
     *
     * @return la contraseña generada para el usuario
     */
    public static StringBuilder generateRandomPassword() {
        LOGGER.info("Generando una nueva contraseña");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$^&*()-_=+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        int length = random.nextInt(4) + 9; // Genera un número aleatorio entre 9 y 12 inclusive

        boolean hasSymbol = false;
        int symbolCount = 0;
        int numberCount = 0;

        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));

            // Asegurarse de que haya al menos un número en la contraseña
            if (Character.isDigit(randomChar) && numberCount == 0) {
                numberCount++;
            }

            // Asegurarse de que haya al menos un símbolo en la contraseña
            if (isSymbol(randomChar)) {
                hasSymbol = true;
            }

            // Asegurarse de que solo se agreguen letras o dígitos
            if (Character.isLetterOrDigit(randomChar)) {
                password.append(randomChar);
            }
        }

        // Si no hay símbolo en la contraseña, agregar uno al final
        if (!hasSymbol) {
            char randomSymbol = characters.charAt(random.nextInt(characters.length()));
            password.setCharAt(random.nextInt(length), randomSymbol);
        }

        return password;
    }

    private static boolean isSymbol(char c) {

        String symbols = "!@#$^&*()-_=+";
        return symbols.indexOf(c) != -1;
    }

}