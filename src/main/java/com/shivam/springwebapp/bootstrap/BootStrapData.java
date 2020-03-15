package com.shivam.springwebapp.bootstrap;

import com.shivam.springwebapp.model.Author;
import com.shivam.springwebapp.model.Book;
import com.shivam.springwebapp.model.Publisher;
import com.shivam.springwebapp.repositories.AuthorRepository;
import com.shivam.springwebapp.repositories.BookRepository;
import com.shivam.springwebapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("Oxford");
        publisher.setCity("New York");
        publisher.setState("US");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        authorRepository.save(eric);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        bookRepository.save(ddd);
        publisherRepository.save(publisher);




        System.out.println(publisherRepository.count());

    }
}
