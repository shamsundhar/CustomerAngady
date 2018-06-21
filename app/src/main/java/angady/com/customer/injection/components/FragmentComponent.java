package angady.com.customer.injection.components;



import angady.com.customer.adapters.OrderHistoryAdapter;
import angady.com.customer.fragments.OrderHistoryFragment;
import angady.com.customer.fragments.ResetPasswordEnterMobileFragment;
import angady.com.customer.fragments.ResetPasswordEnterOtpFragment;
import angady.com.customer.fragments.ShopListFragment;
import angady.com.customer.fragments.SignUpEnterOtpFragment;
import angady.com.customer.fragments.SignUpFragment;
import angady.com.customer.injection.modules.FragmentModule;
import angady.com.customer.injection.scopes.PerFragment;
import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(SignUpFragment fragment);
    void inject(SignUpEnterOtpFragment fragment);
    void inject(ResetPasswordEnterOtpFragment fragment);
    void inject(ResetPasswordEnterMobileFragment fragment);
    void inject(ShopListFragment fragment);
    void inject(OrderHistoryFragment fragment);
}
