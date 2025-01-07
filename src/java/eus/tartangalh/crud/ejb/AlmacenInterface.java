/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Almacen;

/**
 *
 * @author Andoni
 */
public interface AlmacenInterface {

    /**
     * Este método crea un nuevo almacén en el sistema.
     *
     * @param almacen El objeto de la entidad Almacen que contiene los datos del
     * nuevo almacén.
     * @throws CreateException Lanzada cuando ocurre un error o excepción
     * durante la creación.
     */
    public void crearAlmacen(Almacen almacen);

    /**
     * Este método actualiza los datos de un almacén existente en el sistema.
     *
     * @param almacen El objeto de la entidad Almacen que contiene los datos
     * modificados del almacén.
     * @throws UpdateException Lanzada cuando ocurre un error o excepción
     * durante la actualización.
     */
    public void actualizarAlmacen(Almacen almacen);

    /**
     * Este método elimina un almacén del sistema.
     *
     * @param almacen El objeto de la entidad Almacen que se desea eliminar.
     * @throws DeleteException Lanzada cuando ocurre un error o excepción
     * durante la eliminación.
     */
    public void borrarAlmacen(Almacen almacen);
}
