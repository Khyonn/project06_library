package fr.nmocs.library.webservice.impl;

import java.util.List;

import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.webservice.BookService;
import fr.nmocs.library.webservice.error.WebserviceException;


public class BookServiceImpl implements BookService {

	@Override
	public Book createBook(Book book) throws WebserviceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book updateBook(Book book) throws WebserviceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findBookById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findBookByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findBookByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookSample createBookSample(BookSample bookSample) throws WebserviceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookSample updateBookSample(BookSample bookSample) throws WebserviceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookSample findBookSampleById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookSample> findBookSampleByBookId(Integer bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
