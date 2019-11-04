package fr.nmocs.library.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.business.UserManagement;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;
import fr.nmocs.library.webservice.UserService;
import fr.nmocs.library.webservice.dto.AdminDTO;
import fr.nmocs.library.webservice.dto.ReservationDTO;
import fr.nmocs.library.webservice.dto.UserDTO;
import fr.nmocs.library.webservice.dto.mapper.BookMapper;
import fr.nmocs.library.webservice.dto.mapper.ReservationMapper;
import fr.nmocs.library.webservice.dto.mapper.UserMapper;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class UserServiceImpl implements UserService {

	private static final String NOT_ALLOWED = "You are not allowed to perform this action";

	@Autowired
	private UserManagement userMgmt;

	@Autowired
	private AuthManagement authMgmt;

	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

	private ReservationMapper reservationMapper = Mappers.getMapper(ReservationMapper.class);

	@Override
	public UserDTO createUser(UserDTO user) throws LibraryWebserviceException {
		try {
			return userMapper.toDto(userMgmt.createUser(userMapper.toEntity(user)));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public UserDTO updateUser(UserDTO user, String token) throws LibraryWebserviceException {
		User userFromToken = authMgmt.getUser(token);

		if (user != null && !authMgmt.isAdmin(token)) {
			// Si l'utilisateur n'est pas possesseur du compte, on refuse
			if (userFromToken == null || !user.getId().equals(userFromToken.getId())) {
				throw new LibraryWebserviceException(NOT_ALLOWED);
			}
			// Seul un admin peu modifier le status
			user.setStatus(null);
		}

		try {
			return userMapper.toDto(userMgmt.updateUser(userMapper.toEntity(user)));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public AdminDTO grantAdminRightsToUser(Integer userId, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return userMapper.toDto(userMgmt.grantAdminRightsToUser(userId));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException("You are not allowed to perform this action");
		}
	}

	@Override
	public UserDTO downgradeAdminToBasicUser(Integer adminId, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return userMapper.toDto(userMgmt.downgradeAdminToBasicUser(adminId));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException("You are not allowed to perform this action");
		}
	}

	@Override
	public UserDTO findById(Integer id, String token) throws LibraryWebserviceException {
		// Les utilisateurs peuvent consulter leur profil uniquement (et admin)
		User userFromToken = authMgmt.getUser(token);
		if (userFromToken == null || (!authMgmt.isAdmin(token) && !userFromToken.getId().equals(id))) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}

		try {
			return userMapper.toDto(userMgmt.findById(id));
		} catch (LibraryTechnicalException le) {
			return null;
		}
	}

	@Override
	public UserDTO findByEmail(String email, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return userMapper.toDto(userMgmt.findByEmail(email));
		} catch (LibraryTechnicalException le) {
			return null;
		}
	}

	@Override
	public List<UserDTO> findByName(String name, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return userMgmt.findByName(name).stream().map(u -> userMapper.toDto(u)).collect(Collectors.toList());
		} catch (LibraryTechnicalException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public ReservationDTO saveReservation(ReservationDTO reservation) {
		return reservationMapper.toDto(userMgmt.create(reservationMapper.fromDto(reservation)));
	}

	// ===== GESTION DES EXCEPTION

	/**
	 * Check if user is admin based on request token
	 * 
	 * @param token
	 * @throws LibraryWebserviceException
	 */
	private void checkAdmin(String token) throws LibraryWebserviceException {
		if (!authMgmt.isAdmin(token)) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}
	}

	private String getExceptionReason(LibraryException le) {
		String reason = le.getErrorCode().getId() + " => Error editing user : ";
		switch (le.getErrorCode()) {
		// FIELD MISSING
		case USER_UNSETTED:
			reason += "no user given";
			break;
		case USER_DOESNT_EXIST:
			reason += "user doesn't exist";
			break;
		case USER_UNSETTED_LASTNAME:
			reason += "lastname not setted";
			break;
		case USER_UNSETTED_FIRSTNAME:
			reason += "firstname not setted";
			break;
		case USER_UNSETTED_EMAIL:
			reason += "email not setted";
			break;
		case USER_UNSETTED_PASSWORD:
			reason += "password not setted";
			break;
		// SIZE_OVERFLOW
		case USER_OVERSIZED_LASTNAME:
			reason += "lastname is too long";
			break;
		case USER_OVERSIZED_FIRSTNAME:
			reason += "firstname is too long";
			break;
		case USER_OVERSIZED_EMAIL:
			reason += "email is too long";
			break;
		case USER_OVERSIZED_PASSWORD:
			reason += "password is too long";
			break;
		// TECHNICAL
		case USER_DUPLICATED_EMAIL:
			reason += "email is already used";
			break;

		// NOT FOUND
		case USER_NOT_FOUND:
			reason += "no user found";
			break;

		default:
			reason += "unknown cause";
			break;
		}
		return reason;
	}

}
