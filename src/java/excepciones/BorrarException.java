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
public class BorrarException extends Exception{

    public BorrarException(String message) {
        super(message);
    }

    public BorrarException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
