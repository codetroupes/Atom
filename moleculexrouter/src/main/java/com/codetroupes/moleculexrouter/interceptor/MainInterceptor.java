package com.codetroupes.moleculexrouter.interceptor;

import android.content.Intent;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.codetroupes.moleculexrouter.utils.RouteTypeUtils;


/**
 * A interceptor for checking {@link com.codetroupes.moleculexrouter.constant.RouteType#Main}
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
public abstract class MainInterceptor extends RouteTypeInterceptor{

    @Override
    protected boolean onCheckRouteType(int routeTypes){
        return RouteTypeUtils.isMain(routeTypes);
    }

    @Override
    protected void onInterrupt(final Postcard postcard, final InterceptorCallback callback){
        postcard.withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        callback.onContinue(postcard);
    }

}
