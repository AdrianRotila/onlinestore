package com.sda.onlinestore.mvcController;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.entity.Author;
import com.sda.onlinestore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AuthorMVCController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/viewAuthors")
    public String viewAuthors(Model model){
        List<AuthorDto> authorList = authorService.getAuthors();
        model.addAttribute("authors", authorList);
        return "author-list";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute AuthorDto author){
        authorService.addAuthor(author);
        return "redirect:/author-list";
    }

    @GetMapping("/viewAddAuthors")
    public String viewAddAuthors(Model model){
        AuthorDto authorDto = new AuthorDto();
        model.addAttribute("author", authorDto);
        return "view-add-author";
    }

//    package com.sda.onlinestore.restController;
//
//import com.sda.onlinestore.dto.AuthorDto;
//import com.sda.onlinestore.service.AuthorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//    @Controller
//    public class AuthorController {
//
//        @Autowired
//        private AuthorService authorService;
//
//        @PostMapping("/api/addAuthor")
//        public ResponseEntity addAuthor(@RequestBody AuthorDto authorDto){
//            authorService.addAuthor(authorDto);
//            return ResponseEntity.ok(HttpStatus.OK);
//        }
//
//        @GetMapping("/api/getAuthors")
//        public ResponseEntity getAuthors(){
//            List<AuthorDto> authorDtoList = authorService.getAuthors();
//            return ResponseEntity.ok(authorDtoList);
//        }
//    }
}
