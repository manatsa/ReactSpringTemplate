package org.zimnat.lionloader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zimnat.lionloader.aop.annotation.Auditor;
import org.zimnat.lionloader.business.domain.Product;
import org.zimnat.lionloader.business.domain.User;
import org.zimnat.lionloader.business.domain.dto.ProductDTO;
import org.zimnat.lionloader.business.services.ProductService;
import org.zimnat.lionloader.business.services.UserService;

/**
 * @author :: codemaster
 * created on :: 2/4/2023
 */

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Auditor
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }


    @Auditor
    @PostMapping("/")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO){
        Product product= new Product();
        User user=userService.getCurrentUser();
        try{
            product =productService.save(product, user);
            return  ResponseEntity.ok(productService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @Auditor
    @PutMapping("/{id}")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") String id){

        try{
            User currentUser=userService.getCurrentUser();
            Product product=productService.update(id, productDTO,currentUser);
            return  ResponseEntity.ok(productService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
