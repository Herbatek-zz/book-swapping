package com.piotrek.bookswapping.services.converters;

import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookForExchangeConverter {

    private ModelMapper modelMapper;

    public BookForExchangeConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookForExchangeDto convertToDto(BookForExchange bookForExchange) {
        return modelMapper.map(bookForExchange, BookForExchangeDto.class);
    }

    public BookForExchange convertToEntity(BookForExchangeDto bookForExchangeDto) {
        return modelMapper.map(bookForExchangeDto, BookForExchange.class);
    }
}
