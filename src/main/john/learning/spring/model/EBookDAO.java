package john.learning.spring.model;

import java.util.List;

import javax.sql.DataSource;

public interface EBookDAO {
	//set the datasource
	public void setDatasource(DataSource ds);
	//create Ebook
	public boolean createEBook(EBook book);
	//delete
	public boolean deleteEBook(int id);
	//get Ebook
	public EBook getEBook(int id);
	//get Ebooks for page - use of pagination
	public List<EBook> getEBooksPage(int page);
	
}
