package angady.com.customer.data.remote;

import angady.com.customer.model.entities.BaseResponse;
import angady.com.customer.model.network.AddressRequest;
import angady.com.customer.model.network.SignupRequest;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by shyam on 3/19/2018.
 */

public interface AddressApi {
    @POST("user/address")
    @Headers({"Client-Service: shkang$@)!*",
            "Auth-key: psangady#2018",
            "Content-Type: application/json"})
    Observable<BaseResponse> addAddress(@Header("Authorization") String token, @Header("User-ID") String userId,
                                        @Body AddressRequest addressRequest);
}
