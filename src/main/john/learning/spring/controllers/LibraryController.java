package john.learning.spring.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import john.learning.spring.model.EBook;
import john.learning.spring.model.EBookDAO;
import john.learning.spring.util.ViewNames;

@Controller
public class LibraryController {
	@Resource
	EBookDAO ebookDao;
	
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

	//Handle an AJAX request that asks for the books of the given page
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
}
