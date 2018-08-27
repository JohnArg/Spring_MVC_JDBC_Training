package john.learning.spring.model;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("ebookDao")
public class EBookDAOImpl implements EBookDAO {

	private JdbcTemplate template;
	private final int PAGE_TILES = 10;
	
	public EBookDAOImpl() {
		
	}
	
	@Resource
	@Override
	public void setDatasource(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public boolean createEBook(EBook book) {
		String query = "INSERT INTO Ebooks (title, author, published) VALUES (?,?,?);";
		Object[] params = {book.getTitle(), book.getAuthor(), book.getPublishDate()};
		return template.update(query, params, new EBookRowMapper()) == 1; //num of rows affected
	}

	@Override
	public boolean deleteEBook(int id) {
		String query = "DELETE FROM Ebooks WHERE _id = ?";
		Object[] params = {id};
		return template.update(query, params, new EBookRowMapper()) == 1;
	}

	@Override
	public EBook getEBook(int id) {
		String query = "SELECT FROM Ebooks WHERE _id = ?";
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

	@Override
	public int getNumOfPages() {
		int rowsTotal = template.query("SELECT COUNT(*) FROM Ebooks", new IntegerRowMapper()).get(0);
		double pagesFit = (rowsTotal*1.0) / PAGE_TILES;
		Double pagesFitDbl =  new Double(Math.ceil(pagesFit));
		int pagesToShow = pagesFitDbl.intValue();
		return pagesToShow;
	}

}
