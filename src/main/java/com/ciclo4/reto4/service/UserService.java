package com.ciclo4.reto4.service;

import com.ciclo4.reto4.model.User;
import com.ciclo4.reto4.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jorge Quesada
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepositorio;
    
    /**
     * Obtiene todos los usuarios de la coleccion Users.
     * @return 
    */
    public List<User> getAll() {
        return userRepositorio.getAll();
    }
    
    /**
     * Metodo que obtiene los usuarios  por Id de la coleccion Users.
     * @param id
     * @return 
    */
    public Optional<User> getUser(int id) {
        return userRepositorio.getUser(id);
    }
    
    /**
     * Metodo consulta si un correo existe en la coleccion Users.
     * @param email
     * @return 
    */
    public boolean emailExist(String email) {
        return userRepositorio.emailExist(email);
    }

    /**
     * Metodo que retorna email y pasword de la coleccion Users.
     * @param email
     * @param password
     * @return 
    */
    public User autenticateUser(String email, String password) {
        Optional<User> user = userRepositorio.autenticateUser(email, password);

        if (user.isEmpty()) {
            return new User();
        } else {
            return user.get();
        }
    }

    /**
     * Metodo que crea usuarios en la coleccion Users.
     * @param user
     * @return 
    */
    public User create(User user){ 
        //obtiene el maximo id existente ne la coleccion
        Optional<User> userIdMaximo = userRepositorio.lastUserId();
        // Si el id del Usuario que se recibe como parametro es nulo, entonces valida el maximo id
        if (user.getId() == null) {
            //Valida el maximo Id generado, si no hay ninguno aun el primer Id sera 1
            if (userIdMaximo.isEmpty()) {
                user.setId(1);
            }else {
                user.setId(userIdMaximo.get().getId()+ 1);
            }
            return user;
        } else {
            Optional<User> e = userRepositorio.getUser(user.getId());
            if (e.isEmpty()) {
                if (emailExist(user.getEmail()) == false) {
                    return userRepositorio.create(user);
                } else {
                    return user;
                }
            } else {
                return user;
            }
        }
    }

    /**
     * Metodo que actualiza usuarios en la coleccion Users.
     * @param user
     * @return 
    */
    public User update(User user) {

        if (user.getId() != null) {
            Optional<User> userDb = userRepositorio.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification() != null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userDb.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userDb.get().setZone(user.getZone());
                }
                userRepositorio.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    /**
     * Metodo que elimina usuarios en la coleccion Users.
     * @param userId
     * @return 
    */
    public boolean delete(int userId) {
        /*Optional<User> usuario = getUser(userId);
        
        if (usuario.isEmpty()){
            return false;
        }else{
            userRepositorio.delete(usuario.get());
            return true;
        }
        */
        Boolean aBoolean = getUser(userId).map(user -> {
            userRepositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    /**
     * Metodo Get que consulta usuarios  por mes de cumpleaños de la coleccion Users.
     * @param monthBirthtDay
     * @return 
    */
    //Reto 5
    public List<User> birthtDayList(String monthBirthtDay) {
        return userRepositorio.birthtDayList(monthBirthtDay);
    }
}
