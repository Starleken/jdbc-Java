package com.example.jdbcjava;

import Entities.Shipper;
import Repositories.ShipperRepository;
import Scripts.Database.DbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static Scripts.Constants.DbConstants.*;

@SpringBootApplication
public class JdbcJavaApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(JdbcJavaApplication.class, args);

        DbConnection connection = new DbConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        ShipperRepository repository = new ShipperRepository(connection);

        Shipper shipper = new Shipper(1, "casc");

        repository.create(shipper);
        repository.findAll();
    }

}
