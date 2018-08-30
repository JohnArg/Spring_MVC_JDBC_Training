package john.learning.spring.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import john.learning.spring.model.EBook;
import john.learning.spring.model.EBookDAO;
import john.learning.spring.util.ViewNames;

@Controller
public class LibraryController {
	@Resource
	private EBookDAO ebookDao;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
	
	//Get the library page
	@GetMapping("library")
	public String getLibraryPage(Model model) {
		//Get number of total pages to display
		int pagesToShow = ebookDao.getNumOfPages();
		//get the books of page 1 by default
		List<EBook> books;
		books = ebookDao.getEBooksPage(1);		
		model.addAttribute("pages", pagesToShow);
		model.addAttribute("book_list", books);
		model.addAttribute("selected_page", 1);
		return ViewNames.TILE_PAGE;
	}

	//Handle an AJAX requests ===========================================================================
	
	//Get a page of books, by page number
	@ResponseBody
	@GetMapping("library/{page}")
	public String getBooksForPage(@PathVariable int page) {
		if(page <= 0 ) {
			return "[{message : 'Error : no page number provided'}]";
		}
		List<EBook> books;
		books = ebookDao.getEBooksPage(page);
		JSONArray jlist = new JSONArray(books);
		return jlist.toString();
	}
	
	//Handle "add book" ajax requests
	@ResponseBody
	@PostMapping("add_book")
	public ResponseEntity<String> addBook(@RequestParam Map<String,String> params){
		String title = params.get("title");
		String author = params.get("author");
		String date = params.get("publishDate");
		boolean badRequest = false;
		//Check for empty values
		if((title == null) || (author == null) || (date == null)) {
			badRequest = true;
		}
		if((title == "") || (author == "") || (date == "")) {
			badRequest = true;
		}
		if(badRequest) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		//if values not empty
		try {
			LocalDate publishDate = LocalDate.parse(date, formatter);
			EBook book = new EBook(title, author, publishDate);
			ebookDao.createEBook(book);
		}catch(Exception e) {
			System.out.println("Problem parsing data : "+ e.getMessage());
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	//Handle "delete book" ajax requests
	@ResponseBody
	@PostMapping("delete_book")
	public ResponseEntity<String> deleteBook(@RequestParam int id){
		try {
			ebookDao.deleteEBook(id);
		}catch(Exception e) {
			System.out.println("Error in deleting book "+e.getMessage());
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
