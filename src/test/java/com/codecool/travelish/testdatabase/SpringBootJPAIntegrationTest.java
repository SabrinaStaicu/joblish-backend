package com.codecool.travelish.testdatabase;

import com.codecool.travelish.model.application.Application;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpringBootJPAIntegrationTest {

    @Autowired
    GenericEntityRepository genericEntityRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetrieveEntity_thenOk() {

        GenericEntity genericEntity = genericEntityRepository
                .save(new GenericEntity("test"));
        GenericEntity foundEntity = genericEntityRepository.getById(genericEntity.getId());

        Assert.assertNotNull(foundEntity);
        Assert.assertEquals(genericEntity.getValue(), foundEntity.getValue());
        System.out.println(foundEntity.getValue());

    }

}
