package edu.pro.crypto.testtaskcrypto.controller;
/*
  @author   george
  @project   test-task-crypto
  @class  UserController
  @version  1.0.0 
  @since 13.10.23 - 11.33
*/

import edu.pro.crypto.testtaskcrypto.model.User;
import edu.pro.crypto.testtaskcrypto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping
    List<User> getAll() {
        return service.getAll();
    }

    @RequestMapping("/{id}")
    User getUser(@PathVariable("id") String id) {
        return service.get(id);
    }

    @PostMapping()
    User createUser(@RequestBody User user) throws Exception {
        return service.create(user);
    }

    @PutMapping()
    User updateUser(@RequestBody User user){
        return service.update(user);
    }

    @DeleteMapping()
    void deleteUser(String id){
         service.delete(id);
    }






}
