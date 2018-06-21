package angady.com.customer.data.remote;

import java.util.List;
import java.util.Map;

import angady.com.customer.model.entities.BaseResponse;
import angady.com.customer.model.entities.LoginResponse;
import angady.com.customer.model.entities.OrderListResponse;
import angady.com.customer.model.entities.ShopListResponse;
import angady.com.customer.model.network.ForgotPasswordRequest;
import angady.com.customer.model.network.LoginRequest;
import angady.com.customer.model.network.OtpRequest;
import angady.com.customer.model.network.ShopListRequest;
import angady.com.customer.model.network.SignupRequest;
import angady.com.customer.model.network.UserIdRequest;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("user/signup")
    @Headers({"Client-Service: shkang$@)!*",
    "Auth-key: psangady#2018",
    "Content-Type: application/json"})
    Observable<BaseResponse> signUp(@Body SignupRequest signupRequest);


    @POST("user/verify_otp")
    @Headers({"Client-Service: shkang$@)!*",
            "Auth-key: psangady#2018",
            "Content-Type: application/json"})
    Observable<BaseResponse> verifyOtp_signup(@Body OtpRequest otpRequest);


    @POST("user/login")
    @Headers({"Client-Service: shkang$@)!*",
            "Auth-key: psangady#2018",
            "Content-Type: application/json"})
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);


    @POST("user/forgot_password")
    @Headers({"Client-Service: shkang$@)!*",
            "Auth-key: psangady#2018",
            "Content-Type: application/json"})
    Observable<BaseResponse> forgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);


    @POST("user/forgot_verify_otp")
    @Headers({"Client-Service: shkang$@)!*",
            "Auth-key: psangady#2018",
            "Content-Type: application/json"})
    Observable<BaseResponse> forgotPasswordVerify(@Body ForgotPasswordRequest forgotPasswordRequest);

//    Client-Service: shkang$@)!*
//    Auth-key: psangady#2018
//    Content-Type: application/json
//    Authorization-ID: Tokenumber ( like $1$TJ0.gi..$.mAaBoV81F3ZerXzh1WA00)
//    User-ID: Loged-in user id (like userid is 3)



    @POST("user/get_orders")
    @Headers({"Client-Service: shkang$@)!*",
            "Auth-key: psangady#2018",
            "Content-Type: application/json"})
    Observable<OrderListResponse> getUserOrders(@HeaderMap Map<String, String> headers, @Body UserIdRequest userIdRequest);

}
