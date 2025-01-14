/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.Trabajador;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author melany
 */
@Local
public interface TrabajadorInterface {
     public void crearTrabajador (Trabajador  trabajador)throws CrearException;
      public List<Trabajador>encontraTodosLosTrabajadores()throws LeerException;
      public Trabajador encontrarTrabajdorId(String id) throws LeerException;
      public void eliminarTrabajador(Trabajador trabajador) throws BorrarException;
     public void modificarTrabajador(Trabajador trabajador) throws ActualizarException;
}
