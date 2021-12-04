package com.bankapp.user;
import javax.persistence.*;

@Entity
@Table(name ="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private Integer idUser;

    @Column(columnDefinition = "Integer default 5000")
    private Integer value;

    @Column(columnDefinition = "Integer default 0")
    private Integer decouvert;

    @Column(nullable = false)
    private Integer depot;

    @Column(nullable = false)
    private Integer retrait;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Integer decouvert) {
        this.decouvert = decouvert;
    }

    public Integer getDepot() {
        return depot;
    }

    public void setDepot(Integer depot) {
        this.depot = depot;
    }

    public Integer getRetrait() {
        return retrait;
    }

    public void setRetrait(Integer retrait) {
        this.retrait = retrait;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", value=" + value +
                ", decouvert=" + decouvert +
                ", depot=" + depot +
                ", retrait=" + retrait +
                '}';
    }
}
