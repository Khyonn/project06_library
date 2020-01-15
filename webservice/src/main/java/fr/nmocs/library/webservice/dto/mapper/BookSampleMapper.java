package fr.nmocs.library.webservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.webservice.dto.BookDTO;
import fr.nmocs.library.webservice.dto.BookSampleDTO;

@Mapper
public interface BookSampleMapper extends LibraryDTOMapper<BookSample, BookSampleDTO> {

	BookSampleMapper INSTANCE = Mappers.getMapper(BookSampleMapper.class);

	Book fromDto(BookDTO book);

	BookDTO toDto(Book book);
}
