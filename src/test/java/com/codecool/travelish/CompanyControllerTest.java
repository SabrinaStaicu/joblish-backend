package com.codecool.travelish;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.repository.CompanyRepository;
import com.codecool.travelish.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void getAllCompanies() throws Exception {
        Company company = new Company("codecool", "codecool@yahoo.com", "password", "logo", "description", "IT");
        companyService.save(company);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/companies/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // ${7} is the seventh company added to the db, this company comes after the ones from command line runner
                .andExpect(MockMvcResultMatchers.jsonPath("$[7].name",is(company.getName())));
        companyRepository.delete(company);
    }

    @Test
    void getCompanyById() throws Exception {
        Company company = new Company("codecool", "codecool@yahoo.com", "password", "logo", "description", "IT");
        companyService.save(company);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/companies/{id}", company.getId()).
                                contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.id", is(company.getId().intValue())));

        companyRepository.delete(company);
    }
}
