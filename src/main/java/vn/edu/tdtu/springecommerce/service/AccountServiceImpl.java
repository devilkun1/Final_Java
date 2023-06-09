package vn.edu.tdtu.springecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.springecommerce.model.Account;
import vn.edu.tdtu.springecommerce.model.Cart;
import vn.edu.tdtu.springecommerce.model.Customer;
import vn.edu.tdtu.springecommerce.repository.AccountRepository;
import vn.edu.tdtu.springecommerce.repository.CartRepository;
import vn.edu.tdtu.springecommerce.repository.CustomerRepositoy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepositoy customerRepositoy;
    @Autowired
    CartRepository cartRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void register(String username, String password, String name) {
        Account accountItem = new Account();
        accountItem.setUsername(username);
        accountItem.setPassword(password);
        accountItem.setRole("User");
        accountItem.setPermission(1);
        accountRepository.save(accountItem);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = formatter.format(date);

        Customer customerItem = new Customer();
        customerItem.setName(name);
        customerItem.setLastActive(today);
        customerItem.setAccount(accountItem);
        customerRepositoy.save(customerItem);

        Cart cartItem = new Cart();
        cartItem.setStatus(0); // cart is set shopping status
        cartItem.setTotalAmount(0);
        cartItem.setCustomer(customerItem);
        cartRepository.save(cartItem);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account findByUsernameAndPassword(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void updatePermit(int accountId, int permission) {
        Account updatedAccount = accountRepository.findById(accountId);
        updatedAccount.setPermission(permission);
        accountRepository.save(updatedAccount);
    }
}
