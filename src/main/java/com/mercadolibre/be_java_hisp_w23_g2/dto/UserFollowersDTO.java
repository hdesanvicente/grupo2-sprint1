package com.mercadolibre.be_java_hisp_w23_g2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowersDTO {
    @JsonProperty("user_id")
    private int id;
    @JsonProperty("user_name")
    private String userName;
    private List<UserDTO> followed;
}
