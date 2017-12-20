package utm.pad.lab3.builders;

import utm.pad.lab3.dtos.PersonDTO;
import utm.pad.lab3.model.User;

public class UserBuilder {

    private User user = new User();

    private UserBuilder() {
    }

    public UserBuilder firstname(String firstName) {
        this.user.setFirstname(firstName);
        return this;
    }

    public UserBuilder lastname(String lastName) {
        this.user.setLastname(lastName);
        return this;
    }

    public UserBuilder email(String email) {
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder birthDate(Long birthdate) {
        this.user.setBirthDate(birthdate);
        return this;
    }

    public UserBuilder countryCode(String countryCode) {
        this.user.setCountryCode(countryCode);
        return this;
    }

    public UserBuilder phone(Integer phone) {
        this.user.setPhone(phone);
        return this;
    }

    public UserBuilder password(String password) {
        this.user.setPassword(password);
        return this;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public User build() {
        return this.user;
    }

    public static User get(PersonDTO person) {
        User user = new User();
        user.setFirstname(person.getFirstName());
        user.setLastname(person.getLastName());
        user.setEmail(person.getEmail());

        user.setPassword(person.getPassword());
        user.setPhone(person.getPhone());
        user.setCountryCode(person.getCountryCode());
        user.setBirthDate(person.getBirthDate());
        return user;
    }
}