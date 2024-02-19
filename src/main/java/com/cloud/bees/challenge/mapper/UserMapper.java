package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.UserEntity;
import com.cloud.bees.challenge.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends AbstractMapper<UserEntity, User> {

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(User user);

    User toResource(UserEntity entity);

    void copyToEntity(User user, @MappingTarget UserEntity entity);
}
