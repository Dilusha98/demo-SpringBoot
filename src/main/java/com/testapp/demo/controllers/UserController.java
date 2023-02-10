package com.testapp.demo.controllers;

import com.testapp.demo.Utill.varList;
import com.testapp.demo.dto.UserDto;
import com.testapp.demo.dto.responseDto;
import com.testapp.demo.repo.UserRepo;
import com.testapp.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private responseDto responsedto;

    @PostMapping(value = "/saveUser")
    public ResponseEntity saveUser(UserDto userDto){

        try{
           String res = userService.SaveUser(userDto);

           if(res.equals("00")){
               responsedto.setCode(varList.RSP_SUCCESS);
               responsedto.setMessage("Success !");
               responsedto.setContent(userDto);
               return new ResponseEntity(responsedto, HttpStatus.ACCEPTED);

           } else if (res.equals("06")) {
               responsedto.setCode(varList.RSP_DUPLICATED);
               responsedto.setMessage("User already registered");
               responsedto.setContent(userDto);
               return new ResponseEntity(responsedto, HttpStatus.BAD_REQUEST);

           }else {
               responsedto.setCode(varList.RSP_FAIL);
               responsedto.setMessage("Error !");
               responsedto.setContent(null);
               return new ResponseEntity(responsedto, HttpStatus.BAD_REQUEST);

           }

        }catch(Exception ex){
            responsedto.setCode(varList.RSP_FAIL);
            responsedto.setMessage(ex.getMessage());
            responsedto.setContent(null);
            return new ResponseEntity(responsedto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity updateUser( UserDto userDto){

        try{
            String res = userService.updateUser(userDto);

            if(res.equals("00")){
                responsedto.setCode(varList.RSP_SUCCESS);
                responsedto.setMessage("Success !");
                responsedto.setContent(userDto);
                return new ResponseEntity(responsedto, HttpStatus.ACCEPTED);

            } else if (res.equals("01")) {
                responsedto.setCode(varList.RSP_NO_DATA_FOUND);
                responsedto.setMessage("User not registered");
                responsedto.setContent(userDto);
                return new ResponseEntity(responsedto, HttpStatus.BAD_REQUEST);

            }else {
                responsedto.setCode(varList.RSP_FAIL);
                responsedto.setMessage("Error !");
                responsedto.setContent(null);
                return new ResponseEntity(responsedto, HttpStatus.BAD_REQUEST);

            }

        }catch(Exception ex){
            responsedto.setCode(varList.RSP_FAIL);
            responsedto.setMessage(ex.getMessage());
            responsedto.setContent(null);
            return new ResponseEntity(responsedto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value = "/getAllUsers")
    public ResponseEntity getAllUsers(){
        try{
            List<UserDto> userDTtoList = userService.getAllUsers();
            responsedto.setCode(varList.RSP_SUCCESS);
            responsedto.setMessage("Success !");
            responsedto.setContent(userDTtoList);
            return new ResponseEntity(responsedto, HttpStatus.ACCEPTED);

        }catch(Exception ex){
            responsedto.setCode(varList.RSP_FAIL);
            responsedto.setMessage(ex.getMessage());
            responsedto.setContent(null);
            return new ResponseEntity(responsedto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/searchuser/{empId}")
    public ResponseEntity searchUsers(@PathVariable int empId){

        try{
            UserDto userDto = userService.searchUsers(empId);
            if(userDto != null ){
                responsedto.setCode(varList.RSP_SUCCESS);
                responsedto.setMessage("Success !");
                responsedto.setContent(userDto);
                return new ResponseEntity(responsedto, HttpStatus.ACCEPTED);

            }else {
                responsedto.setCode(varList.RSP_DUPLICATED);
                responsedto.setMessage("User Not registered");
                responsedto.setContent(null);
                return new ResponseEntity(responsedto, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception ex){
            responsedto.setCode(varList.RSP_FAIL);
            responsedto.setMessage(ex.getMessage());
            responsedto.setContent(null);
            return new ResponseEntity(responsedto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/deleteuser/{empId}")
    public ResponseEntity deleteUser(@PathVariable int empId){

        try{
            String res = userService.deleteUser(empId);
            if(res.equals("00")){
                responsedto.setCode(varList.RSP_SUCCESS);
                responsedto.setMessage("Success !");
                responsedto.setContent(null);
                return new ResponseEntity(responsedto,HttpStatus.ACCEPTED);

            }else {
                responsedto.setCode(varList.RSP_DUPLICATED);
                responsedto.setMessage("User Not registered");
                responsedto.setContent(null);
                return new ResponseEntity(responsedto, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception ex){
            responsedto.setCode(varList.RSP_FAIL);
            responsedto.setMessage(ex.getMessage());
            responsedto.setContent(null);
            return new ResponseEntity(responsedto, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
