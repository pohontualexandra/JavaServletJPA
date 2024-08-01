public class User {
    private String uname;
    private String email;
    private String password;
    private String phone;

    public User(String uname, String password, String email, String phone) {
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(){
        super();
    }


}
