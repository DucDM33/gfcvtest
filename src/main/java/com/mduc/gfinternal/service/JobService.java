package com.mduc.gfinternal.service;
import com.mduc.gfinternal.exceptionHandler.NotFoundException;
import com.mduc.gfinternal.model.Job;
import com.mduc.gfinternal.model.dto.JobRequestDTO;
import com.mduc.gfinternal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    public Job addJob(JobRequestDTO jobRequest) {
        Job job = new Job();
        job.setTitle(jobRequest.getTitle());
        job.setPosition(jobRequest.getPosition());
        job.setDescription(jobRequest.getDescription());
        job.setRequirements(jobRequest.getRequirements());
        job.setOther(jobRequest.getOther());
        job.setSalary(jobRequest.getSalary());
        job.setBenefit(jobRequest.getBenefit());
        job.setDeadline(jobRequest.getDeadline());
        job.setWorkingTime(jobRequest.getWorkingTime());
        job.setQuantity(jobRequest.getQuantity());
        job.setInterviewFormat(jobRequest.getInterviewFormat());
        job.setLocation(jobRequest.getLocation());
        job.setExpired(false);

        return jobRepository.save(job);
    }
    public Job getJobById(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job foundJob = job.get();
            if (foundJob.getExpired()== false) {
                return foundJob;
            }
        }
        throw new NotFoundException("Job not found or is expired with id: " + id);
    }
    public Page<Job> searchJobsByTitle(String title, String sortBy, String sortDirection, int page, int size) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return jobRepository.findByTitle(title, pageable);
    }
}
