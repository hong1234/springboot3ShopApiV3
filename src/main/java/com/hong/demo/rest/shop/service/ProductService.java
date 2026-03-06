package com.hong.demo.rest.shop.service;

import java.util.UUID;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.transaction.annotation.Transactional;

import com.hong.demo.rest.shop.repository.*;
import com.hong.demo.rest.shop.domain.*;
// import com.hong.demo.rest.shop.domain.CategoryEntity;
// import com.hong.demo.rest.shop.domain.Product;
// import com.hong.demo.rest.shop.domain.ProductEntity;


@RequiredArgsConstructor
// @Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository; 
    private final CategoryRepository categoryRepository;

    public Product addProduct(ProductDTO prod) throws ServiceException {
        CategoryEntity cat = getCategoryById(prod.getCategoryId());

        ProductEntity newProd = new ProductEntity();
        newProd.setTitle(prod.getTitle());
        newProd.setDescription(prod.getDescription());
        newProd.setSupplier(prod.getSupplier());
        newProd.setSearchkeys(prod.getSearchkeys());
        newProd.setImage(prod.getImage());
        newProd.setUnitPrice(prod.getUnitPrice());
        newProd.setCategory(cat);

        newProd = productRepository.save(newProd);
        return productRecord(newProd);
    }

    public Product updateProduct(String productId, ProductDTO prod){
        ProductEntity curProd = getProductById(productId);
        curProd.setTitle(prod.getTitle());
        curProd.setDescription(prod.getDescription());
        curProd.setSupplier(prod.getSupplier());
        curProd.setSearchkeys(prod.getSearchkeys());
        curProd.setImage(prod.getImage());
        curProd.setUnitPrice(prod.getUnitPrice());
        curProd = productRepository.save(curProd);
        return productRecord(curProd);
    }

    public void deleteProduct(String productId){
        productRepository.deleteById(UUID.fromString(productId));
    }

    public List<Product> searchByTitle(String title) throws ServiceException {
        return productRepository.searchByTitle(title.toLowerCase())
            .stream().map(prod -> productRecord(prod)).toList();
    }

    public List<Product> productsOfCategoryId(String categoryId) {
        return productRepository.productsOfCategoryId(UUID.fromString(categoryId))
        .stream().map(prod -> productRecord(prod)).toList();
    }

    public List<Product> getAllProducts() throws ServiceException {
        return productRepository.findAll()
            .stream().map(prod -> productRecord(prod)).toList();
    }

    public Product getProduct(String productId) throws ServiceException {
        ProductEntity prod = getProductById(productId); 
        return productRecord(prod);
    }

    public ProductEntity getProductById(String productId) {
        ProductEntity prod = productRepository.findById(UUID.fromString(productId)).get();
        return prod;
    }

    public CategoryEntity getCategoryById(String categoryId) {
        CategoryEntity cat = categoryRepository.findById(UUID.fromString(categoryId)).get();
        return cat;
    }

    public Product productRecord(ProductEntity prod) {
        return new Product(
            prod.getId().toString(), 
            prod.getTitle(),
            prod.getDescription(),
            prod.getSupplier(),
            prod.getSearchkeys(),
            prod.getImage(),
            prod.getUnitPrice()
        );
    }

}
