package com.example.jdbcjava;

import entities.*;
import repositories.*;
import scripts.database.DbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static scripts.constants.DbConstants.*;

@SpringBootApplication
public class JdbcJavaApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(JdbcJavaApplication.class, args);

        DbConnection connection = new DbConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        OrderRepository repository = new OrderRepository(connection);
    }

}
