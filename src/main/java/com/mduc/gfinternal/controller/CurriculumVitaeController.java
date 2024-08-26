package com.mduc.gfinternal.controller;

import com.mduc.gfinternal.model.CurriculumVitae;
import com.mduc.gfinternal.service.CurriculumVitaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/cv")
public class CurriculumVitaeController {
    @Autowired
    private CurriculumVitaeService curriculumVitaeService;

    @PostMapping("/save")
    public ResponseEntity<CurriculumVitae> saveCV(@RequestBody CurriculumVitae curriculumVitae) {
        try {
            CurriculumVitae savedCurriculumVitae = curriculumVitaeService.saveCV(curriculumVitae);
            return new ResponseEntity<>(savedCurriculumVitae, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
