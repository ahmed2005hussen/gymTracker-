package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.service.interfaces.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {


  private final UserService userService ;

    UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/findByGmail/{gmail}")
    public ResponseEntity<User> findByGmail(@PathVariable String gmail){

        try {
            User user = userService.findByGmail(gmail)
                    .orElseThrow(() -> new RuntimeException("not found"));
            return ResponseEntity.ok(user);
        }catch(RuntimeException e ){

            return ResponseEntity.notFound().build() ;
        }

    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll() ;
    }

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }


}
