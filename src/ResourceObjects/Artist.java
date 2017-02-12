package ResourceObjects;

import java.time.LocalDate;

public class Artist implements Comparable<Artist>  {
    private String nickname;
    private String name;
    private Genre genre;
    private LocalDate dateOfBirth;

    public Artist() {}

    public Artist(String name, Genre genre, LocalDate dateOfBirth) {
        this.name = name;
        this.genre = genre;
        this.dateOfBirth = dateOfBirth;
    }

    public Artist(String name, String nickname, Genre genre, LocalDate dateOfBirth) {
        this.name = name;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Artist clone() {
        return new Artist(
                this.getName(),
                this.getNickname(),
                this.getGenre(),
                this.getDateOfBirth()
        );
    }

    @Override
    public String toString() {
        return getName() + " " + getGenre() + " " + getDateOfBirth().toString();
    }

    @Override
    public int compareTo(Artist o) {
        return this.getName().compareTo(o.getName());
    }
}
