package com.tutorial.apidemo.SpringBootTutorial.database;

import com.tutorial.apidemo.SpringBootTutorial.models.Product;
import com.tutorial.apidemo.SpringBootTutorial.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Now connect with mysql using JPA
/*
   docker run -d --rm --name mysql-spring-boot-tutorial -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_USER=quocanh -e MYSQL_PASSWORD=123456 -e MYSQL_DATABASE=test_db -p 3309:3306 -v mysql-spring-boot-tutorial-volume:/var/lib/mysql mysql:latest

   mysql -h localhost -P 3309 --protocol=tcp -u quocanh -p
 */



@Configuration
public class Database {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product(1L, "Laptop ASUS", 2015, 9790000.0, "https://th.bing.com/th/id/OIP.E42WjiEqfBJBEryFom6JFQHaHa?rs=1&pid=ImgDetMain");
                Product productB = new Product(2L, "Laptop Dell Latitude", 2021, 14860000.0, "https://laptopmedia.com/wp-content/uploads/2021/04/delllatitude135320featured.jpg");
                Product productC = new Product(3L, "Laptop Dell Gaming", 2023, 18900000.0, "https://th.bing.com/th/id/R.cd9e113c88458c03f0d3892b38ff7eb5?rik=AMsXI1Jp53hPPA&pid=ImgRaw&r=0");
                logger.info("insert data: " + productRepository.save(productA));
                logger.info("insert data: " + productRepository.save(productB));
                logger.info("insert data: " + productRepository.save(productC));
            }
        };
    }
}

