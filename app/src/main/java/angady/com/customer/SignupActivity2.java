package angady.com.customer;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import angady.com.customer.fragments.ResetPasswordEnterMobileFragment;
import angady.com.customer.fragments.ResetPasswordEnterOtpFragment;
import angady.com.customer.fragments.SignUpEnterOtpFragment;
import angady.com.customer.fragments.SignUpFragment;
import angady.com.customer.model.entities.BaseResponse;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_MOBILE_NUMBER;
import static angady.com.customer.utils.Constants.BUNDLE_KEY_OTP;
import static angady.com.customer.utils.Constants.BUNDLE_KEY_USER_ID;

public class SignupActivity2 extends BaseActivity implements SignUpFragment.ActivityListener{
    private static final String SIGNUP_FRAGMENT_TAG = "SIGNUP";
    private static final String SIGNUP_ENTER_OTP_FRAGMENT_TAG = "SIGNUP_ENTER_OTP";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new SignUpFragment(), SIGNUP_FRAGMENT_TAG)
                .commit();

    }

    @Override
    public void displayLoginScreen(int result, Intent intent) {

        setResult(result, intent);
        finish();//finishing activity
    }


    @Override
    public void displayOtpPage(String mobileNumber, BaseResponse baseResponse) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_MOBILE_NUMBER, mobileNumber);
        bundle.putString(BUNDLE_KEY_OTP, baseResponse.getOtp());
        bundle.putString(BUNDLE_KEY_USER_ID, baseResponse.getUserId());
        SignUpEnterOtpFragment fragment = new SignUpEnterOtpFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, SIGNUP_ENTER_OTP_FRAGMENT_TAG)
                .commit();
    }
}
