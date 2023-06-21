package com.project.teststudent.studentmanger.controllers;

import com.project.teststudent.studentmanger.entitites.User;
import com.project.teststudent.studentmanger.entitites.response.BaseReponse;
import com.project.teststudent.studentmanger.entitites.response.SuccessResponseObject;
import com.project.teststudent.studentmanger.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v0/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(path = {"add"}, method = {RequestMethod.POST})
    public ResponseEntity<BaseReponse> addUser (@RequestBody User user) {
        try{
            SuccessResponseObject<User> successObject = new SuccessResponseObject<>();
            User createdUser = userRepository.save(user);
            successObject.setCode("200");
            successObject.setMsg("Success");
            successObject.setResponse(createdUser);
            logger.info("Done");
            return new ResponseEntity<>(successObject, HttpStatus.CREATED);
        }
        catch (Exception exp) {
            BaseReponse response = new BaseReponse();
            response.setMsg("Failed: "+exp.getMessage());
            response.setCode("400");
            logger.error(exp.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "get/all", method = {RequestMethod.GET})
    public ResponseEntity<BaseReponse> getAllUsers () {
        try {
            SuccessResponseObject<List<User>> successObject = new SuccessResponseObject<>();
            List<User> allUsers = userRepository.findAll();
            successObject.setCode("200");
            successObject.setMsg("Success");
            successObject.setResponse(allUsers);
            logger.info("Done");
            return new ResponseEntity<>(successObject, HttpStatus.CREATED);
        }
        catch (Exception exp) {
            BaseReponse response = new BaseReponse();
            response.setMsg("Failed: "+exp.getMessage());
            response.setCode("400");
            logger.error(exp.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "get/{id}", method = {RequestMethod.GET})
    public  ResponseEntity<BaseReponse> getUserById (@PathVariable("id") String id) {
        Long userId = Long.parseLong(id);
        try {
            SuccessResponseObject<User> successObject = new SuccessResponseObject<>();
            Optional<User> optionalUser = userRepository.findById(userId);
            successObject.setCode("200");
            successObject.setMsg("Success");
            if(optionalUser.isPresent()){
                successObject.setResponse(optionalUser.get());
            }
            logger.info("Done");
            return new ResponseEntity<>(successObject, HttpStatus.CREATED);
        }
        catch (Exception exp) {
            BaseReponse response = new BaseReponse();
            response.setMsg("Failed: "+exp.getMessage());
            response.setCode("400");
            logger.error(exp.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = {"update"}, method = {RequestMethod.PUT})
    public ResponseEntity<BaseReponse> updateUser (@RequestBody User user) {
        try{
            SuccessResponseObject<User> successObject = new SuccessResponseObject<>();
            User createdUser = userRepository.save(user);
            successObject.setCode("200");
            successObject.setMsg("Success");
            successObject.setResponse(createdUser);
            logger.info("Done");
            return new ResponseEntity<>(successObject, HttpStatus.CREATED);
        }
        catch (Exception exp) {
            BaseReponse response = new BaseReponse();
            response.setMsg("Failed: "+exp.getMessage());
            response.setCode("400");
            logger.error(exp.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "delete/{id}", method = {RequestMethod.GET})
    public  ResponseEntity<BaseReponse> deleteUserById (@PathVariable("id") String id) {
        Long userId = Long.parseLong(id);
        try {
            SuccessResponseObject<User> successObject = new SuccessResponseObject<>();
            Optional<User> optionalUser = userRepository.findById(userId);
            successObject.setCode("200");
            if(optionalUser.isPresent()){
                userRepository.delete(optionalUser.get());
                successObject.setMsg("Successfully Deleted User with Id: "+userId);
                successObject.setResponse(optionalUser.get());
            }
            else {
                successObject.setMsg("User doesn't exist");
            }
            logger.info("Done");
            return new ResponseEntity<>(successObject, HttpStatus.CREATED);
        }
        catch (Exception exp) {
            BaseReponse response = new BaseReponse();
            response.setMsg("Failed: "+exp.getMessage());
            response.setCode("400");
            logger.error(exp.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }



}
