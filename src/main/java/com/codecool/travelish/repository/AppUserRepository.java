package com.codecool.travelish.repository;

import com.codecool.travelish.model.user.AppUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("select new AppUser (au.firstName,au.lastName, au.picture, ap.date, ap.notes) FROM AppUser au, application ap , Company cp WHERE cp.id= ?1")
    List<AppUser> selectUsersByCompany(Long id, Pageable pageable);


}
