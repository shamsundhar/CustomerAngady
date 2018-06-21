package angady.com.customer.model.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shyam on 6/8/2018.
 */

public class UserIdRequest {
    @SerializedName("user_id")
    private String userId;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
