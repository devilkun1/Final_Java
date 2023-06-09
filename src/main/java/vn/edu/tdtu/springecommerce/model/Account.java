package vn.edu.tdtu.springecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String role;
    private int permission;
}
