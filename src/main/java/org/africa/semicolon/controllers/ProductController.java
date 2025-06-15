package org.africa.semicolon.controllers;

import lombok.RequiredArgsConstructor;
import org.africa.semicolon.dtos.requests.AddProductRequest;
import org.africa.semicolon.dtos.requests.CreateCategoryRequest;
import org.africa.semicolon.dtos.requests.DeleteProductRequest;
import org.africa.semicolon.dtos.requests.UpdateProductRequest;
import org.africa.semicolon.dtos.responses.*;
import org.africa.semicolon.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@ControllerAdvice
public class ProductController {

    @Autowired
    private ProductService  productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request){
        AddProductResponse response = productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,response));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        AddProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(new ApiResponse(true, response));
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest request) {
        UpdateProductResponse response = productService.updateProduct(request);
        return ResponseEntity.ok(new ApiResponse(true, response));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct (@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse(true,"Product deleted successfully"));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        List<AddProductResponse> products = productService.listAllProducts();
        return ResponseEntity.ok(new ApiResponse(true, products));
    }
    @PostMapping("/createcategory")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest request){
        try{
            CreateCategoryResponse response = productService.createCategory(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,response));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


}
