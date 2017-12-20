package utm.pad.lab3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import utm.pad.lab3.model.Book;

import java.util.List;


@Repository
public interface BookRepository extends MongoRepository<Book,String>{
    List<Book> findAll();
    List<Book> findByTitle(String title);
    Book findBookById(String id);
    List<Book> findAllByAuthorFirstName(String authorFirstName);
    List<Book> findAllByAuthorLastName(String authorLastName);
    List<Book> findAllByGenre(String genre);
}