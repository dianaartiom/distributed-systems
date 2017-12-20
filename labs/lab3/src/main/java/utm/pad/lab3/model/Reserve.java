package utm.pad.lab3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservers")
public class Reserve {
	@Id
	private String id;
	@DBRef
	private User user;
	@DBRef 
	private Book book;
    private Long reserveDate;
    private Long reserveExpire;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

    public Long getReserveDate() {
        return reserveDate;
	}

    public void setReserveDate(Long reserveDate) {
        this.reserveDate = reserveDate;
	}

    public Long getReserveExpire() {
        return reserveExpire;
	}

    public void setReserveExpire(Long reserveExpire) {
        this.reserveExpire = reserveExpire;
	}
}