package angady.com.customer.data.remote;

import java.util.Map;

import angady.com.customer.model.entities.BaseResponse;
import angady.com.customer.model.entities.ShopListResponse;
import angady.com.customer.model.network.AuthorizeRequest;
import angady.com.customer.model.network.ProductListRequest;
import angady.com.customer.model.network.ProductListResponse;
import angady.com.customer.model.network.ShopListRequest;
import angady.com.customer.model.network.SignupRequest;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by shyam on 4/10/2018.
 */

public interface ShopApi {
   // http://design7.in/angady/index.php/shopkeeper/get_shops_location
    @POST("shopkeeper/get_shops_location")
    @Headers({"Client-Service: shkang$@)!*",
            "Auth-key: psangady#2018",
            "Content-Type: application/json"})
    Observable<ShopListResponse> getShopsFromLocation(@HeaderMap Map<String, String> headers, @Body ShopListRequest shopListRequest);

    @POST("products/products_byshop")
    @Headers({"Client-Service: shkang$@)!*",
            "Auth-key: psangady#2018",
            "Content-Type: application/json"})
    Observable<ProductListResponse> getProductsByShop(@HeaderMap Map<String, String> headers, @Body ProductListRequest productListRequest);



}
