package bgu.spl.net.srv;

public class User {
    private String userName;
    private String password;
    private int connectionId;


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }
}
