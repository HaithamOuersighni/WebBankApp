package com.bankapp.user;


import jdk.jfr.BooleanFlag;

import javax.persistence.*;

@Entity
@Table(name ="users")

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true,length =45)
    private String email;

    @Column(length=15,nullable=false)
    private String password;

    @Column(nullable = false)
    private boolean admin;

    @Column(length=45,nullable = false,name="prenom")
    private String prenom;

    @Column(length=45,nullable = false,name="nom")
    private String nom;

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

    public String getPassword(){
	    return password;
    }

    public void setPassword(String password){
	    this.password=password;
    }

    public String getPrenom(){
	    return prenom;
    }

    public void setPrenom(String prenom){
	    this.prenom=prenom;
    }

    public String getNom(){
	    return nom;
    }

    public void setNom(String nom){
	    this.nom=nom;
    }

    public boolean isAdmin(){
	    return admin;
    }

    public void setAdmin(Boolean val){
        this.admin=val;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}