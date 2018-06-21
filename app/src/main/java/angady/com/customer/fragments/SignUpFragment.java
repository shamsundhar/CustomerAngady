package angady.com.customer.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import angady.com.customer.BaseFragment;
import angady.com.customer.ForgotPasswordActivity;
import angady.com.customer.R;
import angady.com.customer.SignupActivity;
import angady.com.customer.SignupActivity2;
import angady.com.customer.data.remote.LoginApi;
import angady.com.customer.model.entities.BaseResponse;
import angady.com.customer.model.network.SignupRequest;
import angady.com.customer.utils.CustomAlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_OTP;
import static angady.com.customer.utils.Constants.CONST_CUSTOMER_USER_TYPE;

/**
 * Created by shyam on 3/15/2018.
 */

public class SignUpFragment extends BaseFragment {
    private static final String TAG = "SignupFragment";
    ActivityListener activityListener;
    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_mobile) EditText _mobileNumberText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;

    @Inject
    LoginApi loginApi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, null);
        fragmentComponent().inject(this);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityListener = (SignupActivity2)getActivity();
    }
    @OnClick(R.id.link_login)
    public void clickOnLoginLink(){

    }
    @OnClick(R.id.btn_signup)
    public void clickOnSignUp(){
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String mobile = _mobileNumberText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        SignupRequest request = new SignupRequest();
        request.setEmail(email);
        request.setMobile(mobile);
        request.setName(name);
        request.setPassword(password);
        request.setUsername(mobile);
        request.setUsertype(CONST_CUSTOMER_USER_TYPE);

        loginApi.signUp(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        _signupButton.setEnabled(true);
                        onSignupFailed();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse){
                        progressDialog.dismiss();
                        _signupButton.setEnabled(true);
                        if(baseResponse.getStatus().equals("200")) {
                            onSignupSuccess(baseResponse);
                        }
                        else if(baseResponse.getStatus().equals("206")){
                            //display error.
                            new CustomAlertDialog().showAlert1(
                                    getActivity(),
                                    R.string.text_registration_failed,
                                    baseResponse.getError(),
                                    null);
                        }
                    }
                });

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//
//                    }
//                }, 3000);
    }


    public void onSignupSuccess(BaseResponse baseResponse) {
        _signupButton.setEnabled(true);
        activityListener.displayOtpPage(_mobileNumberText.getText().toString().trim(), baseResponse);
//        Intent resultdata = new Intent();
//        resultdata.putExtra(BUNDLE_KEY_OTP, otp);
//        setResult(RESULT_OK, resultdata);
//        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String mobileNumber = _mobileNumberText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        if(mobileNumber.isEmpty() || mobileNumber.length() != 10){
            _mobileNumberText.setError("Not a valid Mobile Number");
            valid = false;
        }
        else{
            _mobileNumberText.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    public interface ActivityListener{
        void displayOtpPage(String mobileNumber, BaseResponse baseResponse);
        void displayLoginScreen(int result, Intent intent);
    }
}