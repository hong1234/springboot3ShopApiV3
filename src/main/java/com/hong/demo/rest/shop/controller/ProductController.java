package com.hong.demo.rest.shop.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.hong.demo.rest.shop.domain.*;

// import com.hong.demo.rest.shop.service.BookService;
// import com.hong.demo.rest.shop.service.ServiceException;  
import com.hong.demo.rest.shop.service.*;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController { 

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Valid ProductDTO prod) throws ServiceException {
        return productService.addProduct(prod);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") String productId, 
                                 @RequestBody @Valid ProductDTO prod) throws ServiceException {
        return productService.updateProduct(productId, prod); 
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") String productId) throws ServiceException {
        productService.deleteProduct(productId);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> searchProductsByTitle(@RequestParam String title) throws ServiceException {  
        return productService.searchByTitle(title);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable String productId) throws ServiceException {
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> productsOfCategoryId(@PathVariable String categoryId) throws ServiceException {
        return productService.productsOfCategoryId(categoryId);
    }

    @GetMapping
    public List<Product> getAllProducts() throws ServiceException {
        return productService.getAllProducts();
    }

    // @PostMapping
    // public Product addProduct2(@RequestBody @Valid ProductDTO prod, BindingResult result) throws ServiceException {
    //     if(result.hasErrors())
    //         throw new ValidationException(createErrorString(result));
    //     return productService.addProduct(prod);
    //     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId()).toUri();
    //     return ResponseEntity.created(location).body(savedBook);
    // }
    
}

