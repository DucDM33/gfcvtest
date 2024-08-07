package com.mduc.gfinternal.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.mduc.gfinternal.model.User;
import com.mduc.gfinternal.model.dto.GetUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserToGetUserDtoConverter implements Converter<User, GetUserDto> {
    @Override
    public GetUserDto convert(User user) {
        GetUserDto dto = new GetUserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setDelete(user.isDelete());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
