package com.mduc.gfinternal.service;

import com.mduc.gfinternal.model.CurriculumVitae;
import com.mduc.gfinternal.repository.CurriculumVitaeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculumVitaeService {
    @Autowired
    private CurriculumVitaeRepository curriculumVitaeRepository;
    public CurriculumVitae saveCV(CurriculumVitae curriculumVitae) {
        CurriculumVitae savedCurriculumVitae = new CurriculumVitae();
        savedCurriculumVitae.setName(curriculumVitae.getName());
        savedCurriculumVitae.setBirthday(curriculumVitae.getBirthday());
        savedCurriculumVitae.setCvUrl(curriculumVitae.getCvUrl());
        savedCurriculumVitae.setPhoneNumber(curriculumVitae.getPhoneNumber());
        savedCurriculumVitae.setJobId(curriculumVitae.getJobId());
        savedCurriculumVitae.setEmail(curriculumVitae.getEmail());
        return curriculumVitaeRepository.save(savedCurriculumVitae);
    }

}
