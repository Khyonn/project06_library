package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.webservice.dto.AdminDTO;
import fr.nmocs.library.webservice.dto.ReservationDTO;
import fr.nmocs.library.webservice.dto.UserDTO;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "UserService", serviceName = "UserService")
public interface UserService {

	UserDTO createUser(@WebParam(name = "user") UserDTO user) throws LibraryWebserviceException;

	UserDTO updateUser(@WebParam(name = "user") UserDTO user, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	AdminDTO grantAdminRightsToUser(@WebParam(name = "userId") Integer userId,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	UserDTO downgradeAdminToBasicUser(@WebParam(name = "adminId") Integer adminId,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	UserDTO findById(@WebParam(name = "id") Integer id, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	UserDTO findByEmail(@WebParam(name = "email") String email, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	List<UserDTO> findByName(@WebParam(name = "name") String name,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	ReservationDTO saveReservation(@WebParam(name = "reservation") ReservationDTO reservation);

}
