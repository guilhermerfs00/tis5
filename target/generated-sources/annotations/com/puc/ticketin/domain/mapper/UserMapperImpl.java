package com.puc.ticketin.domain.mapper;

import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.domain.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-02T21:08:39-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserBO entityToBo(User entity) {
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
    public User boToEntity(UserBO bo) {
        if ( bo == null ) {
            return null;
        }

        User user = new User();

        user.setId( bo.getId() );
        user.setUsername( bo.getUsername() );
        user.setPassword( bo.getPassword() );
        user.setEmail( bo.getEmail() );
        if ( bo.getActive() != null ) {
            user.setActive( bo.getActive() );
        }
        List<String> list = bo.getRoles();
        if ( list != null ) {
            user.setRoles( new ArrayList<String>( list ) );
        }

        return user;
    }
}
