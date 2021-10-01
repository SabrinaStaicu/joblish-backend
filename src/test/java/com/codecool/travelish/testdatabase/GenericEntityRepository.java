package com.codecool.travelish.testdatabase;


import com.codecool.travelish.testdatabase.GenericEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericEntityRepository extends JpaRepository<GenericEntity, Long> {
}
