/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Proveedor;
import excepciones.BorrarException;
import excepciones.CrearException;
import java.util.List;

/**
 *
 * @author markel
 */
public interface ProveedorInterfaz {

    public void crearProveedor(Proveedor proveedor) throws CrearException;

    public Proveedor encontrarProveedor(Integer id);

    public void borrarProveedor(Proveedor proveedor) throws BorrarException;

    public void actualizarProveedor(Proveedor proveedor);

    public List<Proveedor> mostrarTodosProveedores();

    public List<Proveedor> mostrarsProveedoresFecha(String fecha);
    
}
