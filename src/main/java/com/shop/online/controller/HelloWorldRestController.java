package com.shop.online.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shop.online.exception.UserDetailExist;
import com.shop.online.exception.UserDetailNotFound;
import com.shop.online.model.ScreenError;
import com.shop.online.model.User;
import com.shop.online.model.UserDetail;
import com.shop.online.service.UserDetailService;
import com.shop.online.service.UserService;
 
@RestController
public class HelloWorldRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
    @Autowired
    UserDetailService userDetailService;
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDetail>> listAllUsers() {
        List<UserDetail> userdetails = userDetailService.findAll();
        if(userdetails.isEmpty()){
            return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserDetail>>(userdetails, HttpStatus.OK);
    }
 
 //--------------------------------Retrive all Fname---------------------------------
    
  
    
    @RequestMapping(value = "/user/fname/", method = RequestMethod.GET)
    public ResponseEntity<List<String>> fetchAllFname() {
        List<String> fnames = userDetailService.findAllUnicqueFname();
        return new ResponseEntity<List<String>>(fnames, HttpStatus.OK);
    }
 
    
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDetail userdetail,    UriComponentsBuilder ucBuilder) throws UserDetailExist {
        System.out.println("Creating User " + userdetail.getFName());
 
        if (userDetailService.isUserExist(userdetail)) {
            System.out.println("A User with name " + userdetail.getFName() + " already exist");
            ScreenError error=new ScreenError();
            error.setErrorMessage("User already exist");
            return new ResponseEntity<ScreenError>(error,HttpStatus.CONFLICT);
        }
         userDetailService.create(userdetail);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(userdetail.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDetail> updateUser(@PathVariable("id") int id, @RequestBody UserDetail userdetail) throws UserDetailNotFound {
        System.out.println("Updating User " + id);
        
         
        UserDetail currentUser=userDetailService.update(userdetail);
        return new ResponseEntity<UserDetail>(currentUser, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) throws UserDetailNotFound {
        System.out.println("Fetching & Deleting User with id " + id);
 
        UserDetail userdetail = userDetailService.findById(id);
        if (userdetail == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userDetailService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
}