package john.learning.spring.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class EBook {
	private @Getter @Setter int _id; //this will be set by the database - primary key
	private @Getter @Setter String title;
	private @Getter @Setter String author;
	private @Setter LocalDate publishDate;

	public EBook(String title, String author, LocalDate publishDate){
		_id=0;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
	}
	
	public String getPublishDate() {
		return publishDate.getYear() + "-" + publishDate.getMonthValue() + "-" + publishDate.getDayOfMonth();
	}
	
}
