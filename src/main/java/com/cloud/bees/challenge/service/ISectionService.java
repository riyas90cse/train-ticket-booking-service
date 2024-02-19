package com.cloud.bees.challenge.service;

import com.cloud.bees.challenge.model.Section;

import java.util.List;

public interface ISectionService {

    Section saveSection(Section section);

    Section getSection(Long id);

    List<Section> getSections();

    void deleteSection(Long id);

    Section selectSection(Long trainId, String trainNo);

}
