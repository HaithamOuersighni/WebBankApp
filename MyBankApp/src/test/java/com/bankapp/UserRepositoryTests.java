package com.bankapp;

import com.bankapp.user.User;
import com.bankapp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user=new User();
        user.setEmail("user1@gmail.com");
        user.setPassword("123");
        user.setPrenom("Tems");
        user.setNom("haramiste");
        user.setAdmin(false);
        User savedUser=repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId = 1002;
        Optional<User> optUser = repo.findById(userId);
        User user = optUser.get();
        user.setPassword("master");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("master");
    }

    @Test
    public void testGet(){
        Integer userId = 1002;
        Optional<User> optUser = repo.findById(userId);
        Assertions.assertThat(optUser).isPresent();
        System.out.println(optUser.get());
    }

    @Test
    public void testTems(){
        Iterable<User> users = repo.findAll();
        for(User s : users){
            if(s.getId()>1000 && s.getId()<1005){
                System.out.println(s.toString());
            }
        }
    }
}
