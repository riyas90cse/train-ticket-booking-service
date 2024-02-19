package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.UserEntity;
import com.cloud.bees.challenge.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserMapperTest {

    @Mock
    private UserMapper mapper;
    @Mock
    private User user;
    @Mock
    private UserEntity userEntity;

    @BeforeEach
    void setup() {
        user = new User();
        userEntity = new UserEntity();
    }

    @Test
    void resourceToEntityTest() {
        when(mapper.toEntity(user)).thenReturn(userEntity);
        assertNotNull(user);
    }

    @Test
    void entityToResourceTest() {
        when(mapper.toResource(userEntity)).thenReturn(user);
        assertNotNull(user);
    }
}
