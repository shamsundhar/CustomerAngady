package angady.com.customer.model.network;

/**
 * Created by shyam on 3/12/2018.
 */

public class OtpRequest {
  private String id;
  private String otp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
