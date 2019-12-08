package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.webservice.dto.ReservationDTO;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "ReservationService", serviceName = "ReservationService")
public interface ReservationService {

	ReservationDTO createReservation(@WebParam(name = "reservation") ReservationDTO reservation,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	void deleteReservation(@WebParam(name = "reservation") ReservationDTO reservation,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	List<ReservationDTO> findReservationsByUserId(@WebParam(name = "userId") Integer userId,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;
}
