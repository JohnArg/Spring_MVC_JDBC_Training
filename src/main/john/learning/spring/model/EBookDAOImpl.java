package john.learning.spring.model;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class EBookDAOImpl implements EBookDAO {

	private JdbcTemplate template;
	
	@Resource(name="datasource")
	@Override
	public void setDatasource(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public boolean createEBook(EBook book) {
		String query = "INSERT INTO EBooks (title, author, published) VALUES (?,?,?);";
		Object[] params = {book.getTitle(), book.getAuthor(), book.getPublishDate()};
		return template.update(query, params, new EBookRowMapper()) == 1; //num of rows affected
	}

	@Override
	public boolean deleteEBook(int id) {
		String query = "DELETE FROM EBooks WHERE _id = ?";
		Object[] params = {id};
		return template.update(query, params, new EBookRowMapper()) == 1;
	}

	@Override
	public EBook getEBook(int id) {
		String query = "SELECT FROM EBooks WHERE _id = ?";
		Object[] params = {id};
		List<EBook> books =  template.query(query, params, new EBookRowMapper());
		if(books.size() > 1) {
			System.err.println("Multiple books with the same id detected. The id is : "+id);
		}
		return books.get(0);
	}

	//Will return 10 ebooks per page
	@Override
	public List<EBook> getEBooksPage(int page) {
		String query = "SELECT * FROM Ebooks LIMIT ?, ? ;";
		int pageStart = (page-1)*10;
		int pageStop = pageStart+10;
		Object[] params = {pageStart, pageStop};
		List<EBook> books = template.query(query, params, new EBookRowMapper());
		return books;
	}

}