package dbjoggingtimes.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String salt;

    @Column(nullable=false, unique=true)
    private String securePassword;

    public User(){}

    public User(String name, String email, String salt, String securePassword){
        this.name = name;
        this.email = email;
        this.salt = salt;
        this.securePassword = securePassword;
    }

    public User(String name, String email, String securePassword){
        this.name = name;
        this.email = email;
        this.securePassword = securePassword;
        this.salt = "salt";
    }

}
