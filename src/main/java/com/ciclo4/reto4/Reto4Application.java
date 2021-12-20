package com.ciclo4.reto4;

import com.ciclo4.reto4.repository.CosmeticCrudRepository;
import com.ciclo4.reto4.repository.OrderCrudRepository;
import com.ciclo4.reto4.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class Reto4Application implements CommandLineRunner{
@Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private CosmeticCrudRepository cosmeticCrudRepository;
    @Autowired
    private OrderCrudRepository orderCrudRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Reto4Application.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        userCrudRepository.deleteAll();
        cosmeticCrudRepository.deleteAll();
        orderCrudRepository.deleteAll();

        
        
    }
}
