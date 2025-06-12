package org.africa.semicolon.services;

import org.africa.semicolon.data.models.Category;
import org.africa.semicolon.data.models.Product;
import org.africa.semicolon.data.repositories.CategoryRepo;
import org.africa.semicolon.data.repositories.ProductRepo;
import org.africa.semicolon.dtos.requests.AddProductRequest;
import org.africa.semicolon.dtos.requests.UpdateProductRequest;
import org.africa.semicolon.dtos.responses.AddProductResponse;
import org.africa.semicolon.dtos.responses.UpdateProductResponse;
import org.africa.semicolon.exceptions.CategoryNotFoundException;
import org.africa.semicolon.exceptions.ProductNotFoundException;
import org.africa.semicolon.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.africa.semicolon.utils.Mapper.mapProductToResponse;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        Product product =  Mapper.mapRequestToProduct(request);
        Product saved = productRepo.save(product);
        Category category = categoryRepo.findById(product.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("Category not found"));

        return mapProductToResponse(product,category);
    }

    @Override
    public AddProductResponse getProductById(String id) {
       Product product = productRepo.findById(id)
               .orElseThrow(() ->new ProductNotFoundException("Product not found"));
        Category category = categoryRepo.findById(product.getCategoryId())
                .orElseThrow(() ->new CategoryNotFoundException("Category not found"));

        return Mapper.toAddProductResponse(product,category);
    }

    @Override
    public List<AddProductResponse> listAllProducts() {
        List<Product> products = productRepo.findAll();

        return products.stream().map(product -> {
            Category category = categoryRepo.findById(product.getCategoryId()).orElse(null);
            return mapProductToResponse(product,category);
        }).collect(Collectors.toList());
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest request) {
        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        Mapper.mapUpdatedProductToResponse(product,request);

        Product updatedProduct = productRepo.save(product);

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(()->new CategoryNotFoundException("Category not found"));

        return Mapper.toUpdateProductResponse(updatedProduct,category);
    }


}
