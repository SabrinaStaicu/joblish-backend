package com.codecool.travelish.controller;

import com.codecool.travelish.model.application.Application;
import com.codecool.travelish.model.application.ApplicationStatus;
import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.job.JobType;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.model.user.JobPreferences;
import com.codecool.travelish.repository.AppUserRepository;
import com.codecool.travelish.repository.ApplicationRepository;
import com.codecool.travelish.repository.CompanyRepository;
import com.codecool.travelish.repository.JobsRepository;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
@Transactional
@SpringBootTest
public class ApplicationControllerTest {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    MockMvc mockMvc;

    Company company1;
    AppUser appUser1;
    Job job1;
    Application application1;


    @Before
    public void init () {
        Company company = new Company("Microsoft","microsoft@yahoo.com", "password","https://cdn.vox-cdn.com/thumbor/NeSo4JAqv-fFJCIhb5K5eBqvXG4=/7x0:633x417/1200x800/filters:focal(7x0:633x417)/cdn.vox-cdn.com/assets/1311169/mslogo.jpg", "Microsoft was founded in 1975. Our mission is to enable people and businesses throughout the world to realize their full potential by creating technology that transforms the way people work, play, and communicate.", "IT");
        AppUser appUser = new AppUser("Osmanthus", "Wine","Bucharest","No experience. Just graduated.","3223112123","https://media.cancan.ro/unsafe/750x750/smart/filters:contrast(5):quality(80)/https://tacataca.prosport.ro/wp-content/uploads/2021/03/dorian-popa1.jpg","andrei_penica@yahoo.com", "password", new JobPreferences(true, List.of("System administator", "Full-stack developer", "Devops Engineer"), List.of("Bucharest ", "Budapest ", "London "), List.of("Full-time", "Part-time")), List.of("Microsoft Office", "HTML", "CSS", "Golang", "Microservices", "COBOL", "Java"));
        Job job = new Job("Back end developer", LocalDate.now(),"Romania", JobType.FULL_TIME, ExperienceType.JUNIOR,"IT","<p style=\"text-size-adjust: 100%; overflow-wrap: break-word;\"><a href=\"https://build.a.team/referredfromremotive\" rel=\"nofollow\" style=\"background-color: white;\">A·Team</a> is a VC-backed, stealth, application-only platform for senior software builders to team up with the best companies on their next big thing. After talking with hundreds of independent engineers, designers, and product folks, we heard over and over that finding vetted, high-quality, consistent clients is hard, and projects are often too small to be rewarding. A·Team matches small teams of the most talented builders in the world with companies backed by a16z, YC, Softbank, General Catalyst, etc. on a contract basis for many of their most important initiatives. We quietly launched in May 2020, and have helped A·Teamers earn $8.2+ million since.<br></p><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><em>As part of A·Team, you can expect:</em></span></p><ul style=\"margin-top:0;margin-bottom:0;padding-inline-start:48px;\"><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong>High-paying, meaningful missions with the most audacious companies</strong></span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong> sent your way;</strong> generally $110-$190/hr, with vetted, fascinating clients doing work that matters. A·Team is picky, and only works with companies referred to us by top funds or existing members and clients such as Lyft, McGraw Hill, the former-CEO of Waze, a few new unicorns, and dozens of venture-backed startups by a16z, YC, Softbank, GC, etc. </span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong>Keep 100% of what you earn:</strong> </span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">we exist to empower independent builders, which is why A.Team charges a percentage </span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">on top of your standard rate.</span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong>Do more with a team you like:</strong> </span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">our focus is on small, diverse, cross-functional teams of insanely talented developers, PMs, designers, growth leads, etc. This is because clients with bigger budgets &amp; more ambitious work need teams to solve problems, not individuals to do tasks. That said, we try to keep friends together, since clients love builders who have worked together before.</span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong>Full autonomy:</strong></span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"> we only want you to join teams when the timing's right. Often they start around 15-20hrs/week, and last at least 3-6 months (longer if it's going great, or contract-to-hire if true love is found). </span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong>Small, curated, off-the-record gatherings:</strong></span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"> to meet other independent builders around common interests, to potentially team up and refer one another work</span></p></li></ul><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong>How to apply:</strong></span></p><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Go here: <a href=\"https://build.a.team/referredfromremotive\" rel=\"nofollow\" style=\"color: rgb(57, 64, 80); text-decoration: none; background-color: rgb(255, 255, 255);\">https://build.a.team/referredfromremotive</a> + mention Remotive. </span>No resume or cover letter needed; we respect your time so the application is short. We're also much more interested in seeing what you've made, and excited to chat more if there’s a fit.</p><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong>What you’ll do:</strong></span></p><ul style=\"margin-top:0;margin-bottom:0;padding-inline-start:48px;\"><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Once part of A.Team, you’ll regularly be invited to impactful missions that match your interests, which you can accept or decline. Take your pick from early-stage incubations with world-class founders, to fast-growing super-funded companies, to old school non-tech incumbents looking to build as a tech giant would</span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Missions usually involve building an ambitious piece of software from 0 to 1 as part of a small 3-4 person team. </span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">You’ll be paid to scope it out, give the client options, guide strategy, and execute on the selected solution. Sometimes the client has a clear vision, sometimes not; which is why A.Team builders tend to be senior folks who can work together to find the right direction. </span></p></li></ul><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><strong><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Who A</span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">·</span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Team is for:</span></strong></p><ul style=\"margin-top:0;margin-bottom:0;padding-inline-start:48px;\"><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Senior software developers who left large companies and high-growth startups to pursue their craft with autonomy.</span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Those who prefer consistent contract work over a full-time role, who want to create a variety of new products alongside other top-tier builders.</span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">The majority of A.Teamers spend most of their time doing independent work, but a sizeable percentage are either employed full-time (but testing out client work), bootstrapping a side project, or looking for their next big thing</span></p></li></ul><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><strong><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Who A</span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">·</span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Team is </span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">not</span><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"> for:</span></strong></p><ul style=\"margin-top:0;margin-bottom:0;padding-inline-start:48px;\"><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">People looking for small gigs</span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Folks looking to build simple wordpress/wix/squarespace-style websites</span></p></li><li dir=\"ltr\" style=\"list-style-type: disc; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><p dir=\"ltr\" style=\"line-height:1.38;margin-top:0pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">Those still early in their careers and recent university/bootcamp grads (at least not yet)</span></p></li></ul><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><strong>Our long-term vision:</strong></span></p><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\"><a href=\"https://build.a.team/referredfromremotive\" rel=\"nofollow\">A·Team</a> is a new type of company for a new kind of independent software builder. We call them \"unhirables\": people who traditional companies couldn’t hire full-time even if they wanted to, but who want to do their most meaningful work with their favorite people in small, autonomous, distributed expert teams. </span></p><span id=\"docs-internal-guid-89e33c27-7fff-f5a0-6a97-70cc09d5793a\"></span><p dir=\"ltr\" style=\"line-height:1.38;margin-top:12pt;margin-bottom:12pt;\"><span style=\"font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline;\">To help us secure amazing missions, we raised $5 million+ (not public, yet) from NFX, Village Global, and Box Group, along with the former CEO of Upwork, the founders of Fiverr and Lemonade, Apple's Global Head of Recruiting, YC Partner Aaron Harris, Wharton's Adam Grant, and Duke's Dan Ariely.</span></p>",99,"Bucharest", company);
        Application application = new Application(ApplicationStatus.Not_seen, job, appUser, LocalDate.now());


        company1 = companyRepository.save(company);
        appUser1 = appUserRepository.save(appUser);
        job1 = jobsRepository.save(job);
        application1 = applicationRepository.save(application);
    }

    @Test
    public void getAllByUser_responseOk() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/applications/by-user-id/{id}", appUser1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].job.title",is(application1.getJob().getTitle())));

    }
//
    @Test
    public void getAllByJobId_responseOk() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/applications/filter-by-job/{id}", job1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].job.title",is(job1.getTitle())));
    }

}
