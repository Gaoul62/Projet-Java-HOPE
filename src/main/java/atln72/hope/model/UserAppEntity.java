package atln72.hope.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERAPP")
public class UserAppEntity {
    // Fields
    @Id
    @Column(name = "USERID")
    private int userId;

    @Column(name= "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "ROLE", nullable = false, length = 50)
    private String role;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    // Constructors
    public UserAppEntity() {}

    public UserAppEntity(int userId, String username, String email, String role, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return role;
    }

    public void setUserRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
