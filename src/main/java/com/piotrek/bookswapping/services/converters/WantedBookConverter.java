package com.piotrek.bookswapping.services.converters;

import com.piotrek.bookswapping.dto.WantedBookDto;
import com.piotrek.bookswapping.entities.WantedBook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WantedBookConverter {

    private ModelMapper modelMapper;

    public WantedBookConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public WantedBookDto convertToDto(WantedBook wantedBook) {
        return modelMapper.map(wantedBook, WantedBookDto.class);
    }

    public WantedBook convertToEntity(WantedBookDto wantedBookDto) {
        return modelMapper.map(wantedBookDto, WantedBook.class);
    }
}
