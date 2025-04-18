package com.projectmanagement.repo;

import com.projectmanagement.model.ResumeScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.projectmanagement.model.User;


import java.util.Optional;

public interface ResumeScreeningRepository extends JpaRepository<ResumeScreening, Long> {

    @Query("SELECT r FROM ResumeScreening r WHERE r.user.email = :email")
    Optional<ResumeScreening> findByUserEmail(@Param("email") String email);
    Optional<ResumeScreening> findByUser(User user);


}
