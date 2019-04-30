package com.codetroupes.moleculexrouter.navigator;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.alibaba.android.arouter.facade.Postcard;
import com.codetroupes.moleculexrouter.callback.RouteCallback;
import com.codetroupes.moleculexrouter.navigator.impl.NavigatorBuilder;
import com.codetroupes.moleculexrouter.service.ActivityManager;
import com.codetroupes.moleculexrouter.service.Scheduler;


/**
 * Router with support features
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
public interface Router{

    String PATH = "/xrouter/navigator";

    /**
     * Inject params and services
     * @param container Activity or Fragment
     */
    void inject(Object container);

    /**
     * Create a navigator with interfaces
     * @param navigator the interfaces
     * @return the delegate of your navigator interfaces
     */
    <T> T create(Class<T> navigator);

    /** Getting a service with path */
    <T> T service(String path);

    /** Create a {@link NavigatorBuilder} with intent */
    NavigatorBuilder build(Intent intent);

    /** Create a {@link NavigatorBuilder} with path */
    NavigatorBuilder build(String path);

    /** Create a {@link NavigatorBuilder} with uri */
    NavigatorBuilder build(Uri uri);

    /**
     * Invoke startActivityForResult with a callback
     * @param postcard    route postcard
     * @param requestCode requestCode
     * @param callback    callback for getting the result and data
     */
    void startActivityForResult(Postcard postcard, int requestCode, RouteCallback callback);

    /** Getting the top activity or application context */
    Context getContext();

    ActivityManager getActivityManager();
    void setActivityManager(ActivityManager activityManager);

    Scheduler getScheduler();
    void setScheduler(Scheduler scheduler);

}
