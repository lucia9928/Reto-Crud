/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Gestiona;

/**
 *
 * @author markel
 */
public interface GestionaInterfaz {

    public void crearGestion(Gestiona gestiona);

    public void actualizarGestiona(Gestiona gestiona);
    
}
