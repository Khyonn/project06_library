package fr.nmocs.library.business.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.nmocs.library.business.UserManagement;
import fr.nmocs.library.consumer.UserRepository;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.error.ErrorCode;
import fr.nmocs.library.model.error.LibraryBusinessException;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;

@Service
public class UserManagementImpl implements UserManagement {

	@Autowired
	private UserRepository userRepo;

	@Transactional
	private User checkAndSaveUser(User user) throws LibraryException {
		checkFields(user);
		formatFields(user);
		try {
			return userRepo.save(user);
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.USER_DUPLICATED_EMAIL);
		}
	}

	@Override
	@Transactional
	public User createUser(User user) throws LibraryException {
		if (user != null) {
			user.setId(null);
		}
		return checkAndSaveUser(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) throws LibraryException {
		return checkAndSaveUser(user);
	}

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

	private void checkFields(User user) throws LibraryBusinessException {
		if (user == null) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED);
		}
		if (StringUtils.isBlank(user.getLastName())) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED_LASTNAME);
		}
		if (user.getLastName().length() > 255) {
			throw new LibraryBusinessException(ErrorCode.USER_OVERSIZED_LASTNAME);
		}
		if (StringUtils.isBlank(user.getFirstName())) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED_FIRSTNAME);
		}
		if (user.getFirstName().length() > 255) {
			throw new LibraryBusinessException(ErrorCode.USER_OVERSIZED_LASTNAME);
		}
		if (StringUtils.isBlank(user.getEmail())) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED_EMAIL);
		}
		if (user.getEmail().length() > 255) {
			throw new LibraryBusinessException(ErrorCode.USER_OVERSIZED_LASTNAME);
		}
		if (StringUtils.isBlank(user.getPassword())) {
			throw new LibraryBusinessException(ErrorCode.USER_UNSETTED_PASSWORD);
		}
		if (user.getPassword().length() > 255) {
			throw new LibraryBusinessException(ErrorCode.USER_OVERSIZED_LASTNAME);
		}
	}

	private void formatFields(User user) {
		user.setLastName(user.getLastName().trim());
		user.setFirstName(user.getFirstName().trim());
		user.setEmail(user.getEmail().trim());
	}

}
