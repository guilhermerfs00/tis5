package com.puc.ticketin.domain.mapper;

import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.domain.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-26T16:52:25-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserBO entityToBo(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserBO.UserBOBuilder userBO = UserBO.builder();

        userBO.id( entity.getId() );
        userBO.username( entity.getUsername() );
        userBO.password( entity.getPassword() );
        userBO.email( entity.getEmail() );
        userBO.active( entity.isActive() );
        List<String> list = entity.getRoles();
        if ( list != null ) {
            userBO.roles( new ArrayList<String>( list ) );
        }

        return userBO.build();
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
