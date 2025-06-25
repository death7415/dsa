package Java8.entityAndDao;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private double rating;

    public Book(int id, String title, String author, String genre, int publicationYear, double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.rating = rating;
    }

}

class BookMapDao {

    private static final Map<Integer, Book> BOOK_MAP = new HashMap<>();

    static {
        BOOK_MAP.put(1, new Book(
                1,
                "Effective Java",
                "Joshua Bloch",
                "Programming",
                2008,
                4.6
        ));

        BOOK_MAP.put(2, new Book(
                2,
                "Clean Code",
                "Robert C. Martin",
                "Programming",
                2009,
                4.4
        ));

        BOOK_MAP.put(3, new Book(
                3,
                "Design Patterns: Elements of Reusable Object-Oriented Software",
                "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides",
                "Programming",
                1994,
                4.2
        ));

        BOOK_MAP.put(4, new Book(
                4,
                "To Kill a Mockingbird",
                "Harper Lee",
                "Fiction",
                1960,
                4.7
        ));

        BOOK_MAP.put(5, new Book(
                5,
                "1984",
                "George Orwell",
                "Fiction",
                1949,
                4.3
        ));

        BOOK_MAP.put(6, new Book(
                6,
                "The Pragmatic Programmer",
                "Andrew Hunt, David Thomas",
                "Programming",
                1999,
                4.5
        ));
    }

    public static Map<Integer, Book> getBookMap() {
        return BOOK_MAP;
    }
}

