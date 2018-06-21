package angady.com.customer.injection.modules;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import angady.com.customer.injection.qualifier.ChildFragmentManager;
import angady.com.customer.injection.scopes.PerFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ChildFragmentManager
    FragmentManager provideChildFragmentManager() { return mFragment.getChildFragmentManager(); }


}
