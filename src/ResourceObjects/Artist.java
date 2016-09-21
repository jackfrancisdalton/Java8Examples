package ResourceObjects;

import java.time.LocalDate;

/**
 * Created by Jack F. Dalton on 0017 17 09 2016.
 */
public class Artist {
    private String name;
    private Genre genre;
    private LocalDate dateOfBirth;

    public Artist(String name, Genre genre, LocalDate dateOfBirth) {
        this.name = name;
        this.genre = genre;
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return getName() + " " + getGenre() + " " + getDateOfBirth().toString();
    }
}
