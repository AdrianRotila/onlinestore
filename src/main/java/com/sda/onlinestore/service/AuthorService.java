package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.entity.Author;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.repository.AuthorRepository;
import com.sda.onlinestore.repository.UserAccountRepository;
import com.sda.onlinestore.transformers.AuthorTransformer;
import com.sda.onlinestore.transformers.UserAccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorTransformer authorTransformer;

    public void addAuthor(AuthorDto authorDto) {
        Author author = authorTransformer.transform(authorDto);
        authorRepository.save(author);
    }

    public List<AuthorDto> getAuthors() {

        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> authorDtoList = new ArrayList<>();

        for (Author author : authors) {
            AuthorDto authorDto = authorTransformer.transformReversed(author);
            authorDtoList.add(authorDto);
        }
        return authorDtoList;
    }
}
