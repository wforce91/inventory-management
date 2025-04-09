package com.barquero.inventory;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.barquero.inventory.dao.ProductDAO;
import com.barquero.inventory.model.Product;

public class TestApp {
    public static void main(String[] args) {
        // Load Spring context from applicationContext.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        // Run schema.sql manually
        jdbcTemplate.execute("CREATE TABLE products (" +
                          "id INT AUTO_INCREMENT PRIMARY KEY, " +
                          "name VARCHAR(255) NOT NULL, " +
                          "quantity INT NOT NULL)");

        // Get the DAO bean
        ProductDAO productDAO = context.getBean(ProductDAO.class);

        // Add a product
        Product p = new Product();
        p.setName("Computer");
        p.setQuantity(42);
        productDAO.addProduct(p);
        
        Product p2 = new Product();
        p2.setName("Mouse");
        p2.setQuantity(30);
        productDAO.addProduct(p2);
        
        Product p3 = new Product();
        p3.setName("Keyboards");
        p3.setQuantity(15);
        productDAO.addProduct(p3);
        

        // List all products
        List<Product> products = productDAO.listProducts();
        for (Product prod : products) {
            System.out.println("ID: " + prod.getId() + ", Name: " + prod.getName() + ", Quantity: " + prod.getQuantity());
        	prod.toString();
        }

        // Delete product with ID 1 (if it exists)
        productDAO.deleteProduct(1);
        
        //Check list after deleting one product
        List<Product> productsList = productDAO.listProducts();
        for (Product prod : productsList) {
            System.out.println("ID: " + prod.getId() + ", Name: " + prod.getName() + ", Quantity: " + prod.getQuantity());
        	prod.toString();
        }
    }
}
