package angady.com.customer.model.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shyam on 4/17/2018.
 */

public class AuthorizeRequest {
    @SerializedName("Authorization-ID")
    private String token;
    @SerializedName("User-ID")
    private String userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
