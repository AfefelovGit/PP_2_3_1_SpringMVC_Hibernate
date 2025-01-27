package webservice.api.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

import static java.lang.String.format;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "FirstName cannot be empty.")
    @Size(min = 2, max = 30, message = "FirstName must be from 2 to 30 characters.")
    @Column(name = "firstName", nullable = false, length = 30)
    private String firstName;

    @NotEmpty(message = "LastName cannot be empty.")
    @Size(min = 2, max = 30, message = "LastName must be from 2 to 30 characters.")
    @Column(name = "lastName", nullable = false, length = 30)
    private String lastName;

    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Value does not match Email.")
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {

        return format("id: %4d %10s %12s %20s", id, firstName, lastName, email);
    }
}
