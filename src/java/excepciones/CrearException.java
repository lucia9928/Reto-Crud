/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author 2dam
 */
public class CrearException extends Exception{

    public CrearException(String message) {
        super(message);
    }

    public CrearException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
