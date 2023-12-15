package com.mercadolibre.be_java_hisp_w23_g2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.be_java_hisp_w23_g2.dto.UserDTO;
import com.mercadolibre.be_java_hisp_w23_g2.entity.User;
import com.mercadolibre.be_java_hisp_w23_g2.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = userRepository.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User aux:users
             ) {
            userDTOS.add(objectMapper.convertValue(aux,UserDTO.class));
        }

        return userDTOS;
    }
}
