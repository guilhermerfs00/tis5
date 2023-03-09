package com.puc.ticketin.domain.mapper;

import com.puc.ticketin.api.request.UserRequest;
import com.puc.ticketin.api.response.UserResponse;
import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.domain.entity.User;
import com.puc.ticketin.domain.enums.RoleEnum;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-09T20:18:56-0300",
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
        userBO.setPassword( entity.getPassword() );
        userBO.setEmail( entity.getEmail() );
        userBO.setActive( entity.isActive() );
        List<RoleEnum> list = entity.getRoles();
        if ( list != null ) {
            userBO.setRoles( new ArrayList<RoleEnum>( list ) );
        }

        return userBO;
    }

    @Override
    public UserResponse entityToResponse(User entity) {
        if ( entity == null ) {
            return null;
        }

        List<RoleEnum> roles = null;
        String id = null;
        String name = null;
        String email = null;
        Boolean active = null;

        List<RoleEnum> list = entity.getRoles();
        if ( list != null ) {
            roles = new ArrayList<RoleEnum>( list );
        }
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        active = entity.isActive();

        UserResponse userResponse = new UserResponse( id, name, email, active, roles );

        return userResponse;
    }

    @Override
    public User boToEntity(UserBO bo) {
        if ( bo == null ) {
            return null;
        }

        User user = new User();

        user.setId( bo.getId() );
        user.setPassword( bo.getPassword() );
        user.setEmail( bo.getEmail() );
        if ( bo.getActive() != null ) {
            user.setActive( bo.getActive() );
        }
        List<RoleEnum> list = bo.getRoles();
        if ( list != null ) {
            user.setRoles( new ArrayList<RoleEnum>( list ) );
        }

        return user;
    }

    @Override
    public UserBO requestToBo(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        UserBO userBO = new UserBO();

        userBO.setPassword( request.getPassword() );
        userBO.setEmail( request.getEmail() );
        userBO.setActive( request.getActive() );
        List<RoleEnum> list = request.getRoles();
        if ( list != null ) {
            userBO.setRoles( new ArrayList<RoleEnum>( list ) );
        }

        return userBO;
    }

    @Override
    public UserResponse boToResonse(UserBO bo) {
        if ( bo == null ) {
            return null;
        }

        List<RoleEnum> roles = null;
        String id = null;
        String email = null;
        Boolean active = null;

        List<RoleEnum> list = bo.getRoles();
        if ( list != null ) {
            roles = new ArrayList<RoleEnum>( list );
        }
        id = bo.getId();
        email = bo.getEmail();
        active = bo.getActive();

        String name = null;

        UserResponse userResponse = new UserResponse( id, name, email, active, roles );

        return userResponse;
    }
}
