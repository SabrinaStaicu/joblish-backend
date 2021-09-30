package com.codecool.travelish.controller;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.job.JobType;
import com.codecool.travelish.repository.CompanyRepository;
import com.codecool.travelish.repository.JobsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private final List<Job> jobs = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void init() {
        Company company = new Company("codecool", "codecool@yahoo.com", "password", "logo", "description", "IT");
        companyRepository.save(company);

        Job firstJob = new Job("Job", LocalDate.now(), "Rom", JobType.PART_TIME, ExperienceType.JUNIOR, "IT", "Description", 2222, "CITY", company);
        Job secondJob = new Job("Second job", LocalDate.now(), "Rom", JobType.PART_TIME, ExperienceType.JUNIOR, "IT", "Description", 2222, "CITY", company);
        jobs.addAll(List.of(firstJob, secondJob));
    }

    @Test
    void getAllJobs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/jobs/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(jobs.size() + 15)));
    }

    @Test
    @WithMockUser(roles = "COMPANY")
    void addJob() throws Exception {
        Long companyId = companyRepository.findAll().get(companyRepository.findAll().size() - 1).getId();
        Job job = jobs.get(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/jobs/add-job/{companyId}", companyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(job)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        jobsRepository.delete(jobsRepository.findAll().get(jobsRepository.findAll().size() - 1));
    }

    @Test
    @WithMockUser(roles = "COMPANY")
    void deleteJob() throws Exception {
        Long jobId = jobsRepository.findAll().get(jobsRepository.findAll().size() - 1).getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/jobs/{jobId}", jobId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
