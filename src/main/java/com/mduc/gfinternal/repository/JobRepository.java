package com.mduc.gfinternal.repository;

import com.mduc.gfinternal.model.Contact;
import com.mduc.gfinternal.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query("SELECT j FROM Job j WHERE j.title LIKE %:title% AND j.expired = false ")
    Page<Job> findByTitle(@Param("title") String title, Pageable pageable);
    @Query("SELECT j FROM Job j WHERE j.title LIKE %:title%")
    Page<Job> adminFindByTitle(@Param("title") String title, Pageable pageable);
}
