package angady.com.customer.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shyam on 4/10/2018.
 */

public class LoginResponse {
    private String status;
    private String message;
    private String error;
    private String id;
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
