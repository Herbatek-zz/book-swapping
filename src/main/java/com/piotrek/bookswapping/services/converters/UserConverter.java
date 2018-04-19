package com.piotrek.bookswapping.services.converters;

import com.piotrek.bookswapping.dto.UserDto;
import com.piotrek.bookswapping.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
