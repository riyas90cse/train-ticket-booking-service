package com.cloud.bees.challenge.controller;

import com.cloud.bees.challenge.model.Train;
import com.cloud.bees.challenge.service.impl.TrainService;
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
@Tag(name = "Train Controller", description = "Testing Purpose")
public class TrainController {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping(value = "/trains")
    public ResponseEntity<List<Train>> getAll() {
        return new ResponseEntity<>(trainService.getTrains(), HttpStatus.OK);
    }

    @GetMapping(value = "/trains/{id}")
    public ResponseEntity<Train> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(trainService.getTrain(id), HttpStatus.OK);
    }

    @PostMapping(value = "/trains")
    public ResponseEntity<Train> create(@RequestBody @Valid Train train) {
        Train savedTrain = trainService.saveTrain(train);
        return new ResponseEntity<>(savedTrain, HttpStatus.CREATED);
    }

    @PutMapping(value = "/trains")
    public ResponseEntity<Train> update(@RequestBody @Valid Train train) {
        Train updatedTrain = trainService.updateTrain(train);
        return new ResponseEntity<>(updatedTrain, HttpStatus.OK);
    }

    @DeleteMapping(value = "/trains/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        trainService.deleteTrain(id);
        String message = "Train Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
