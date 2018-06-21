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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import angady.com.customer.BaseFragment;
import angady.com.customer.ForgotPasswordActivity;
import angady.com.customer.LoginActivity;
import angady.com.customer.R;
import angady.com.customer.data.remote.LoginApi;
import angady.com.customer.model.entities.BaseResponse;
import angady.com.customer.model.network.ForgotPasswordRequest;
import angady.com.customer.utils.CustomAlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_MOBILE_NUMBER;

/**
 * Created by shyam on 2/19/2018.
 */

public class ResetPasswordEnterOtpFragment extends BaseFragment {
    ResetPasswordEnterMobileFragment.ActivityListener activityListener;
    @BindView(R.id.otp_mobile_number_message)
    TextView otp_mobile_number_message;
    @BindView(R.id.btn_validate)
    Button validateButton;
    @BindView(R.id.input_otp)
    EditText otpET;
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
        activityListener = (ForgotPasswordActivity)getActivity();
    }
    @OnClick(R.id.btn_validate)
    public void clickOnValidate(){
        Bundle bundle = this.getArguments();
             String otp = otpET.getText().toString().trim();
             if(otp.length() > 0){
                 final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                         R.style.AppTheme_Dark_Dialog);
                 progressDialog.setIndeterminate(true);
                 progressDialog.setMessage("Loading please wait...");
                 progressDialog.show();
                 loginApi.forgotPasswordVerify(new ForgotPasswordRequest(bundle.getString(BUNDLE_KEY_MOBILE_NUMBER), otp))
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
                                 if(baseResponse.getStatus().equals("200")) {
                                     ((ResetPasswordEnterMobileFragment.ActivityListener)getActivity())
                                             .resetPasswordResult(true);

                                 }
                                 else if(baseResponse.getStatus().equals("206")){
                                     //display error.
                                     new CustomAlertDialog().showAlert1(
                                             getActivity(),
                                             R.string.text_reset_password_failed,
                                             baseResponse.getMessage(),
                                             new CustomAlertDialog.Callback() {
                                                 @Override
                                                 public void onSucess(int t) {
                                                     ((ResetPasswordEnterMobileFragment.ActivityListener)getActivity())
                                                             .resetPasswordResult(false);
                                                 }
                                             });
                                 }

                             }
                         });
             }
             else{

             }


        activityListener.resetPasswordResult(true);
    }
    private void initData(){
        Resources res = getResources();
        Bundle bundle = this.getArguments();
        if(bundle != null){
            validateButton.setEnabled(true);
            String text = String.format(res.getString(R.string.otp_mobile_number_message),  bundle.get(BUNDLE_KEY_MOBILE_NUMBER));
            CharSequence styledText = Html.fromHtml(text);
            otp_mobile_number_message.setText(styledText);
        }
        else{
            validateButton.setEnabled(false);
        }

    }
}
