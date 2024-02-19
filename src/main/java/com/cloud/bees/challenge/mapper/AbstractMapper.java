package com.cloud.bees.challenge.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface AbstractMapper<E, R> {
    E toEntity(R resource);

    R toResource(E entity);

    default List<R> toResources(List<E> entities) {
        return entities == null ? Collections.emptyList() :
                entities.stream()
                        .filter(Objects::nonNull)
                        .map(this::toResource)
                        .filter(Objects::nonNull).toList();
    }
}
