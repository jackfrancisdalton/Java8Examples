package ResourceObjects;

import java.util.List;

/**
 * Created by Jack F. Dalton on 0017 17 09 2016.
 */
public class Album {
    private String name;
    private List<Song> songs;
    private Genre genre;
    private Artist artist;

    public Album(String name, List<Song> songs, Genre genre, Artist artist) {
        this.name = name;
        this.songs = songs;
        this.genre = genre;
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
