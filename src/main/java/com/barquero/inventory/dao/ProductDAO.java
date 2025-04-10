package com.barquero.inventory.dao;

import java.util.List;

import com.barquero.inventory.model.Product;

public interface ProductDAO {
	void addProduct(Product product);
	List<Product> listProducts();
	void deleteProduct(int id);
	boolean updateProduct(Product product);
}
