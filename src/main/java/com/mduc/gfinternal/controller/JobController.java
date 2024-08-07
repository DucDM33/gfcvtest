package com.mduc.gfinternal.controller;

import com.mduc.gfinternal.exceptionHandler.NotFoundException;
import com.mduc.gfinternal.model.Job;
import com.mduc.gfinternal.model.dto.JobRequestDTO;
import com.mduc.gfinternal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/new")
    public ResponseEntity<Job> addJob(@Valid @RequestBody JobRequestDTO jobRequest) {
        Job savedJob = jobService.addJob(jobRequest);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer id) {
        try {
            Job job = jobService.getJobById(id);
            return new ResponseEntity<>(job, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    public Page<Job> searchJobs(
            @RequestParam String title,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return jobService.searchJobsByTitle(title, sortBy, sortDirection, page, size);
    }
}
