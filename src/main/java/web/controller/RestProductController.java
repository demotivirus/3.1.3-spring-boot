package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.Product;
import web.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RestProductController {
    @Autowired
    ProductService service;

    @GetMapping("products")
    public List<Product> allProducts(){
        return service.listAll();
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = service.get(id);
        if (product == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("products")
    public void addProduct(@RequestBody Product product){
        service.save(product);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productDetail, @PathVariable("id") Long id){
        Product product = service.get(id);

        if (product == null)
            return ResponseEntity.notFound().build();

        service.save(productDetail);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("products/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
