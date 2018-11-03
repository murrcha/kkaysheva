package ru.job4j.streamapi.banktransfer;

import org.junit.Test;
import ru.job4j.banktransfer.Account;
import ru.job4j.banktransfer.User;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Bank Test (stream api)
 *
 * @author Ksenya Kyahseva (murrcha@me.com)
 * @version 0.1
 * @since 03.11.2018
 */
public class BankTest {

    /**
     * Test addUser to map
     */
    @Test
    public void whenAddNewUserInMapThenMapContainsThisUser() {
        Bank bank = new Bank();
        User user = new User("Иванов Иван Иванович", "1234567890");
        User userClone = new User("Иванов Иван Иванович", "1234567890");
        bank.addUser(user);
        bank.addUser(userClone);
        assertThat(bank.getUsers().size(), is(1));
        assertThat(bank.getUsers().contains(user), is(true));
    }

    /**
     * Test deleteUser from map
     */
    @Test
    public void whenDeleteUserFromMapThenMapNotContainsThisUser() {
        Bank bank = new Bank();
        User user = new User("Иванов Иван Иванович", "1234567890");
        bank.addUser(user);
        assertThat(bank.getUsers().size(), is(1));
        assertThat(bank.getUsers().contains(user), is(true));
        bank.deleteUser(user);
        assertThat(bank.getUsers().isEmpty(), is(true));
        assertThat(bank.getUsers().contains(user), is(false));
    }

    /**
     * Test addAccountToUser
     */
    @Test
    public void whenAddAccountToUserThenUserListAccountsContainsThisAccount() {
        Bank bank = new Bank();
        User user = new User("Иванов Иван Иванович", "1234567890");
        Account account = new Account("777111555222333", 100D);
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account);
        List<Account> accounts = bank.getUserAccounts(user.getPassport());
        assertThat(accounts.size(), is(1));
        assertThat(accounts.get(0).getRequisites(), is("777111555222333"));
        assertThat(accounts.get(0).getValue(), is(100D));
    }

    /**
     * Test addAccountToUser
     */
    @Test
    public void whenDeleteAccountFromUserThenUserListAccountsNotContainsThisAccount() {
        Bank bank = new Bank();
        User user = new User("Иванов Иван Иванович", "1234567890");
        Account account1 = new Account("777111555222333", 0D);
        Account account2 = new Account("777111555222000", 1000D);
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account1);
        bank.addAccountToUser(user.getPassport(), account2);
        List<Account> accounts = bank.getUserAccounts(user.getPassport());
        assertThat(accounts.size(), is(2));
        assertThat(accounts.get(0).getRequisites(), is("777111555222333"));
        assertThat(accounts.get(0).getValue(), is(0D));
        assertThat(accounts.get(1).getRequisites(), is("777111555222000"));
        assertThat(accounts.get(1).getValue(), is(1000D));
        bank.deleteAccountFromUser(user.getPassport(), account1);
        accounts = bank.getUserAccounts(user.getPassport());
        assertThat(accounts.size(), is(1));
        bank.deleteAccountFromUser(user.getPassport(), account2);
        accounts = bank.getUserAccounts(user.getPassport());
        assertThat(accounts.isEmpty(), is(true));
    }

    /**
     * Test getUserAccounts
     */
    @Test
    public void whenGetUserAccountsThenReturnListAccount() {
        Bank bank = new Bank();
        User user = new User("Иванов Иван Иванович", "1234567890");
        Account account = new Account("777111555222333", 5000D);
        bank.addUser(user);
        List<Account> accounts = bank.getUserAccounts(user.getPassport());
        assertThat(accounts.isEmpty(), is(true));
        bank.addAccountToUser(user.getPassport(), account);
        accounts = bank.getUserAccounts(user.getPassport());
        assertThat(accounts.size(), is(1));
        assertThat(accounts.get(0).getRequisites(), is("777111555222333"));
        assertThat(accounts.get(0).getValue(), is(5000D));
    }

    /**
     * Test transferMoney valid
     */
    @Test
    public void whenTransferMoneyIsValidThenTransferIsSuccess() {
        Bank bank = new Bank();
        User userSource = new User("Иванов Иван Иванович", "1234567890");
        Account accountSource = new Account("777111555222333", 5000D);
        bank.addUser(userSource);
        bank.addAccountToUser(userSource.getPassport(), accountSource);
        User userDestination = new User("Сидоров Семен Сергеевич", "1234098765");
        Account accountDestination = new Account("777000444222333", 10D);
        bank.addUser(userDestination);
        bank.addAccountToUser(userDestination.getPassport(), accountDestination);
        boolean result = bank.transferMoney(userSource.getPassport(), accountSource.getRequisites(),
                userDestination.getPassport(), accountDestination.getRequisites(), 4000D);
        assertThat(result, is(true));
        assertThat(bank.getUserAccounts(userSource.getPassport()).get(0).getValue(), is(1000D));
        assertThat(bank.getUserAccounts(userDestination.getPassport()).get(0).getValue(), is(4010D));
    }

    /**
     * Test transferMoney valid
     */
    @Test
    public void whenTransferMoneyForOneUserIsValidThenTransferIsSuccess() {
        Bank bank = new Bank();
        User user = new User("Иванов Иван Иванович", "1234567890");
        Account accountSource = new Account("777111555222333", 5000D);
        Account accountDestination = new Account("777000444222333", 10D);
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), accountSource);
        bank.addAccountToUser(user.getPassport(), accountDestination);
        boolean result = bank.transferMoney(user.getPassport(), accountSource.getRequisites(),
                user.getPassport(), accountDestination.getRequisites(), 4000D);
        assertThat(result, is(true));
        assertThat(bank.getUserAccounts(user.getPassport()).get(0).getValue(), is(1000D));
        assertThat(bank.getUserAccounts(user.getPassport()).get(1).getValue(), is(4010D));
    }

    /**
     * Test transferMoney invalid
     */
    @Test
    public void whenTransferMoneyIsInvalidThenTransferIsFailed() {
        Bank bank = new Bank();
        User userSource = new User("Иванов Иван Иванович", "1234567890");
        Account accountSource = new Account("777111555222333", 5000D);
        bank.addUser(userSource);
        bank.addAccountToUser(userSource.getPassport(), accountSource);
        User userDestination = new User("Сидоров Семен Сергеевич", "1234098765");
        Account accountDestination = new Account("777000444222333", 10D);
        bank.addUser(userDestination);
        bank.addAccountToUser(userDestination.getPassport(), accountDestination);
        boolean result = bank.transferMoney(userSource.getPassport(), accountSource.getRequisites(),
                userDestination.getPassport(), accountDestination.getRequisites(), 5001D);
        assertThat(result, is(false));
        assertThat(bank.getUserAccounts(userSource.getPassport()).get(0).getValue(), is(5000D));
        assertThat(bank.getUserAccounts(userDestination.getPassport()).get(0).getValue(), is(10D));
    }
}