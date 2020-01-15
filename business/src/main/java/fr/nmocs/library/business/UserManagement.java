package fr.nmocs.library.business;

import java.util.List;

import fr.nmocs.library.model.Admin;
import fr.nmocs.library.model.Reservation;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;

public interface UserManagement {

	User createUser(User user) throws LibraryException;

	User updateUser(User user) throws LibraryException;

	Admin grantAdminRightsToUser(Integer userId) throws LibraryException;

	User downgradeAdminToBasicUser(Integer adminId) throws LibraryException;

	User findById(Integer id) throws LibraryTechnicalException;

	User findByEmail(String email) throws LibraryTechnicalException;

	List<User> findByName(String name) throws LibraryTechnicalException;

	Reservation create(Reservation reservation);
}
