/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.ProductoFarmaceutico;
import eus.tartangalh.crud.create.RecetaMedica;
import excepciones.ActualizarException;
import excepciones.BorrarException;
import excepciones.CrearException;
import excepciones.LeerException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author melany
 * 
 */
@Local
public interface RecetaMedicaInterface {
    public void crearRecetaMedica(RecetaMedica  receta)throws CrearException;
    public List<RecetaMedica> encontrarTodasLasRecetas() throws LeerException;
    public RecetaMedica encontrarRecetasPorId(Integer id) throws LeerException;
    public void eliminarRecetaMedica(RecetaMedica receta) throws BorrarException;
    public void modificarRecetaMedica(RecetaMedica receta) throws ActualizarException;
    public List<RecetaMedica> encontrarRecetasPorFecha(Date fechaInicio, Date fechaFin) throws LeerException;
     public List<ProductoFarmaceutico> obtenerProductosPorReceta (Integer recetaid)throws LeerException;
}
