package fr.nmocs.library.webservice.dto.mapper;

public interface LibraryDTOMapper<Entity, Dto> {

	Entity fromDto(Dto dto);

	Dto toDto(Entity entity);
}
