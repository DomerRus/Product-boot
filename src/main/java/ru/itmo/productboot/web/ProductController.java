package ru.itmo.productboot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itmo.productboot.model.Product;
import ru.itmo.productboot.service.ProductService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
//@CrossOrigin("http://labvm-42-05.itmo-lab.cosm-lab.science")
//@CrossOrigin("localhost:4200")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getProducts() {
        log.info("Я тут");
        Optional<List<Product>> products = productService.getProducts();
        if(products.get().size()>0) {
            return products
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.noContent().build());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        return productService.getProduct(id)
                .map(item -> ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(item))
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product) {
        return productService.saveProducts(product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Product> changeProduct(@Valid @RequestBody Product product) {
        return productService.changeProducts(product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(productService.deleteProducts(id)).build();
    }

    @DeleteMapping(value = "/measure", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> deleteProductByMeasure(@RequestParam(name = "unit") String unit) {
        return ResponseEntity.status(productService.deleteProductsByMeasure(unit)).build();
    }

    @GetMapping(value = "/measure", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getCountByManufacture(@RequestParam(name = "manufactureId") Integer id){
        return ResponseEntity.ok(productService.countByManufactureId(id));
    }

    @GetMapping(value = "/distinct", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getDistinctByManufactureCost(){
        return productService.getDistinctByManufactureCost()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
