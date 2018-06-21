package angady.com.customer.injection.components;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import angady.com.customer.LoginActivity;
import angady.com.customer.SignupActivity;
import angady.com.customer.injection.modules.ActivityModule;
import angady.com.customer.injection.qualifier.ActivityContext;
import angady.com.customer.injection.qualifier.ActivityFragmentManager;
import angady.com.customer.injection.scopes.PerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent extends AppComponent {

//    @ActivityContext Context activityContext();
//    @ActivityFragmentManager FragmentManager defaultFragmentManager();


    // create inject methods for your Activities here

    void inject(SignupActivity activity);
    void inject(LoginActivity activity);

}
