/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Proveedor;
import javax.ejb.Local;

/**
 *
 * @author markel
 */
@Local
public interface ProveedorInterfaz {

    public void crearProveedor(Proveedor proveedor);
    
}
