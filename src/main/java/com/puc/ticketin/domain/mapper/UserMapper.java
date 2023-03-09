package com.puc.ticketin.domain.mapper;//package com.example.demo.domain.mapper;


import com.puc.ticketin.api.request.UserRequest;
import com.puc.ticketin.api.response.UserResponse;
import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserBO entityToBo(User entity);

    UserResponse entityToResponse(User entity);

    User boToEntity(UserBO bo);

    UserBO requestToBo(UserRequest request);

    UserResponse boToResonse(UserBO bo);
}
