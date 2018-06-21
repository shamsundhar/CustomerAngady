package angady.com.customer.model.network;

/**
 * Created by shyam on 4/7/2018.
 */

public class ForgotPasswordRequest {
    private String mobile;
    private String otp;

    public ForgotPasswordRequest(String mobile, String otp) {
        this.mobile = mobile;
        this.otp = otp;
    }

    public ForgotPasswordRequest(String mobile) {

        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
