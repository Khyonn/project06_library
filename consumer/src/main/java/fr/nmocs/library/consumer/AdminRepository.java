package fr.nmocs.library.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nmocs.library.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
