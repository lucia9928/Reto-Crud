/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Proveedor;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author markel
 */
@Local
public interface ProveedorInterfaz {

    public void crearProveedor(Proveedor proveedor) throws CrearException;

    public Proveedor encontrarProveedor(Integer id) throws LeerException ;

    public void borrarProveedor(Proveedor proveedor) throws BorrarException;

    public void actualizarProveedor(Proveedor proveedor) throws ActualizarException;

    public List<Proveedor> mostrarTodosProveedores() throws LeerException;

    public List<Proveedor> mostrarsProveedoresFecha(String fecha) throws LeerException;
    
}
