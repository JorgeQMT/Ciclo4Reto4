package com.ciclo4.reto4.repository;

import com.ciclo4.reto4.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jorge Quesada
 */
@Repository
public class UserRepository {
    @Autowired
    private UserCrudRepository userCrudRepository;

    /**
     * Obtiene todos los usuarios de la coleccion Users.
     * @return 
    */
    public List<User> getAll() {
        return userCrudRepository.findAll();
    }

    /**
     * Metodo que obtiene los usuarios  por Id de la coleccion Users.
     * @param id
     * @return
     * */
    public Optional<User> getUser(int id) {
        return userCrudRepository.findById(id);
    }

    /**
     * Crea usuarios en la coleccion Users. 
     * @param user
     * @return 
    */
    public User create(User user) {
        return userCrudRepository.save(user);
    }

    /**
     * Actualiza usuarios en la coleccion Users.  
     * @param user
     * @return 
    */
    public User update(User user) {
        return userCrudRepository.save(user);
    }

    /**
     * Elimina usuarios en la coleccion Users. 
     * @param user
    */
    public void delete(User user) {
        userCrudRepository.delete(user);
        /*
        Boolean aBoolean = getUser(userId).map(user -> {
            repositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;*/
    }
    
    /**
     * Metodo consulta si un correo existe en la coleccion Users.
     * @param email
     * @return 
    */
    public boolean emailExist(String email) {
        Optional <User> user = userCrudRepository.findByEmail(email);
        return !user.isEmpty();
    }

    /**
     * Autenticar los usuarios en la coleccion Users.
     * @param email
     * @param password
     * @return 
    */
    public Optional <User> autenticateUser(String email, String password) {
        return userCrudRepository.findByEmailAndPassword(email, password);

    }
    
    /**
     * Consulta el ulrimo  usuario en la coleccion Users. 
     * @return 
    */
    public Optional<User> lastUserId(){
        return userCrudRepository.findTopByOrderByIdDesc();
    }
    /**
     * Crea usuarios en la coleccion Users. 
     * @param monthBirthtDay
     * @return 
     *  Reto 5
    */
    public List<User> birthtDayList(String monthBirthtDay) {
        return userCrudRepository.findByMonthBirthtDay(monthBirthtDay);
    }
}
