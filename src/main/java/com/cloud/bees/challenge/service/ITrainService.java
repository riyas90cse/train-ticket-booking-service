package com.cloud.bees.challenge.service;

import com.cloud.bees.challenge.model.Train;

import java.util.List;

public interface ITrainService {

    Train saveTrain(Train train);

    Train updateTrain(Train train);

    Train getTrain(Long id);

    List<Train> getTrains();

    void deleteTrain(Long id);

    Train selectTrain(String from, String to);

}
