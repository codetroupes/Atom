package com.codetroupes.moleculexrouter.interceptor;


import com.codetroupes.moleculexrouter.utils.RouteTypeUtils;

/**
 * A interceptor for checking {@link com.codetroupes.moleculexrouter.constant.RouteType#Login}
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
public abstract class LoginInterceptor extends RouteTypeInterceptor{

    @Override
    protected boolean onCheckRouteType(int routeTypes){
        return RouteTypeUtils.isLogin(routeTypes) && !isLogin();
    }

    public abstract boolean isLogin();

}
