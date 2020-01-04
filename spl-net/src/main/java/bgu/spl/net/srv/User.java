package bgu.spl.net.srv;

public class User {
    private String userName;
    private String password;
    private int connectionId; // -1 represent disconnected user

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        connectionId = -1;
    }

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
