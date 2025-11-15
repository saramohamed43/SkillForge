package skillforge;

public abstract class User{
    public int userId;
    public String role;
    public String username;
    public String email;
    public String passwordHash;

    public User(int userId, String role, String username, String email, String passwordHash) {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }  
}