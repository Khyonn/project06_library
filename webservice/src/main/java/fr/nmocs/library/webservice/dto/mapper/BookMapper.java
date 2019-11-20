package fr.nmocs.library.webservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fr.nmocs.library.business.model.ReservationQueue;
import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.User;
import fr.nmocs.library.webservice.dto.BookDTO;
import fr.nmocs.library.webservice.dto.ReservationQueueDTO;
import fr.nmocs.library.webservice.dto.UserDTO;

@Mapper
public interface BookMapper extends LibraryDTOMapper<Book, BookDTO> {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	ReservationQueue fromDTO(ReservationQueueDTO dto);

	ReservationQueueDTO toDTO(ReservationQueue entity);

	User fromDTO(UserDTO userDto);

	@Mapping(target = "password", ignore = true)
	UserDTO toDTO(User user);

}
