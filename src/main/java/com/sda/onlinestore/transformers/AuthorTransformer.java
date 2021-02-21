package com.sda.onlinestore.transformers;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.entity.Author;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AuthorTransformer {

    public Author transform (AuthorDto authorDto){
        Author author = new Author();
        BeanUtils.copyProperties(authorDto, author);
        return author;
    }

    public AuthorDto transformReversed(Author author){
        AuthorDto authorDto = new AuthorDto();
        BeanUtils.copyProperties(author, authorDto);
        return authorDto;
    }
}
