package com.ciclo4.reto4.repository;


import com.ciclo4.reto4.model.Cosmetic;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author Jorge Quesada
 */

public interface CosmeticCrudRepository extends MongoRepository<Cosmetic, String> {
    
    public Optional<Cosmetic> findByReference(String reference);
    
    //Reto 5
    public List<Cosmetic> findByPriceLessThanEqual(double precio);

    //Reto 5
    @Query("{'description':{'$regex':'?0','$options':'i'}}")
    public List<Cosmetic> findByDescriptionLike(String description);
    
    

}
