package angady.com.customer;

import android.app.Application;
import android.content.res.Resources;


import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import angady.com.customer.injection.components.AppComponent;
import angady.com.customer.injection.components.DaggerAppComponent;
import angady.com.customer.injection.modules.AppModule;
import angady.com.customer.utils.PreferenceHelper;

@ReportsCrashes(mailTo = "shyam.kdp@gmail.com", customReportContent = {
        ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME,
        ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL,
        ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT}, mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text)
public class MyApplication extends Application {

    private static MyApplication sInstance = null;

    private static AppComponent sAppComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
//        sAppComponent = DaggerAppComponent.create();


        // The following line triggers the initialization of ACRA for crash Log Reposrting
        if (PreferenceHelper.getPrefernceHelperInstace().getBoolean(
                this, PreferenceHelper.SUBMIT_LOGS, true)) {
            ACRA.init(this);
        }

    }

    public static MyApplication getInstance() { return sInstance; }

    public static AppComponent getAppComponent() { return sAppComponent; }

    public static Resources getRes() { return sInstance.getResources(); }
}
