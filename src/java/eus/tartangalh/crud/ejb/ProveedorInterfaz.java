/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Proveedor;
import java.util.List;

/**
 *
 * @author markel
 */
public interface ProveedorInterfaz {

    public void crearProveedor(Proveedor proveedor);

    public Proveedor encontrarProveedor(Integer id);

    public void borrarProveedor(Integer id);

    public void actualizarProveedor(Integer id, Proveedor proveedor);

    public List<Proveedor> mostrarTodosProveedores();
    
}
