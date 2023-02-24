package com.puc.ticketin.domain.mapper;

import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.domain.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-23T22:06:03-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserBO entityToBo(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserBO userBO = new UserBO();

        userBO.setId( entity.getId() );
        userBO.setUsername( entity.getUsername() );
        userBO.setPassword( entity.getPassword() );
        userBO.setEmail( entity.getEmail() );
        userBO.setActive( entity.isActive() );
        List<String> list = entity.getRoles();
        if ( list != null ) {
            userBO.setRoles( new ArrayList<String>( list ) );
        }

        return userBO;
    }

    @Override
    public UserEntity boToEntity(UserBO bo) {
        if ( bo == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( bo.getId() );
        userEntity.setUsername( bo.getUsername() );
        userEntity.setPassword( bo.getPassword() );
        userEntity.setEmail( bo.getEmail() );
        if ( bo.getActive() != null ) {
            userEntity.setActive( bo.getActive() );
        }
        List<String> list = bo.getRoles();
        if ( list != null ) {
            userEntity.setRoles( new ArrayList<String>( list ) );
        }

        return userEntity;
    }
}
