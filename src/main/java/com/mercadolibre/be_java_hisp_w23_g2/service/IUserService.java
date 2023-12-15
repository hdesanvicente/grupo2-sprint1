package com.mercadolibre.be_java_hisp_w23_g2.service;

import com.mercadolibre.be_java_hisp_w23_g2.dto.UserDTO;
import com.mercadolibre.be_java_hisp_w23_g2.dto.UserFollowersCountDTO;

import java.util.List;

public interface IUserService {
    UserFollowersCountDTO getFollowersCountSeller(int userId);
    List<UserDTO> getAll();

}
