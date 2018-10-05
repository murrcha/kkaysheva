package ru.job4j.parser;

import java.util.Date;
import java.util.Objects;

/**
 * Vacancy
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Vacancy {

    private int id;

    private final String title;

    private final String link;

    private final String author;

    private final Date date;

    public Vacancy(String title, String link, String author, Date date) {
        this.title = title;
        this.link = link;
        this.author = author;
        this.date = date;
    }

    public Vacancy(int id, String title, String link, String author, Date date) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.author = author;
        this.date = date;
    }

    @SuppressWarnings("unused")
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(title, vacancy.title)
                && Objects.equals(link, vacancy.link)
                && Objects.equals(author, vacancy.author)
                && Objects.equals(date, vacancy.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, author, date);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", author='" + author + '\''
                + ", date=" + date
                + '}';
    }
}
