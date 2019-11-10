package fr.nmocs.library.webservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.User;
import fr.nmocs.library.webservice.dto.BookDTO;
import fr.nmocs.library.webservice.dto.BookSampleDTO;
import fr.nmocs.library.webservice.dto.LoanDTO;
import fr.nmocs.library.webservice.dto.UserDTO;

@Mapper
public interface LoanMapper extends LibraryDTOMapper<Loan, LoanDTO> {

	LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

	User fromDTO(UserDTO dto);

	@Mapping(target = "password", ignore = true)
	UserDTO toDTO(User entity);

	BookSample fromDTO(BookSampleDTO dto);

	BookSampleDTO toDTO(BookSample entity);

	Book fromDTO(BookDTO dto);

	BookDTO toDTO(Book entity);
}
