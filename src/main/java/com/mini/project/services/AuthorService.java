package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //Add service
    public Long addAuthor(String name, String nationality, String biography){
        Author author =  new Author();
        author.setIsActive(true);
        author.setCreatedDate(new Date());
        author.setName(name);
        author.setNationality(nationality);
        author.setBiography(biography);
        author = authorRepository.save(author);
        return author.getId();
    }

    //Get All authors service
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    //Get Author By Id service
    public Author getById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent() && author.get().getIsActive()) {
            return author.get();
        }
        return new Author();
    }

    //Update service
    public Author updateAuthor(Long id, String updateName, String updateNationality, String updateBiography) throws Exception{
        Author authorToUpdate =  authorRepository.getById(id);
        if(authorToUpdate==null){
            throw new Exception("Author is not found by the id");
        }
        authorToUpdate.setUpdatedDate(new Date());
        authorToUpdate.setName(updateName);
        authorToUpdate.setNationality(updateNationality);
        authorToUpdate.setBiography(updateBiography);
        authorToUpdate = authorRepository.save(authorToUpdate);
        return authorToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Author deleteAuthor = authorRepository.getById(id);
        if(deleteAuthor == null){
            return false;
        }
        deleteAuthor.setIsActive(false);
        deleteAuthor.setUpdatedDate(new Date());
        authorRepository.save(deleteAuthor);
        return true;
    }


}
