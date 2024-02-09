package rs.ac.uns.ftn.siit.op.json.task1;

import java.util.List;

public class Bookstore {
	List<Book> books;
	List<Magazine> magazines;

	public Bookstore() {

	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Magazine> getMagazines() {
		return magazines;
	}

	public void setMagazines(List<Magazine> magazines) {
		this.magazines = magazines;
	}

	@Override
	public String toString() {
		return "Bookstore [books=" + books + ", magazines=" + magazines + "]";
	}

}
