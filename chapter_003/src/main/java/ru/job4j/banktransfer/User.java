package ru.job4j.banktransfer;

/**
 * User - пользователь
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {

    /**
     * Имя
     */
    private String name;

    /**
     * Паспорт
     */
    private String passport;

    /**
     * Конструктор
     * @param name
     * @param passport
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!name.equals(user.name)) {
            return false;
        }
        return passport.equals(user.passport);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + passport.hashCode();
        return result;
    }

    /**
     * Method getPassport
     * @return
     */
    public String getPassport() {
        return this.passport;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public int compareTo(User user) {
        return this.name.compareTo(user.name);
    }
}
