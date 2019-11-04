package fr.nmocs.library.webservice.dto.mapper;

import org.mapstruct.Mapper;

import fr.nmocs.library.business.model.ReservationQueue;
import fr.nmocs.library.webservice.dto.ReservationQueueDTO;

@Mapper
public interface ReservationQueueMapper extends LibraryDTOMapper<ReservationQueue, ReservationQueueDTO> {

}
