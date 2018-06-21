package angady.com.customer.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import angady.com.customer.BaseFragment;
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

/**
 * Created by shyam on 2/19/2018.
 */

public class ResetPasswordEnterMobileFragment extends BaseFragment {
    @BindView(R.id.input_mobile)
    EditText _mobileText;
    ActivityListener activityListener;
    @Inject
    LoginApi loginApi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password_enter_mobile, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        return view;
    }

    @OnClick(R.id.btn_submit)
    public void clickOnSubmit(){
        final String mobileNumber = _mobileText.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        if(mobileNumber.length() > 0 && mobileNumber.length() == 10){
            _mobileText.setError(null);
            loginApi.forgotPassword(new ForgotPasswordRequest(mobileNumber))
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
                                ((ActivityListener)getActivity()).displayOtpPage(mobileNumber);
                            }
                            else if(baseResponse.getStatus().equals("206")){
                                //display error.
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_reset_password_failed,
                                        baseResponse.getMessage(),
                                        null);
                            }

                        }
                    });
        }
        else{
            _mobileText.setError("Invalid Mobile Number");
        }

    }
    public interface ActivityListener{
        void displayOtpPage(String mobileNumber);
        void resetPasswordResult(Boolean flag);
    }
}
