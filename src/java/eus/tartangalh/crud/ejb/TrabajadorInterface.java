/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.RecetaMedica;
import eus.tartangalh.crud.create.Trabajador;
import java.util.List;

/**
 *
 * @author 2dam
 */
public interface TrabajadorInterface {
     public void crearTrabajador (Trabajador  trabajador);
         public List<Trabajador>encontraTrabajador();
       public Trabajador encontrarTrabajdorId(Long id) ;
           public void eliminarTrabajador(RecetaMedica receta);
     public void modificarTrabajador(RecetaMedica customer);
}
