package john.learning.spring.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class EBook {
	private @Getter int _id; //this will be set by the database - primary key
	private @Getter String title;
	private @Getter String author;
	private LocalDate publishDate;

	public EBook(String title, String author, LocalDate publishDate){
		_id=0;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
	}
	
	public String getPublishDate() {
		return publishDate.getDayOfMonth() + "/" + publishDate.getMonthValue() + "/" + publishDate.getYear();
	}
}
