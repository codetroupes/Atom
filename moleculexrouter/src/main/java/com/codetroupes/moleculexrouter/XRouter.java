package com.codetroupes.moleculexrouter;

import android.app.Application;

import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.launcher.ARouter;
import com.codetroupes.moleculexrouter.navigator.Router;


import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
public class XRouter {

    public static String SCHEME = "togojoy";
    public static String AUTHORITY = "router";

    private static Router sRouter;

    /**
     * Instead of using {@link ARouter#getInstance()}
     * @return instance with all the features of the router
     */
    public static Router getRouter(){
        if(sRouter == null){
            sRouter = (Router) ARouter.getInstance().build(Router.PATH).navigation();
        }
        return sRouter;
    }

    /** Instead of using {@link ARouter#init(Application)} */
    public static void init(Application application){
        ARouter.init(application);
    }

    /** Scheme setting for building route uri */
    public static void setScheme(String scheme){
        SCHEME = scheme;
    }
    /** Authority setting for building route uri */
    public static void setAuthority(String authority){
        AUTHORITY = authority;
    }
    /** invoke this when Instant Run is enable */
    public static synchronized void openDebug(){
        ARouter.openDebug();
    }

    public static boolean debuggable(){
        return ARouter.debuggable();
    }

    public static synchronized void openLog(){
        ARouter.openLog();
    }

    public static synchronized void printStackTrace(){
        ARouter.printStackTrace();
    }

    public static synchronized void setExecutor(ThreadPoolExecutor tpe){
        ARouter.setExecutor(tpe);
    }

    public static boolean canAutoInject(){
        return ARouter.canAutoInject();
    }

    public static synchronized void monitorMode(){
        ARouter.monitorMode();
    }

    public static boolean isMonitorMode(){
        return ARouter.isMonitorMode();
    }

    public static void setLogger(ILogger userLogger){
        ARouter.setLogger(userLogger);
    }

}
