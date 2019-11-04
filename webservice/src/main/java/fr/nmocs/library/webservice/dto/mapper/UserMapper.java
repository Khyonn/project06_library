package fr.nmocs.library.webservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.nmocs.library.model.Admin;
import fr.nmocs.library.model.User;
import fr.nmocs.library.webservice.dto.AdminDTO;
import fr.nmocs.library.webservice.dto.UserDTO;

@Mapper
public interface UserMapper extends LibraryDTOMapper<User, UserDTO> {

	@Mapping(target = "password", ignore = true)
	UserDTO toDto(User user);

	User toEntity(UserDTO userDto);

	@Mapping(target = "password", ignore = true)
	AdminDTO toDto(Admin admin);

	Admin toEntity(AdminDTO adminDto);
}
