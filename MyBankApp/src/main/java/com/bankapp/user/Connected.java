package com.bankapp.user;
import javax.persistence.*;

@Entity
@Table(name ="connected")
public class Connected {
    @Id
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false,unique = true,length =45)
    private String email;


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
