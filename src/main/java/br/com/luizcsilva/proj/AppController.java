package br.com.luizcsilva.proj;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppController {

    private static List < Product > productList = new ArrayList <> ();

    @PostMapping("/product/")
    public ResponseEntity setProduct(@RequestBody Product product) {
        if (findProduct(product.getId()) == null) {
            productList.add(product);
            return ResponseEntity.ok(productList);
        }
        return ResponseEntity.badRequest().body("This product id already exists");
    }

    @GetMapping(value = "/product/{id}" )
    public ResponseEntity getProducts(@PathVariable(value = "id") int id) {

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

    @GetMapping("/products")
    public ResponseEntity <List<Product>> getProducts() {
        return ResponseEntity.ok(productList);
    }

}
