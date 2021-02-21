package com.sda.onlinestore.restController;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/api/addAuthor")
    public ResponseEntity addAuthor(@RequestBody AuthorDto authorDto){
        authorService.addAuthor(authorDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/api/getAuthors")
    public ResponseEntity getAuthors(){
        List<AuthorDto> authorDtoList = authorService.getAuthors();
        return ResponseEntity.ok(authorDtoList);
    }
}
