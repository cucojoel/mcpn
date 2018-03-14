package com.salesforce.marketingcloud.cordovaplugin;

import android.app.Application;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.util.Log;

import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.registration.RegistrationManager;

import java.util.Set;
import java.util.TreeSet;

import io.ionic.starter.R;

public class MCCordovaPluginApplication extends Application {

    private static final String TAG = "MCCordovaPluginApplication";
    private static Context context;

    public static Context getAppContext() {
        return context;
    }
    public static final String CURRENT_CORDOVA_VERSION_NAME = "MC_Cordova_v1.0.2";

    @Override
    public void onCreate() {
        super.onCreate();


        context = getApplicationContext();
        boolean mcAnalyticsEnabled = "enabled".equalsIgnoreCase(getString(R.string.MCANALYTICS));

        Log.d(TAG, "entra build #######");
        Log.d(TAG, "==> MCCordovaPluginApplication onCreate");
        Log.d(TAG, getString(R.string.MCANALYTICS));
        Log.d(TAG, getString(R.string.APPID));
        Log.d(TAG, getString(R.string.ACCESSTOKEN));
        Log.d(TAG, getString(R.string.GCMSENDERID));
        Log.d(TAG, getString(R.string.CHANNELNAME));

        try {
        MarketingCloudSdk.init(this, MarketingCloudConfig.builder()
            .setApplicationId(getString(R.string.APPID))
            .setAccessToken(getString(R.string.ACCESSTOKEN))
            .setGcmSenderId(getString(R.string.GCMSENDERID))
            .setGeofencingEnabled(true) 
            .setNotificationChannelName(String.valueOf(R.string.CHANNELNAME))
            .build(), new MarketingCloudSdk.InitializationListener() {
                @Override public void complete(InitializationStatus status) {
                    Log.d(TAG, "entra build #######");
                    if (status.isUsable()) {
                        RegistrationManager registrationManager = MarketingCloudSdk.getInstance().getRegistrationManager();
                        RegistrationManager.Editor registrationEditor = registrationManager.edit();
                        Set<String> tags = new TreeSet<>(registrationManager.getTags());
                        if (!tags.isEmpty()) {
                            for (String tag : tags) {
                                if (!tag.equals(CURRENT_CORDOVA_VERSION_NAME) && tag.startsWith("MC_Cordova_v")) {
                                    registrationEditor.removeTags(tag);
                                }
                            }
                        }
                        registrationEditor.addTags(CURRENT_CORDOVA_VERSION_NAME).commit();
                    }
                }
            });
        }catch(Exception e){
            Log.d(TAG, "ERROR onCreate: " + e.getMessage());
        }
        MarketingCloudSdk.requestSdk(new MarketingCloudSdk.WhenReadyListener() {
            @Override public void ready(MarketingCloudSdk marketingCloudSdk) {

                marketingCloudSdk.getRegionMessageManager().enableGeofenceMessaging();
            }
        });
    }
}
