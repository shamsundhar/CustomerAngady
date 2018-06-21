package angady.com.customer.injection.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import angady.com.customer.BuildConfig;
import angady.com.customer.injection.qualifier.AppContext;
import angady.com.customer.injection.scopes.PerApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @PerApplication
    @AppContext
    Context provideAppContext() {
        return mApp;
    }

    @Provides
    @PerApplication
    Resources provideResources() {
        return mApp.getResources();
    }

}
