package com.mercadolibre.be_java_hisp_w23_g2.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.be_java_hisp_w23_g2.dto.ProductBasicDTO;
import com.mercadolibre.be_java_hisp_w23_g2.dto.requests.PostDTO;
import com.mercadolibre.be_java_hisp_w23_g2.repository.UserRepository;
import com.mercadolibre.be_java_hisp_w23_g2.service.ProductService;
import java.io.IOException;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  ProductService service;

  @BeforeEach
  void setUp() throws IOException {
    service = new ProductService(new UserRepository());
  }

  @Test
  void addPost() throws Exception {
    PostDTO postDTO = new PostDTO(1, LocalDate.now(),
        new ProductBasicDTO(1, "Reloj", "Accesorios", "Swatch", "Gris", "Reloj comodo"),
        1, 500.0);
    ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false)
        .writer();

    String payloadJson = writer.writeValueAsString(postDTO);
    mockMvc.perform(post("/products/post").contentType("application/json").content(payloadJson))
        .andDo(print())
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Publication successfully added."));

  }

  @Test
  void addPostAlreadyExists() throws Exception {
    PostDTO postDTO = new PostDTO(1, LocalDate.now(),
        new ProductBasicDTO(1, "Reloj", "Accesorios", "Swatch", "Gris", "Reloj comodo"),
        1, 500.0);

    service.addPost(postDTO);
    ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false)
        .writer();

    String payloadJson = writer.writeValueAsString(postDTO);
    mockMvc.perform(post("/products/post").contentType("application/json").content(payloadJson))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("The product id already exists."));

  }

}
