package john.learning.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

public class EBookRowMapper implements RowMapper<EBook>{
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public EBook mapRow(ResultSet rs, int rowNum) throws SQLException {
		EBook book;
		String publishDateStr = rs.getString("published"); //get it as a String. If you get it as a Date, it will display a wrong date
		LocalDate publishDate = LocalDate.parse(publishDateStr, formatter);
		book = new EBook(rs.getString("title"), rs.getString("author"), publishDate);
		book.set_id(rs.getInt("_id"));
		return book;
	}

}
