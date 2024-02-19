package com.cloud.bees.challenge.service.impl;

import com.cloud.bees.challenge.entity.TrainEntity;
import com.cloud.bees.challenge.exception.NotFoundException;
import com.cloud.bees.challenge.mapper.TrainMapper;
import com.cloud.bees.challenge.model.Train;
import com.cloud.bees.challenge.repository.TrainRepository;
import com.cloud.bees.challenge.service.ITrainService;
import com.cloud.bees.challenge.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.cloud.bees.challenge.service.util.ServiceUtil.setSeatDetails;
import static com.cloud.bees.challenge.util.ServiceErrors.TRAIN_ENTITY_NOT_FOUND_BY_ID;
import static com.cloud.bees.challenge.util.ServiceErrors.TRAIN_NOT_AVAILABLE_FOR_GIVEN_LOCATION;

@Slf4j
@Service
public class TrainService implements ITrainService {

    private final TrainRepository trainRepository;
    private final TrainMapper trainMapper;

    @Autowired
    public TrainService(TrainRepository trainRepository, TrainMapper trainMapper) {
        this.trainRepository = trainRepository;
        this.trainMapper = trainMapper;
    }

    @Transactional
    @Override
    public Train saveTrain(Train train) {
        //Generate Random Train NO When Persist
        train.setTrainNo(ApplicationUtil.generateTrainNo());

        TrainEntity entity = trainMapper.toEntity(train);
        TrainEntity savedEntity = trainRepository.saveAndFlush(entity);
        Train savedTrain = trainMapper.toResource(savedEntity);
        setSections(savedEntity, savedTrain);
        log.info("Saved Train {}", savedTrain.toString());
        return savedTrain;
    }

    @Transactional
    @Override
    public Train updateTrain(Train train) {
        Optional<TrainEntity> entity = trainRepository.findById(train.getId());
        if (entity.isEmpty()) {
            throw new NotFoundException(TRAIN_ENTITY_NOT_FOUND_BY_ID);
        }
        trainMapper.copyToEntity(train, entity.get());
        TrainEntity updatedEntity = trainRepository.saveAndFlush(entity.get());
        Train updatedTrain = trainMapper.toResource(updatedEntity);
        setSections(updatedEntity, updatedTrain);
        log.info("Updated Train {}", updatedTrain.toString());
        return updatedTrain;
    }

    @Override
    public Train getTrain(Long id) {
        Optional<TrainEntity> entity = trainRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException(TRAIN_ENTITY_NOT_FOUND_BY_ID);
        }
        Train train = trainMapper.toResource(entity.get());
        setSections(entity.get(), train);
        log.info("Existing Train {}", train.toString());
        return train;
    }

    @Override
    public List<Train> getTrains() {
        List<TrainEntity> entities = trainRepository.findAll();
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<Train> trains = trainMapper.toResources(entities);
        entities.forEach(entity -> trains.forEach(train -> setSections(entity, train)));
        log.info("Existing Trains {}", trains.toString());
        return trains;
    }

    @Transactional
    @Override
    public void deleteTrain(Long id) {
        Optional<TrainEntity> entity = trainRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException(TRAIN_ENTITY_NOT_FOUND_BY_ID);
        }
        log.info("Existing Train to be deleted {}", entity.get());
        trainRepository.delete(entity.get());
    }

    @Override
    public Train selectTrain(String from, String to) {
        List<Train> availableTrains = getAvailableTrainsByFromAndToLocation(from, to);
        if (availableTrains.isEmpty()) {
            throw new NotFoundException(TRAIN_NOT_AVAILABLE_FOR_GIVEN_LOCATION);
        }
        return availableTrains.getFirst();
    }

    public List<Train> getAvailableTrainsByFromAndToLocation(String from, String to) {
        List<TrainEntity> entities = trainRepository.findTrainEntitiesByOriginLocationAndDestinationLocation(from, to);
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }
        return trainMapper.toResources(entities);
    }

    public static void setSections(TrainEntity entity, Train train) {
        if (entity.getSections() != null && !entity.getSections().isEmpty()) {
            entity.getSections().forEach(sectionEntity ->
                    train.getSections().forEach(section -> {
                        section.setTrainName(sectionEntity.getTrain().getTrainName());
                        section.setTrainNo(sectionEntity.getTrain().getTrainNo());
                        if (!sectionEntity.getSeats().isEmpty()) {
                            sectionEntity.getSeats().forEach(seatEntity ->
                                    section.getSeats()
                                            .forEach(seat -> seat.setSeatDetail(setSeatDetails(seatEntity))));
                        }
                    }));
        }
    }
}
