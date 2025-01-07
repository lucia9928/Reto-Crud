/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eus.tartangalh.crud.ejb;

/**
 *
 * @author 2dam
 */
import eus.tartangalh.crud.create.ProductoFarmaceutico;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

@Local
public interface ProductoFarmaceuticoControladorLocal {

    /**
     * This method creates a new ProductoFarmaceutico in the data store.
     * @param producto The ProductoFarmaceutico entity object containing new product data.
     * @throws CreateException Thrown when any error or exception occurs during creation.
     */
    public void createProducto(ProductoFarmaceutico producto);

    /**
     * This method updates a ProductoFarmaceutico data in the data store.
     * @param producto The ProductoFarmaceutico entity object containing modified product data.
     * @throws UpdateException Thrown when any error or exception occurs during update.
     */
    public void updateProducto(ProductoFarmaceutico producto);

    /**
     * This method removes a ProductoFarmaceutico from the data store.
     * @param producto The ProductoFarmaceutico entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during deletion.
     */
    public void removeProducto(ProductoFarmaceutico producto);

    /**
     * This method obtains a ProductoFarmaceutico from the data store using its id.
     * @param id The id for the product to be retrieved.
     * @return A ProductoFarmaceutico entity object containing product data.
     * @throws ReadException Thrown when any error or exception occurs during reading.
     */
    public ProductoFarmaceutico findProductoById(Integer id);

    /**
     * This method gets a list with all ProductoFarmaceutico entities in the data store.
     * @return A List of ProductoFarmaceutico entity objects.
     * @throws ReadException Thrown when any error or exception occurs during reading.
     */
    public List<ProductoFarmaceutico> findAllProductos();

    /**
     * This method gets a list with all ProductoFarmaceutico entities whose expiration date is before a specific date.
     * @param fechaLimite The date to filter the products.
     * @return A List of ProductoFarmaceutico entity objects.
     * @throws ReadException Thrown when any error or exception occurs during reading.
     */
    public List<ProductoFarmaceutico> findProductosByFechaCaducidadBefore(LocalDate fechaLimite);

    /**
     * This method gets a list of ProductoFarmaceutico entities by name.
     * @param nombre The name of the product.
     * @return A List of ProductoFarmaceutico entity objects matching the name.
     * @throws ReadException Thrown when any error or exception occurs during reading.
     */
    public List<ProductoFarmaceutico> findProductosByNombre(String nombre);
}

