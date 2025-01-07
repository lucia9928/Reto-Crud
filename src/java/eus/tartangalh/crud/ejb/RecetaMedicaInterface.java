/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

import eus.tartangalh.crud.create.RecetaMedica;
import java.util.List;

/**
 *
 * @author 2dam
 */
public interface RecetaMedicaInterface {
    public void crearRecetaMedica (RecetaMedica  account);
    public List<RecetaMedica> findAllCustomers();
    public RecetaMedica findCustomer(Long id);
    public void eliminarRecetaMedica(RecetaMedica receta);
    public void modificarRecetaMedica(RecetaMedica customer);
}
