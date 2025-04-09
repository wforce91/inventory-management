package com.barquero.inventory.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.barquero.inventory.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addProduct(Product product) {
		String sql = "INSERT INTO products (name, quantity) VALUES (?,?)";
		jdbcTemplate.update(sql, product.getName(), product.getQuantity());
	}

	@Override
	public List<Product> listProducts() {
		String sql = "SELECT * FROM products";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setQuantity(rs.getInt("quantity"));
            return p;
        });
	}

	@Override
	public void deleteProduct(int id) {
		String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
	}

}
