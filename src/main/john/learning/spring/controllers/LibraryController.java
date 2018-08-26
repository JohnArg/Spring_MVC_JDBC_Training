package john.learning.spring.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import john.learning.spring.model.EBook;
import john.learning.spring.model.EBookDAO;
import john.learning.spring.util.ViewNames;

@Controller
public class LibraryController {
	@Resource
	EBookDAO ebookDao;
	
	//get page 1 for now
	@GetMapping("list")
	public String getPageList(Model model) {
		List<EBook> books = ebookDao.getEBooksPage(1);
		model.addAttribute("book_list", books);
		return ViewNames.LIST_PAGE;
	}
	
}
