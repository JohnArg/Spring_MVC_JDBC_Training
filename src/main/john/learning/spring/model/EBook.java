package john.learning.spring.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class EBook {
	private @Getter String title;
	private @Getter String author;
	private LocalDate publishDate;
	
	public String getPublishDate() {
		return publishDate.getDayOfMonth() + "/" + publishDate.getMonthValue() + "/" + publishDate.getYear();
	}
}
