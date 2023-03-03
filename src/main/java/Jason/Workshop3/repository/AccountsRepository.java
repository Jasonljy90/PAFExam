package Jason.Workshop3.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Jason.Workshop3.model.AccountRepoModel;

@Repository
public class AccountsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String findAccountsSQL = "select account_id, name from accounts;";
    private String findAccountExistsSQL = "select * from accounts where account_id = ?;";
    private String getBalanceSQL = "select balance from accounts where account_id = ?";
    // public Double findAccountBalance(String accountId) {
    // AccountRepoModel account = jdbcTemplate.queryForObject(findAccountExistsSQL,
    // BeanPropertyRowMapper.newInstance(AccountRepoModel.class),
    // accountId);
    // return account.getAmount();
    // }

    public Optional<Double> findAccountBalance(String accountId) {
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(getBalanceSQL, accountId);
        return Optional.ofNullable(rs.next() ? rs.getDouble("balance") : null);
    }

    public Boolean findAccountExists(String accountId) {
        AccountRepoModel account = jdbcTemplate.queryForObject(findAccountExistsSQL,
                BeanPropertyRowMapper.newInstance(AccountRepoModel.class),
                accountId);
        if (account == null) {
            return false;
        }
        return true;
    }

    public List<AccountRepoModel> findAccountIdName() {
        List<AccountRepoModel> resultList = new ArrayList<>();
        resultList = jdbcTemplate.query(findAccountsSQL, BeanPropertyRowMapper.newInstance(AccountRepoModel.class));
        return resultList;
    }

    public boolean withdraw(String accountId, double amount) {
        final int rowCount = jdbcTemplate.update("update account set balance = balance - ? where account_id = ?",
                amount, accountId);
        return rowCount > 0;
    }

    public boolean deposit(String accountId, double amount) {
        final int rowCount = jdbcTemplate.update("update account set balance = balance + ? where account_id = ?",
                amount, accountId);
        return rowCount > 0;
    }

}
// what happens when search order date but is empty list -> return
// optional<List<Date>>

// Failed to instantiate [java.sql.Date]: No default constructor found

// How to bind data to html