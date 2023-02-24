package com.puc.ticketin.domain.mapper;//package com.example.demo.domain.mapper;


import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserBO entityToBo(UserEntity entity);

    UserEntity boToEntity(UserBO bo);
}
