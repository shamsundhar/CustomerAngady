package angady.com.customer.injection.components;

import android.content.Context;
import android.content.res.Resources;


import angady.com.customer.SignupActivity;
import angady.com.customer.data.remote.LoginApi;
import angady.com.customer.data.remote.ShopApi;
import angady.com.customer.injection.modules.AppModule;
import angady.com.customer.injection.modules.DataModule;
import angady.com.customer.injection.modules.NetModule;
import angady.com.customer.injection.qualifier.AppContext;
import angady.com.customer.injection.scopes.PerApplication;
import dagger.Component;

@PerApplication
@Component(modules={AppModule.class, NetModule.class, DataModule.class})
public interface AppComponent {
    @AppContext Context appContext();
    Resources resources();
    //    RefWatcher refWatcher();
//
//    Realm realm();
//    CountryRepo countryRepo();
    LoginApi loginApi();
    ShopApi shopApi();
}
