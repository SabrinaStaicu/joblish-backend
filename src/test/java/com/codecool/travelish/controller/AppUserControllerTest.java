package com.codecool.travelish.controller;


import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.model.user.JobPreferences;
import com.codecool.travelish.repository.AppUserRepository;
import com.codecool.travelish.service.AppUserService;
import org.apache.catalina.filters.CorsFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;

//@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
public class AppUserControllerTest {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MockMvc mockMvc;


    AppUser appUser = new AppUser("Osmanthus", "Wine","Bucharest","No experience. Just graduated.","3223112123","https://media.cancan.ro/unsafe/750x750/smart/filters:contrast(5):quality(80)/https://tacataca.prosport.ro/wp-content/uploads/2021/03/dorian-popa1.jpg","andrei_penica@yahoo.com", "password", new JobPreferences(true, List.of("System administator", "Full-stack developer", "Devops Engineer"), List.of("Bucharest ", "Budapest ", "London "), List.of("Full-time", "Part-time")), List.of("Microsoft Office", "HTML", "CSS", "Golang", "Microservices", "COBOL", "Java"));


    @Test
    void getById_validId_responseOk() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",is("Penica")));
    }


    @Test
    void getAll_responseOk() throws Exception {
//        Mockito.when(appUserService.findById(1L)).thenReturn(appUser);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName",is("Penica")));
    }

    @Test
    void getAllUsers_responseOk() throws Exception {
        appUserService.save(appUser);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName",is(appUser.getFirstName())));
        appUserRepository.delete(appUser);
    }
}
