package org.africa.semicolon.services;

import org.africa.semicolon.data.models.Category;
import org.africa.semicolon.data.models.Product;
import org.africa.semicolon.data.repositories.CategoryRepo;
import org.africa.semicolon.data.repositories.ProductRepo;
import org.africa.semicolon.dtos.requests.*;
import org.africa.semicolon.dtos.responses.AddProductResponse;
import org.africa.semicolon.dtos.responses.CreateCategoryResponse;
import org.africa.semicolon.dtos.responses.DeleteProductResponse;
import org.africa.semicolon.dtos.responses.UpdateProductResponse;
import org.africa.semicolon.exceptions.CategoryNotFoundException;
import org.africa.semicolon.exceptions.ProductNotFoundException;
import org.africa.semicolon.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.africa.semicolon.utils.Mapper.mapProductToResponse;
import static org.africa.semicolon.utils.Mapper.mapRequestToProductResponse;

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

        return mapRequestToProductResponse(product,category);
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

    @Override
    public DeleteProductResponse deleteProduct(String productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        productRepo.deleteById(productId);

        DeleteProductResponse response = new DeleteProductResponse();
        response.setMessage("Product deleted successfully");
        return response;
    }

    @Override
    public List<AddProductResponse> getProductsByCategory(String categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        List<Product> products = productRepo.findAllByCategoryId(categoryId);

        return products.stream()
                .map(product -> Mapper.mapProductToAddProductResponse(product, category.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddProductResponse> searchAndFilterProducts(ProductSearchRequest request) {
        List<Product> products = productRepo.findAll();
        Stream<Product> filtered = products.stream();

        boolean searchedKeywordIsNotEmpty = request.getKeyword() != null;
        if(searchedKeywordIsNotEmpty) filtered = filtered.filter(product ->
                product.getName().toLowerCase().contains(request.getKeyword().toLowerCase()) ||
                product.getDescription().toLowerCase().contains(request.getKeyword().toLowerCase()));

        boolean searchedCategoryIsNotEmpty = request.getCategoryId() != null;
        if(searchedCategoryIsNotEmpty) filtered = filtered.filter(product ->
                request.getCategoryId().equals(product.getCategoryId()));

        boolean priceIsNotEmpty = request.getMinPrice() != null && request.getMaxPrice() != null;
        if (priceIsNotEmpty) filtered = filtered.filter(product ->
                    product.getPrice().compareTo(request.getMinPrice()) >= 0 &&
                    product.getPrice().compareTo(request.getMaxPrice()) <= 0);

        if (request.getMinQuantity() != null) filtered = filtered.filter(product -> product.getQuantity() >= request.getMinQuantity());

        return filtered
                .map(product -> Mapper.mapProductToAddProductResponse(product,""))
                .collect(Collectors.toList());

    }

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        Category savedCategory = categoryRepo.save(category);
        CreateCategoryResponse response = new CreateCategoryResponse();
        response.setId(savedCategory.getId());
        response.setName(savedCategory.getName());
        response.setMessage("Category created successfully");

        return response;

    }


}
