package com.barquero.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barquero.inventory.dao.ProductDAO;
import com.barquero.inventory.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@GetMapping
	public List<Product> getAllProdcts(){
		return productDAO.listProducts();
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> addProduct(@RequestBody Product product){
		productDAO.addProduct(product);
		return ResponseEntity.ok("Product added");
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productDAO.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }
}
