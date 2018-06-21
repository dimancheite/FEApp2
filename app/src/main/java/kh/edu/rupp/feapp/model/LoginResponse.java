package kh.edu.rupp.feapp.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    private String message;
    private int code;

    @SerializedName("data")
    private User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
