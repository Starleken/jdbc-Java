package com.example.jdbcjava;

import entities.*;
import repositories.*;
import scripts.database.DbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import scripts.primaryKeys.OrderItemPrimaryKey;

import static scripts.constants.DbConstants.*;

@SpringBootApplication
public class JdbcJavaApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(JdbcJavaApplication.class, args);

        DbConnection connection = new DbConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        OrderItemRepository repository = new OrderItemRepository(connection);

        OrderItem orderItem = new OrderItem(10,3,10,10.2);
        orderItem.setQuantity(12);
        repository.delete(new OrderItemPrimaryKey(orderItem.getOrderId(), orderItem.getProductId()));
    }

}
