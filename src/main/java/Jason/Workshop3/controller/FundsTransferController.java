package Jason.Workshop3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Jason.Workshop3.model.AccountRepoModel;
import Jason.Workshop3.model.Accounts;
import Jason.Workshop3.services.FundsTransferService;

@Controller
public class FundsTransferController {
    @Autowired
    FundsTransferService fundsTransferSvc;

    @GetMapping()
    public String accountForm(Model model) {

        // Get account name from database
        List<AccountRepoModel> accountInfo = fundsTransferSvc.findAccountIdNameSvc();

        model.addAttribute("accountInfo", accountInfo);
        model.addAttribute("accounts", new Accounts());
        return "index";
    }

    @PostMapping(path = "/transfer")
    public String account(Accounts accounts, Model model) {

        // Check C0 both from and to exists in database
        Boolean resultFrom = fundsTransferSvc.findAccountExistsSvc(accounts.getFrom());
        Boolean resultTo = fundsTransferSvc.findAccountExistsSvc(accounts.getTo());

        // If both are true, continue with checks
        if (resultFrom == false && resultTo == false) {
            // Print error message
            System.out.println("Both account to and from should exists");
            return "index";
        }

        // Check C1
        Integer length1 = accounts.getFrom().length();
        Integer length2 = accounts.getTo().length();

        if (length1 != 10 || length2 != 10) {
            // Print error message
            System.out.println("Account id should be 10 characters");
            return "index";
        }

        // Check C2
        if (accounts.getFrom() == accounts.getTo()) {
            // Print error message
            System.out.println("Account id should not be the same");
            return "index";
        }

        // Check C3
        if (accounts.getAmount() <= 0) {
            // Print error message
            System.out.println("Amount cannot be 0 or negative");
            return "index";
        }

        // Check C4
        if (accounts.getAmount() <= 0) {
            // Print error message
            System.out.println("Amount to transfer cannot be less than $10");
            return "index";
        }

        // Check C5
        // Get balance from database
        // Need to get the exact id from the From attribute
        String id = accounts.getFrom();
        Optional<Double> balanceOpt = fundsTransferSvc.findAccountBalanceSvc(id);
        Double balance = balanceOpt.get();

        if (accounts.getAmount() > balance) {
            // Print error message
            System.out.println("Insufficient funds in account");
            return "index";
        }

        // Need httpsession to prevent user having to retype info

        return "transaction";

    }
}
