package fr.nmocs.library.webservice.dto.mapper;

public interface LibraryDTOMapper<Entity, Dto> {

	Entity fromDTO(Dto dto);

	Dto toDTO(Entity entity);
}
