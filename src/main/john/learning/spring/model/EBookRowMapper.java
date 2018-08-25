package john.learning.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EBookRowMapper implements RowMapper<EBook>{

	@Override
	public EBook mapRow(ResultSet rs, int rowNum) throws SQLException {
		EBook book;
		book = new EBook(rs.getString("title"), rs.getString("author"), rs.getDate("published").toLocalDate());
		book.set_id(rs.getInt("_id"));
		return book;
	}

}
