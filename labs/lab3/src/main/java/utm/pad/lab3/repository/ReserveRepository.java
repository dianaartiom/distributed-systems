package utm.pad.lab3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import utm.pad.lab3.model.Book;
import utm.pad.lab3.model.Reserve;
import utm.pad.lab3.model.User;

import java.util.List;

@Repository
public interface ReserveRepository extends MongoRepository<Reserve, String> {

    Reserve insert(Reserve reserve);
    List<Reserve> findAllByBook(Book book);
    List<Reserve> findAllByUser(User user);
    List<Reserve> findAllByReserveDateBeforeAndReserveExpireAfter(Long before, Long after);
    List<Reserve> findDistinctByReserveDateAndReserveExpire(Long before, Long after);

    void delete(Reserve reserve);
}