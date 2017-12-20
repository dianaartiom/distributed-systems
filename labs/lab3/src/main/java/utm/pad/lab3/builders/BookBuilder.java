package utm.pad.lab3.builders;

import utm.pad.lab3.dtos.BookDTO;
import utm.pad.lab3.model.Book;

import java.util.List;

public class BookBuilder {

    private Book book = new Book();

    private BookBuilder() {
    }

    public BookBuilder title(String title) {
        this.book.setTitle(title);
        return this;
    }

    public BookBuilder authorFirstName(String authorFirstName) {
        this.book.setAuthorFirstName(authorFirstName);
        return this;
    }

    public BookBuilder authorLastName(String authorLastName) {
        this.book.setAuthorLastName(authorLastName);
        return this;
    }

    public BookBuilder genre(String genre) {
        this.book.setGenre(genre);
        return this;
    }

    public BookBuilder description(String description) {
        this.book.setDescription(description);
        return this;
    }

    public BookBuilder publishDate(Long publishDate) {
        this.book.setPublishDate(publishDate);
        return this;
    }

    public BookBuilder pages(Integer pages) {
        this.book.setPages(pages);
        return this;
    }

    public BookBuilder ageLimit(Integer ageLimit) {
        this.book.setAgeLimit(ageLimit);
        return this;
    }

    public BookBuilder count(Integer count) {
        this.book.setCount(count);
        return this;
    }

    public BookBuilder tags(List<String> tags) {
        this.book.setTags(tags);
        return this;
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public Book build() {
        return this.book;
    }

    public static Book get(BookDTO bookDTO) {
        Book book = new Book();
        book.setAgeLimit(bookDTO.getAgeLimit());
        book.setAuthorFirstName(bookDTO.getAuthorFirstName());
        book.setAuthorLastName(bookDTO.getAuthorLastName());
        book.setCount(bookDTO.getCount());
        book.setDescription(bookDTO.getDescription());
        book.setPages(bookDTO.getPages());
        book.setPublishDate(bookDTO.getPublishDate());
        book.setTitle(bookDTO.getTitle());
        return book;
    }
}