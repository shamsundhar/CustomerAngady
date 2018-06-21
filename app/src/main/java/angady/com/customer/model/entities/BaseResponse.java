package angady.com.customer.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shyam on 3/9/2018.
 */

public class BaseResponse {
    private String status;
    private String message;
    @SerializedName("user_id")
    private String userId;
    private String otp;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String error;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }


}
