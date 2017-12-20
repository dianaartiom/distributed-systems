package utm.pad.lab3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import utm.pad.lab3.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
