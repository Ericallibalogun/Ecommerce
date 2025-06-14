package org.africa.semicolon.services;

import org.africa.semicolon.data.models.Product;
import org.africa.semicolon.dtos.requests.AddProductRequest;
import org.africa.semicolon.dtos.requests.DeleteProductRequest;
import org.africa.semicolon.dtos.requests.ProductSearchRequest;
import org.africa.semicolon.dtos.requests.UpdateProductRequest;
import org.africa.semicolon.dtos.responses.AddProductResponse;
import org.africa.semicolon.dtos.responses.DeleteProductResponse;
import org.africa.semicolon.dtos.responses.UpdateProductResponse;

import java.util.List;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request);
    AddProductResponse getProductById(String id);
    List<AddProductResponse> listAllProducts();
    UpdateProductResponse updateProduct(UpdateProductRequest request);
    DeleteProductResponse deleteProduct( String productId);
    List<AddProductResponse> getProductsByCategory(String categoryId);
    List<AddProductResponse> searchAndFilterProducts(ProductSearchRequest request);
}
