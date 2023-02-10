package com.testapp.demo.services;

import com.testapp.demo.Utill.varList;
import com.testapp.demo.dto.UserDto;
import com.testapp.demo.entity.UserEntity;
import com.testapp.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.Token;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String SaveUser(UserDto userDto) {

        if (userRepo.existsById(userDto.getEmpId())){
                return varList.RSP_DUPLICATED;
        }else{
            userRepo.save(modelMapper.map(userDto,UserEntity.class));
            return varList.RSP_SUCCESS;
        }

    }

    public String updateUser(UserDto userDto){

        if(userRepo.existsById(userDto.getEmpId())){
            userRepo.save(modelMapper.map(userDto,UserEntity.class));
            return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }

    }

    public List<UserDto> getAllUsers(){

        List<UserEntity> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<ArrayList<UserDto>>(){
        }.getType());
    }

    public UserDto searchUsers(int empId){
        if (userRepo.existsById(empId)) {
            UserEntity userEntity = userRepo.findById(empId).orElse(null);
            return modelMapper.map(userEntity,UserDto.class);
        }else {
            return null;
        }
    }

    public String deleteUser(int empId){

        if (userRepo.existsById(empId)){
            userRepo.deleteById(empId);
            return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }

    }

}
