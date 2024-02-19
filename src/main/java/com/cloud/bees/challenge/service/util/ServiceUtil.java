package com.cloud.bees.challenge.service.util;

import com.cloud.bees.challenge.entity.SeatEntity;
import com.cloud.bees.challenge.entity.SectionEntity;
import com.cloud.bees.challenge.model.Section;

import java.util.HashMap;
import java.util.Map;

public class ServiceUtil {
    public static Map<String, String> setSeatDetails(SeatEntity savedEntity) {
        Map<String, String> seatDetailMap = new HashMap<>();
        seatDetailMap.put("trainNo", savedEntity.getSection().getTrain().getTrainNo());
        seatDetailMap.put("trainName", savedEntity.getSection().getTrain().getTrainName());
        seatDetailMap.put("sectionName", savedEntity.getSection().getName());
        seatDetailMap.put("sectionDescription", savedEntity.getSection().getDescription());
        return seatDetailMap;
    }

    public static void setSectionAndSeatDetail(Section section, SectionEntity entity) {
        section.setTrainNo(entity.getTrain().getTrainNo());
        section.setTrainName(entity.getTrain().getTrainName());
        if (entity.getSeats() != null && !entity.getSeats().isEmpty()) {
            entity.getSeats().forEach(seatEntity ->
                    section.getSeats().forEach(seat -> seat.setSeatDetail(setSeatDetails(seatEntity))));
        }
    }
}
