package ru.job4j.banktransfer;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

/**
 * Bank - банк
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Bank {

    /**
     * Набор счетов пользователей
     */
   private Map<User, List<Account>> usersAccounts = new TreeMap<>();

    /**
     * Method addUser - добавляет пользователя
     * @param user
     */
    public void addUser(User user) {
        this.usersAccounts.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Method deleteUser - удаляет пользователя
     * @param user
     */
    public void deleteUser(User user) {
        this.usersAccounts.remove(user);
    }

    /**
     * Method addAccountToUser - добавляет счет пользователю
     * @param passport
     * @param account
     */
    public void addAccountToUser(String passport, Account account) {
        Set<User> users = this.usersAccounts.keySet();
        for (User user : users) {
            if (user.getPassport().equals(passport)) {
                this.usersAccounts.get(user).add(account);
                break;
            }
        }
    }

    /**
     * Method deleteAccountFromUser - удаляет счет пользователя
     * @param passport
     * @param account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        Set<User> users = this.usersAccounts.keySet();
        for (User user : users) {
            if (user.getPassport().equals(passport)) {
                this.usersAccounts.get(user).remove(account);
                break;
            }
        }
    }

    /**
     * Method getUserAccounts - получает список счетов пользователя
     * @param passport
     * @return
     */
    public List<Account> getUserAccounts(String passport) {
        Set<User> users = this.usersAccounts.keySet();
        List<Account> accounts = new ArrayList<>();
        for (User user : users) {
            if (user.getPassport().equals(passport)) {
                accounts = this.usersAccounts.get(user);
            }
        }
        return accounts;
    }

    /**
     * Method getUsers
     * @return
     */
    public Set<User> getUsers() {
        return this.usersAccounts.keySet();
    }

    /**
     * Method getAccount
     * @param passport
     * @param requisite
     * @return
     */
    public Account getAccount(String passport, String requisite) {
        Account result = null;
        List<Account> accounts = this.getUserAccounts(passport);
        for (Account account : accounts) {
            if (account.getRequisites().equals(requisite)) {
                result = account;
                break;
            }
        }
        return result;
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