package com.bankapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;
    @Autowired private AccountRepository arepo;
    @Autowired private ConnectedRepository crepo;

    public List<User> listAllUser(){
        return (List<User>) repo.findAll();
    }
    public List<Account> listAllAccount(){
        return (List<Account>) arepo.findAll();
    }
    public List<Connected> listAllConnected() { return (List<Connected>) crepo.findAll();}

    public boolean save(User user) {
        for(User u : listAllUser()){
            if(u.getEmail().equals(user.getEmail())){
                return false;
            }
        }

        Account account = new Account();
        repo.save(user);

        account.setIdUser(listAllUser().get(listAllUser().size()-1).getId());
        if(listAllUser().get(listAllUser().size()-1).getAdmin()){
            account.setDecouvert(-2000);
            account.setAdmin(true);
        }else{
            account.setDecouvert(0);
            account.setAdmin(false);
        }
        account.setDepot(getDepot());
        account.setRetrait(getRetrait());
        account.setValue(5000);
        arepo.save(account);

        Connected co = new Connected();
        co.setId(listAllUser().get(listAllUser().size()-1).getId());
        co.setEmail(listAllUser().get(listAllUser().size()-1).getEmail());
        crepo.save(co);

        return true;
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

    public void saveGoogle(User user) {
        for(User u : listAllUser()){
            if(u.getEmail().equals(user.getEmail())){
                System.out.println("User Already exist");
                Connected co = new Connected();
                co.setEmail(u.getEmail());
                co.setId(u.getId());
                crepo.save(co);
                return;
            }
        }
        user.setType("google");
        repo.save(user);

        Account account = new Account();
        account.setIdUser(listAllUser().get(listAllUser().size()-1).getId());
        if(listAllUser().get(listAllUser().size()-1).getAdmin()){
            account.setDecouvert(-2000);
            account.setAdmin(true);
        }else{
            account.setDecouvert(0);
            account.setAdmin(false);
        }
        account.setDepot(getDepot());
        account.setRetrait(getRetrait());
        account.setValue(5000);
        arepo.save(account);

        Connected co = new Connected();
        co.setId(listAllUser().get(listAllUser().size()-1).getId());
        co.setEmail(listAllUser().get(listAllUser().size()-1).getEmail());
        crepo.save(co);
    }

    public void disconnect(){
        for(Connected c : listAllConnected()){
            crepo.delete(c);
        }
    }

    public boolean connect(User user) {
        for(User u : listAllUser()){
            if(u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())){
                Connected co = new Connected();
                co.setId(u.getId());
                co.setEmail(u.getEmail());
                crepo.save(co);
                return true;
            }
        }
        return false;
    }

    public Account getConnected() {
        int id = listAllConnected().get(0).getId();
        for(Account a : listAllAccount()){
            if(a.getIdUser().equals(id)){
                return a;
            }
        }
        return null;
    }

    public void addMoney() {
        Account a = getConnected();
        a.setValue(a.getValue()+a.getDepot());
        arepo.deleteById(a.getId());
        arepo.save(a);
    }

    public boolean subMoney() {
        Account a = getConnected();
        if(a.getDecouvert()<=a.getValue()-a.getDepot()){
            a.setValue(a.getValue()-a.getDepot());
            arepo.deleteById(a.getId());
            arepo.save(a);
            return true;
        }
        return false;
    }
}
