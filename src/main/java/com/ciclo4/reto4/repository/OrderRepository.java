package com.ciclo4.reto4.repository;

import com.ciclo4.reto4.model.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jorge Quesada
 */
@Repository
public class OrderRepository {
    @Autowired
    private OrderCrudRepository orderCrudRepository;

    @Autowired
    private MongoTemplate mongoTamplate;
    
    public List<Order> getAll() {
        return orderCrudRepository.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderCrudRepository.findById(id);
    }

    public Order create(Order order) {
        return orderCrudRepository.save(order);
    }

    public Order update(Order order) {
        return orderCrudRepository.save(order);
    }

    public void delete(Order order) {
        orderCrudRepository.delete(order);
        /*
        Boolean aBoolean = getUser(userId).map(user -> {
            repositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;*/
    }
    
    public Optional<Order> lastOrderId(){
        return orderCrudRepository.findTopByOrderByIdDesc();
    }
    
    public List<Order> finByZone(String zona) {
        return orderCrudRepository.findByZone(zona);
    }
    
    //Metodos del Reto 4 
    //Ordenes de un asesor
    public List<Order> ordersSalesManByID(Integer id){
        
        Query query = new Query();
        
        Criteria criterio = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);
        
        List<Order> orders = mongoTamplate.find(query, Order.class);
        return orders;
    }
    
    //Ordenes de un asesor por estado 
    public List<Order> ordersSalesManByState(String state, Integer id){
        
        Query query = new Query();
        
        Criteria criterio = Criteria.where("salesMan.id").is(id).and("status").is(state);
        query.addCriteria(criterio);
        
        List<Order> orders = mongoTamplate.find(query, Order.class);
        return orders;
    }
    
    //Ordenes de un asesor por fecha 
    public List<Order> ordersSalesManByDate(String dateStr, Integer id){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd");
        Query query = new Query();
        
        Criteria criterio = Criteria.where("registerDay")
                                    .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                                    .lt(LocalDate.parse(dateStr, dtf).plusDays(2).atStartOfDay())
                                    .and("salesMan.id").is(id);
        query.addCriteria(criterio);
        
        List<Order> orders = mongoTamplate.find(query, Order.class);
        return orders;
    }

}
