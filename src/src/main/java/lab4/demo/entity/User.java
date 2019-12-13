package lab4.demo.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s265057.users")
public class User {
    @Id
    private String username;

    private String password;


}