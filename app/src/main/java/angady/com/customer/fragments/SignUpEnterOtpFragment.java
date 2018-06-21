package angady.com.customer.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import angady.com.customer.BaseFragment;
import angady.com.customer.ForgotPasswordActivity;
import angady.com.customer.R;
import angady.com.customer.SignupActivity;
import angady.com.customer.SignupActivity2;
import angady.com.customer.data.remote.LoginApi;
import angady.com.customer.model.entities.BaseResponse;
import angady.com.customer.model.network.OtpRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static angady.com.customer.utils.Constants.BUNDLE_KEY_MOBILE_NUMBER;
import static angady.com.customer.utils.Constants.BUNDLE_KEY_OTP;
import static angady.com.customer.utils.Constants.BUNDLE_KEY_OTP_VERIFIED_MESSAGE;
import static angady.com.customer.utils.Constants.BUNDLE_KEY_USER_ID;

/**
 * Created by shyam on 3/15/2018.
 */

public class SignUpEnterOtpFragment extends BaseFragment {
    SignUpFragment.ActivityListener activityListener;
    @BindView(R.id.otp_mobile_number_message)
    TextView otp_mobile_number_message;
    @BindView(R.id.input_otp)
    EditText input_otp;
    @Inject
    LoginApi loginApi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password_enter_otp, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        initData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityListener = (SignupActivity2)getActivity();
    }
    @OnClick(R.id.btn_validate)
    public void clickOnValidate(){
        if(input_otp.getText().toString().trim().length() > 0)
        {
            OtpRequest request = new OtpRequest();
            request.setId(getArguments().getString(BUNDLE_KEY_USER_ID));
            request.setOtp(input_otp.getText().toString().trim());

            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Validating your OTP...");
            progressDialog.show();

            loginApi.verifyOtp_signup(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();

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
                            if(baseResponse.getStatus().equals("200"))
                            {
                                Intent intent = new Intent();
                                intent.putExtras(getArguments());
                                intent.putExtra(BUNDLE_KEY_OTP_VERIFIED_MESSAGE, baseResponse.getMessage());
                                activityListener.displayLoginScreen(RESULT_OK, intent);
                            }

                        }
                    });
        }
    }
    private void initData(){
        Resources res = getResources();
        Bundle bundle = this.getArguments();
        if(bundle != null){
            String text = String.format(res.getString(R.string.otp_mobile_number_message),  bundle.get(BUNDLE_KEY_MOBILE_NUMBER));
            CharSequence styledText = Html.fromHtml(text);
            otp_mobile_number_message.setText(styledText);
            input_otp.setText(bundle.getString(BUNDLE_KEY_OTP));
        }

    }
}
