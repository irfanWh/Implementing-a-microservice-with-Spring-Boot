package ma.enset.bankaccountsmicroservice.web;

import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import ma.enset.bankaccountsmicroservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAcccount> bankAcccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAcccount bankAcccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAcccount save(@RequestBody BankAcccount bankAcccount){
        if(bankAcccount.getId()==null)bankAcccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAcccount);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAcccount update(@PathVariable String id,@RequestBody BankAcccount bankAcccount){
        BankAcccount acccount=bankAccountRepository.findById(id).orElseThrow();
        if(bankAcccount.getBalance()!= null)acccount.setBalance(bankAcccount.getBalance());
        if(bankAcccount.getCreateAt()!= null)acccount.setCreateAt(new Date());
        if(bankAcccount.getCurrency()!= null)acccount.setCurrency(bankAcccount.getCurrency());
        if(bankAcccount.getType()!= null)acccount.setType(bankAcccount.getType());
        return bankAccountRepository.save(acccount);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAcccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}