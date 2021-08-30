package com.codecool.travelish.data;

import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.JobOffer;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.model.user.Resume;
import com.codecool.travelish.model.user.UserEducation;
import com.codecool.travelish.model.user.UserExperience;
import com.codecool.travelish.repository.JobsRepository;
import com.codecool.travelish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Data implements CommandLineRunner {
    private JobsRepository jobsRepository;
    private UserRepository userRepository;

    @Autowired
    public Data(JobsRepository jobsRepository, UserRepository userRepository){
        this.jobsRepository = jobsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        JobOffer jobOffer = new JobOffer("JOB1", LocalDate.now(),"Romania","Remote", ExperienceType.JUNIOR,"Microsoft","no-image","It","asdasdasd",99,"Bucharest");
        UserEducation education = new UserEducation("scoala");
        UserExperience experience = new UserExperience("experience", "It");
        Resume resume = new Resume(List.of(experience), List.of(education));
        AppUser appUser = new AppUser("Andrei","Penica","Romania","Bucharest","asdas",1234,resume,"avatar","email");
        jobsRepository.save(jobOffer);
        userRepository.save(appUser);

    }
}
