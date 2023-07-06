abstract class User{

    private String username;
    private String password;
    private String nik;
    private String ponsel;

    public User(String username, String password, String nik, String ponsel){
        this.username = username;
        this.password = password;
        this.nik = nik;
        this.ponsel = ponsel;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getNik(){
        return nik;
    }

    public String getPonsel(){
        return ponsel;
    }

    public abstract void displayInfo();

}