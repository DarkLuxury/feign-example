package br.com.luizcsilva.proj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppController {

    private static List < Product > productList = new ArrayList < > ();
    static {
        productList.add(new Product(1, "product-1", 12.0));
        productList.add(new Product(2, "product-2", 34.0));
        productList.add(new Product(3, "product-3", 9.0));
    }

    @GetMapping("/products")
    public ResponseEntity getProducts() {
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/product/{id}")
    public ResponseEntity setProducts(@PathVariable int id) {
        productList.add(new Product(id, "product-x", 12.0));
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProducts(@PathVariable int id) {

        Product product = findProduct(id);
        if (product == null) {
            return ResponseEntity.badRequest()
                    .body("Invalid product Id");
        }

        return ResponseEntity.ok(product);

    }

    private Product findProduct(int id) {
        return productList.stream()
                .filter(user -> user.getId()
                        .equals(id))
                .findFirst()
                .orElse(null);
    }

}
