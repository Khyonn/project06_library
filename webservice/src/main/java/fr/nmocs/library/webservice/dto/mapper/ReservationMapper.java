package fr.nmocs.library.webservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.nmocs.library.model.Reservation;
import fr.nmocs.library.webservice.dto.ReservationDTO;

@Mapper
public interface ReservationMapper extends LibraryDTOMapper<Reservation, ReservationDTO> {

	ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
}
