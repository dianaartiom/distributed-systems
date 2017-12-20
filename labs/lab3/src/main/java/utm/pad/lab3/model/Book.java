package utm.pad.lab3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="books")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }


}