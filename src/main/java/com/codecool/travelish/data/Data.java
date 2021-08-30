package com.codecool.travelish.data;

import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.user.AppUser;
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
        Job job = new Job("JOB1", LocalDate.now(),"Romania","Remote", ExperienceType.JUNIOR,"Microsoft","no-image","It","asdasdasd",99,"Bucharest");

        AppUser appUser = new AppUser("andreiandrei", "Andrei","Penica","Bucharest","exp","3423423422","avatar@yahoo.com", "password");
        jobsRepository.save(job);
        userRepository.save(appUser);

    }
}
