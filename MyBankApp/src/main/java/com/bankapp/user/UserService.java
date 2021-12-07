package com.bankapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;
    @Autowired private AccountRepository arepo;

    public List<User> listAllUser(){
        return (List<User>) repo.findAll();
    }
    public List<Account> listAllAccount(){
        return (List<Account>) arepo.findAll();
    }

    public void save(User user) {
        Account account = new Account();
        repo.save(user);
        account.setIdUser(listAllUser().get(listAllUser().size()-1).getId());
        account.setDepot(getDepot());
        account.setRetrait(getRetrait());
        account.setValue(5000);
        arepo.save(account);
    }
    public Integer getDepot(){
        for(Account a : listAllAccount()){
            return a.getDepot();
        }
        return 100;
    }

    public Integer getRetrait(){
        for(Account a : listAllAccount()){
            return a.getRetrait();
        }
        return 100;
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result=repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID"+id);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Long count =repo.countById(id);
        if(count == null || count ==0){
            throw new UserNotFoundException("Could not find any users with ID"+id);
        }
        repo.deleteById(id);
    }
}
