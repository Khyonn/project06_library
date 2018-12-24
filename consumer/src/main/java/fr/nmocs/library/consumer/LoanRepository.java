package fr.nmocs.library.consumer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nmocs.library.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

	List<Loan> findByBorrowerId(Integer userId);

	List<Loan> findByReturnDateIsNull();

}
