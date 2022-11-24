package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private static final int ACCOUNT_NAME_LENGTH_LIMIT = 5;
    private static final int ACCOUNT_PASS_LENGTH_LIMIT = 8;

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validateAccount(account);
        accountManager.createNewAccount(configureAccountBeforeCreation(account));
    }

    private void validateAccount(Account account) {
        validateAccountName(account.getName());
        validateAccountPassword(account.getPassword());
    }

    private void validateAccountName(String accountName) {
        if (isValidItemLength(accountName, ACCOUNT_NAME_LENGTH_LIMIT)) {
            throw new WrongAccountNameException();
        }
    }

    private boolean isValidItemLength(String item, int lengthLimit) {
        return item.length() <= lengthLimit;
    }

    private void validateAccountPassword(String password) {
        validatePasswordLength(password);
        validateWithPasswordChecker(password);
    }

    private void validatePasswordLength(String password) {
        if (isValidItemLength(password, ACCOUNT_PASS_LENGTH_LIMIT)) {
            throw new TooShortPasswordException();
        }
    }

    private void validateWithPasswordChecker(String password) {
        if (passwordChecker.validate(password) != OK) {
            throw new WrongPasswordException();
        }
    }

    private Account configureAccountBeforeCreation(Account account) {
        account.setAddresses(getAccountAddresses(account));
        account.setCreatedDate(new Date());
        return account;
    }

    private List<Address> getAccountAddresses(Account account) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        return addresses;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }
}
