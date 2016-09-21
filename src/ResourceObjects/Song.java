package ResourceObjects;

/**
 * Created by Jack F. Dalton on 0017 17 09 2016.
 */
public class Song {
    private String song;
    private Genre genre;
    private double length;

    public Song(String song, Genre genre, double length) {
        this.song = song;
        this.genre = genre;
        this.length = length;
    }
}
