package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Represents a User entity in the fitness tracker application.
 * Maps to the "users" table in the database.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /**
     * The unique identifier for the user.
     * Automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    @Setter(AccessLevel.NONE)
    private Long id;

    /**
     * The first name of the user.
     * Cannot be null.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The last name of the user.
     * Cannot be null.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * The birthdate of the user.
     * Cannot be null.
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * The email address of the user.
     * Must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Constructor to create a new User instance with mandatory fields.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param birthdate the user's birthdate
     * @param email the user's email address
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}
