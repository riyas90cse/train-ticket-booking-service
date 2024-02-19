package com.cloud.bees.challenge.service.impl;

import com.cloud.bees.challenge.entity.SectionEntity;
import com.cloud.bees.challenge.entity.TrainEntity;
import com.cloud.bees.challenge.exception.NotFoundException;
import com.cloud.bees.challenge.mapper.SectionMapper;
import com.cloud.bees.challenge.model.Section;
import com.cloud.bees.challenge.repository.SectionRepository;
import com.cloud.bees.challenge.repository.TrainRepository;
import com.cloud.bees.challenge.service.ISectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.cloud.bees.challenge.service.util.ServiceUtil.setSectionAndSeatDetail;
import static com.cloud.bees.challenge.util.ServiceErrors.*;

@Slf4j
@Service
public class SectionService implements ISectionService {

    private final SectionRepository sectionRepository;
    private final TrainRepository trainRepository;
    private final SectionMapper sectionMapper;

    public SectionService(SectionRepository sectionRepository, SectionMapper sectionMapper,
                          TrainRepository trainRepository) {
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
        this.trainRepository = trainRepository;
    }

    @Transactional
    @Override
    public Section saveSection(Section section) {
        SectionEntity entity = sectionMapper.toEntity(section);

        if (section.getTrainNo() != null || section.getTrainName() != null) {
            Optional<TrainEntity> trainEntity =
                    trainRepository.findTrainEntityByTrainNoOrTrainName(section.getTrainNo(), section.getTrainName());
            trainEntity.ifPresent(entity::setTrain);
        }

        SectionEntity savedEntity = sectionRepository.saveAndFlush(entity);
        Section savedSection = sectionMapper.toResource(savedEntity);

        setSectionAndSeatDetail(savedSection, savedEntity);

        log.info("Saved Section {}", savedSection);
        return savedSection;
    }

    @Override
    public Section getSection(Long id) {
        Optional<SectionEntity> entity = sectionRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException(SECTION_ENTITY_NOT_FOUND_BY_ID);
        }
        Section section = sectionMapper.toResource(entity.get());

        setSectionAndSeatDetail(section, entity.get());

        log.info("Existing Section {}", section);
        return section;
    }

    @Override
    public List<Section> getSections() {
        List<SectionEntity> entities = sectionRepository.findAll();
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<Section> sections = sectionMapper.toResources(entities);

        entities.forEach(sectionEntity -> sections.forEach(section ->
                setSectionAndSeatDetail(section, sectionEntity)));

        log.info("Existing Sections {}", sections.toString());
        return sections;
    }

    @Transactional
    @Override
    public void deleteSection(Long id) {
        Optional<SectionEntity> existingEntity = sectionRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new NotFoundException(SECTION_ENTITY_NOT_FOUND_BY_ID);
        }
        log.info("Existing Section to be deleted {}", existingEntity.get());
        sectionRepository.delete(existingEntity.get());
    }

    @Override
    public Section selectSection(Long trainId, String trainNo) {
        List<Section> availableSections = getAvailableSections(trainId, trainNo);
        if (availableSections.isEmpty()) {
            throw new NotFoundException(SECTION_NOT_AVAILABLE_FOR_GIVEN_TRAIN_NO_OR_ID);
        }
        return availableSections.getFirst();
    }

    private List<Section> getAvailableSections(Long trainId, String trainNo) {
        List<SectionEntity> entities;
        if (trainNo != null) {
            entities = sectionRepository.findSectionEntitiesByTrain_TrainNo(trainNo);
            return getSections(entities);
        } else if (trainId != null) {
            entities = sectionRepository.findSectionEntitiesByTrain_Id(trainId);
            return getSections(entities);
        } else {
            return Collections.emptyList();
        }
    }

    private List<Section> getSections(List<SectionEntity> entities) {
        List<Section> sections;
        sections = sectionMapper.toResources(entities);
        entities.forEach(sectionEntity -> sections.forEach(section ->
                setSectionAndSeatDetail(section, sectionEntity)));
        return sections;
    }
}
