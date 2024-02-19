package com.cloud.bees.challenge.controller;

import com.cloud.bees.challenge.model.Section;
import com.cloud.bees.challenge.service.impl.SectionService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Hidden
@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "Section Controller", description = "Testing Purpose")
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping(value = "/sections")
    public ResponseEntity<List<Section>> getAll() {
        return new ResponseEntity<>(sectionService.getSections(), HttpStatus.OK);
    }

    @GetMapping(value = "/sections/{id}")
    public ResponseEntity<Section> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(sectionService.getSection(id), HttpStatus.OK);
    }

    @PostMapping(value = "/sections")
    public ResponseEntity<Section> create(@RequestBody @Valid Section section) {
        Section savedSection = sectionService.saveSection(section);
        return new ResponseEntity<>(savedSection, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/sections/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        sectionService.deleteSection(id);
        String message = "Section Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
