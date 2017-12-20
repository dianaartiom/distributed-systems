package utm.pad.lab3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utm.pad.lab3.dtos.PersonDTO;
import utm.pad.lab3.dtos.ReserveDTO;
import utm.pad.lab3.model.Book;
import utm.pad.lab3.model.Reserve;
import utm.pad.lab3.model.User;
import utm.pad.lab3.repository.BookRepository;
import utm.pad.lab3.repository.ReserveRepository;
import utm.pad.lab3.repository.UserRepository;

import java.util.List;

@Service
public class ReserveService {

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    public Reserve insert(ReserveDTO reserveDTO, PersonDTO personDTO) {
        Reserve reserve = new Reserve();
        reserve.setBook(bookRepository.findBookById(reserveDTO.getBookId()));
        reserve.setUser(userRepository.findByEmail(personDTO.getEmail()));
        return reserveRepository.insert(reserve);
    }

    public List<Reserve> findAll() {
        return reserveRepository.findAll();
    }

    public List<Reserve> findAllByBook(Book book) {
        return reserveRepository.findAllByBook(book);
    }

    public List<Reserve> findAllByUser(User user) {
        return reserveRepository.findAllByUser(user);
    }

    public List<Reserve> findAvailableBetweenDates(Long startingDate, Long  endindingDate) {
        return reserveRepository.findAllByReserveDateBeforeAndReserveExpireAfter(startingDate, endindingDate);
    }

    public Reserve update(Reserve reserve) {
        return reserveRepository.save(reserve);
    }
}