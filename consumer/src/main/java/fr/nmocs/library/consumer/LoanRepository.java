package fr.nmocs.library.consumer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nmocs.library.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

	List<Loan> findByBorrowerId(Integer userId);

	List<Loan> findByReturnDateIsNull();

	boolean existsByBookSampleIdAndReturnDateIsNull(Integer bookSampleId);

	boolean existsByIdNotAndBookSampleIdAndReturnDateIsNull(Integer id, Integer bookSampleId);

	/**
	 * For given user and book id Returns if user is borrowing a booksample of given
	 * book id
	 * 
	 * @param userId
	 * @param bookId
	 * @return
	 */
	boolean existsByBorrowerIdAndBookSampleBookIdAndReturnDateIsNull(Integer userId, Integer bookId);

	/**
	 * Returns not-ended loans for a given book
	 * 
	 * @param bookId
	 * @return
	 */
	List<Loan> findByBookSampleBookIdAndReturnDateIsNull(Integer bookId);
}
