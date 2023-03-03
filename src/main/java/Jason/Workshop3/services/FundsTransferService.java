package Jason.Workshop3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import Jason.Workshop3.Utils;
import Jason.Workshop3.model.AccountRepoModel;
import Jason.Workshop3.model.Accounts;
import Jason.Workshop3.model.Transaction;
import Jason.Workshop3.repository.AccountsRepository;
import jakarta.json.JsonObject;

@Service
public class FundsTransferService {

    @Autowired
    AccountsRepository accountRepo;

    @Autowired
    LogAuditService logAuditService;

    public List<AccountRepoModel> findAccountIdNameSvc() {
        List<AccountRepoModel> accountInfo = accountRepo.findAccountIdName();
        return accountInfo;
    }

    public Boolean findAccountExistsSvc(String accountId) {
        return accountRepo.findAccountExists(accountId);
    }

    public Optional<Double> findAccountBalanceSvc(String accountId) {
        return accountRepo.findAccountBalance(accountId);
    }

    @Transactional
    public boolean transfer(Accounts accounts, String toId, String fromId) {
        // Generate transaction id
        String transactionId = Utils.generateId(8);

        final Optional<Double> optFrom = accountRepo.findAccountBalance(fromId);
        final Optional<Double> optTo = accountRepo.findAccountBalance(toId);

        if (optFrom.isEmpty() || optTo.isEmpty()) {
            return false;
        }

        if (accountRepo.withdraw(accounts.getFrom(), accounts.getAmount()) == false
                || accountRepo.deposit(accounts.getTo(), accounts.getAmount()) == false) {
            return false;
        }

        // Create JsonObject
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        transaction.setDate(timestamp);
        transaction.setFromAcc(fromId);
        transaction.setToAcc(toId);
        transaction.setAmount(accounts.getAmount());

        JsonObject jsobObj = Utils.toJsonObj(transaction);

        // Save to redis database
        logAuditService.save(jsobObj);

        return true;
    }

}
