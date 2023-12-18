package com.mercadolibre.be_java_hisp_w23_g2.service;

import com.mercadolibre.be_java_hisp_w23_g2.dto.UserDTO;
import com.mercadolibre.be_java_hisp_w23_g2.dto.UserFollowersCountDTO;
import com.mercadolibre.be_java_hisp_w23_g2.dto.UserFollowersDTO;

import java.util.List;

public interface IUserService {
    UserFollowersCountDTO getFollowersCountSeller(int userId);

    List<UserDTO> getAll();

    UserFollowersDTO getFollowersUser(int userId);
}
