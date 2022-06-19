package ru.itmo.productboot.service;

import org.springframework.http.HttpStatus;
import ru.itmo.productboot.model.Count;
import ru.itmo.productboot.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    Optional<List<Product>> getProducts();

    Optional<Product> getProduct(Integer id);

    Optional<Product> saveProducts(Product product);

    HttpStatus deleteProducts(Integer id);

    HttpStatus deleteProductsByMeasure(String unit);

    Count countByManufactureId(Integer id);

    Optional<Product> changeProducts(Product product);

    Optional<List<Product>> getDistinctByManufactureCost();
}
