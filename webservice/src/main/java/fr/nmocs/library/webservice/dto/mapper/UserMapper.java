package fr.nmocs.library.webservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fr.nmocs.library.model.Admin;
import fr.nmocs.library.model.User;
import fr.nmocs.library.webservice.dto.AdminDTO;
import fr.nmocs.library.webservice.dto.UserDTO;

@Mapper
public interface UserMapper extends LibraryDTOMapper<User, UserDTO> {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "password", ignore = true)
	UserDTO toDTO(User user);

	User fromDTO(UserDTO userDto);

	@Mapping(target = "password", ignore = true)
	AdminDTO toDTO(Admin admin);

	Admin fromDTO(AdminDTO adminDto);
}
