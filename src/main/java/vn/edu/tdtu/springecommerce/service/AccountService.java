package vn.edu.tdtu.springecommerce.service;

import vn.edu.tdtu.springecommerce.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    void register(String username, String password, String name);
    Account findByUsername(String username);
    Account findByUsernameAndPassword(String username, String password);
    void updatePermit(int accountId, int permission);
}
