package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@PathVariable si se va a pasar los datos por la url
//@RequestBody si se va a pasar los datos por el body
@RestController
@RequestMapping("/products")
public class ProductController {
    //Inyectar el servicio
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation(value = "Get all products")
    @ApiResponse(code = 200, message = "Successful operation")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(
                productService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "Id of the product", required = true, example = "1")
                                                  @PathVariable("id") Long productId){
        return productService.getProduct(productId
        ).map(
                product -> new ResponseEntity<>(product, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/category/{categoryId}")
    @ApiOperation(value = "Get products by category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.getByCategory(categoryId
        ).map(
                products -> new ResponseEntity<>(products, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping()
    @ApiOperation(value = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Product created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(
                productService.save(product),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity delete(@PathVariable("id") Long productId){
        if (productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
