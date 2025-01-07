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
public class LeerException extends Exception{

    public LeerException(String message) {
        super(message);
    }

    public LeerException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
