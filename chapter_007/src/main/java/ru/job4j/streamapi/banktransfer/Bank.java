package ru.job4j.streamapi.banktransfer;

import ru.job4j.banktransfer.Account;
import ru.job4j.banktransfer.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Set;

/**
 * Bank (stream api)
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 03.11.2018
 */
public class Bank {

    /**
     * Набор счетов пользователей
     */
   private final Map<User, List<Account>> usersAccounts = new TreeMap<>();

    /**
     * Method addUser - добавляет пользователя
     * @param user object
     */
    public void addUser(User user) {
        this.usersAccounts.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Method deleteUser - удаляет пользователя
     * @param user object
     */
    public void deleteUser(User user) {
        this.usersAccounts.remove(user);
    }

    /**
     * Method addAccountToUser - добавляет счет пользователю
     * @param passport string
     * @param account object
     */
    public void addAccountToUser(String passport, Account account) {
        this.usersAccounts.keySet().stream().filter(user ->
                user.getPassport().equals(passport))
                .limit(1)
                .forEach(user -> this.usersAccounts.get(user).add(account));
    }

    /**
     * Method deleteAccountFromUser - удаляет счет пользователя
     * @param passport string
     * @param account object
     */
    public void deleteAccountFromUser(String passport, Account account) {
        this.usersAccounts.keySet().stream().filter(user ->
                user.getPassport().equals(passport))
                .limit(1)
                .forEach(user -> this.usersAccounts.get(user).remove(account));
    }

    /**
     * Method getUserAccounts - получает список счетов пользователя
     * @param passport string
     * @return accounts list
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public List<Account> getUserAccounts(String passport) {
        return this.usersAccounts.get(
                this.usersAccounts.keySet().stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst().get());
    }

    /**
     * Method getUsers
     * @return users set
     */
    public Set<User> getUsers() {
        return this.usersAccounts.keySet();
    }

    /**
     * Method getAccount
     * @param passport string
     * @param requisite string
     * @return account
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Account getAccount(String passport, String requisite) {
        return this.getUserAccounts(passport).stream()
                .filter(account -> account.getRequisites().equals(requisite))
                .findFirst().get();
    }

    /**
     * Method transferMoney - переводит деньги с одного счета на другой
     * @param sourcePassport паспорт пользователя отправителя
     * @param sourceRequisite реквизиты счета пользователя отправителя
     * @param destinationPassport паспорт пользователя получателя
     * @param destinationRequisite реквизиты счета пользователя получателя
     * @param amount сумма перевода
     * @return true или false (не хватает денег на счете пользователя отправителя)
     */
    @SuppressWarnings("Duplicates")
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        Account sourceAccount = this.getAccount(sourcePassport, sourceRequisite);
        Account destinationAccount = this.getAccount(destinationPassport, destinationRequisite);
        if (sourceAccount != null && destinationAccount != null && sourceAccount.getValue() >= amount) {
            double sourceValue = sourceAccount.getValue() - amount;
            getUserAccounts(sourcePassport).set(
                    getUserAccounts(sourcePassport).indexOf(sourceAccount),
                    new Account(sourceRequisite, sourceValue)
            );
            double destinationValue = destinationAccount.getValue() + amount;
            getUserAccounts(destinationPassport).set(
                    getUserAccounts(destinationPassport).indexOf(destinationAccount),
                    new Account(destinationRequisite, destinationValue)
            );
            result = true;
        }
        return result;
    }
}