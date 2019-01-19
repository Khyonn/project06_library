package fr.nmocs.library.business.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.nmocs.library.business.UserManagement;
import fr.nmocs.library.consumer.AdminRepository;
import fr.nmocs.library.consumer.UserRepository;
import fr.nmocs.library.model.Admin;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.constants.UserStatus;
import fr.nmocs.library.model.constants.UserType;
import fr.nmocs.library.model.error.ErrorCode;
import fr.nmocs.library.model.error.LibraryBusinessException;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;

@Service
public class UserManagementImpl implements UserManagement {

	private static final Integer DATABASE_STRING_SIZE = 255;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AdminRepository adminRepo;

	// ========== CREATES AND UPDATES

	@Transactional
	private User formatAndSaveUser(User user) throws LibraryException {
		formatFields(user);
		try {
			return userRepo.save(user);
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.USER_DUPLICATED_EMAIL);
		}
	}

	@Override
	public User createUser(User user) throws LibraryException {
		if (user != null) {
			user.setId(null);
			user.setStatus(UserStatus.ACTIVE.getValue());
		} else {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED);
		}
		checkFields(user);
		return formatAndSaveUser(user);
	}

	@Override
	public User updateUser(User user) throws LibraryException {
		if (user == null) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED);
		}
		if (user.getId() == null || user.getId().equals(0)) {
			throw new LibraryBusinessException(ErrorCode.USER_DOESNT_EXIST);
		}
		User databaseUser = findById(user.getId());
		mergeUser(databaseUser, user);
		checkFields(databaseUser);
		return formatAndSaveUser(databaseUser);
	}

	@Override
	@Transactional
	public Admin grantAdminRightsToUser(Integer userId) throws LibraryException {
		userRepo.updateUserType(userId, UserType.ADMIN.getValue());
		return adminRepo.findById(userId).orElse(null);
	}

	@Override
	@Transactional
	public User downgradeAdminToBasicUser(Integer adminId) throws LibraryException {
		userRepo.updateUserType(adminId, UserType.BASIC.getValue());
		return userRepo.findById(adminId).orElse(null);
	}

	// ========== READERS

	@Override
	public User findById(Integer id) throws LibraryTechnicalException {
		try {
			return userRepo.findById(id).get();
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.USER_NOT_FOUND);
		}
	}

	@Override
	public User findByEmail(String email) throws LibraryTechnicalException {
		try {
			return userRepo.findByEmailIgnoreCase(email).get();
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.USER_NOT_FOUND);
		}
	}

	@Override
	public List<User> findByName(String name) throws LibraryTechnicalException {
		try {
			return userRepo.findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(name, name);
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.USER_NOT_FOUND);
		}
	}

	// ===== UTILS

	/**
	 * Check if user fields are setted and
	 * 
	 * @param user
	 * @throws LibraryBusinessException
	 */
	private void checkFields(User user) throws LibraryBusinessException {
		// USER LASTNAME
		if (StringUtils.isBlank(user.getLastName())) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED_LASTNAME);
		}
		if (user.getLastName().length() > DATABASE_STRING_SIZE) {
			throw new LibraryBusinessException(ErrorCode.USER_OVERSIZED_LASTNAME);
		}
		// USER FIRSTNAME
		if (StringUtils.isBlank(user.getFirstName())) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED_FIRSTNAME);
		}
		if (user.getFirstName().length() > DATABASE_STRING_SIZE) {
			throw new LibraryBusinessException(ErrorCode.USER_OVERSIZED_FIRSTNAME);
		}
		// USER EMAIL
		if (StringUtils.isBlank(user.getEmail())) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED_EMAIL);
		}
		if (user.getEmail().length() > DATABASE_STRING_SIZE) {
			throw new LibraryBusinessException(ErrorCode.USER_OVERSIZED_EMAIL);
		}
		User databaseUser = userRepo.findByEmailIgnoreCase(user.getEmail()).orElse(null);
		if (databaseUser != null && !databaseUser.getId().equals(user.getId())) {
			throw new LibraryBusinessException(ErrorCode.USER_DUPLICATED_EMAIL);
		}
		// USER PASSWORD
		if (StringUtils.isBlank(user.getPassword())) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED_PASSWORD);
		}
		if (user.getPassword().length() > DATABASE_STRING_SIZE) {
			throw new LibraryBusinessException(ErrorCode.USER_OVERSIZED_PASSWORD);
		}
	}

	/**
	 * Trim fields on user entity
	 * 
	 * @param user
	 */
	private void formatFields(User user) {
		user.setLastName(user.getLastName().trim());
		user.setFirstName(user.getFirstName().trim());
		user.setEmail(user.getEmail().trim());
		if (user.getStatus() != null && (!user.getStatus().equals(UserStatus.ACTIVE.getValue())
				&& !user.getStatus().equals(UserStatus.UNACTIVE.getValue()))) {
			user.setStatus(UserStatus.UNACTIVE.getValue());
		}
	}

	/**
	 * Merge user fields with database user fields
	 * 
	 * @param user
	 * @param databaseUser
	 */
	private void mergeUser(User databaseUser, User user) {
		if (!StringUtils.isBlank(user.getLastName())) {
			databaseUser.setLastName(user.getLastName());
		}
		if (!StringUtils.isBlank(user.getFirstName())) {
			databaseUser.setFirstName(user.getFirstName());
		}
		if (!StringUtils.isBlank(user.getPassword())) {
			databaseUser.setPassword(user.getPassword());
		}
		if (!StringUtils.isBlank(user.getEmail())) {
			databaseUser.setEmail(user.getEmail());
		}
		if (user.getStatus() != null && (user.getStatus().equals(UserStatus.ACTIVE.getValue())
				|| user.getStatus().equals(UserStatus.UNACTIVE.getValue()))) {
			databaseUser.setStatus(user.getStatus());
		}

	}

}
