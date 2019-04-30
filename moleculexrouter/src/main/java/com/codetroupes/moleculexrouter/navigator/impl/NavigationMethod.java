package com.codetroupes.moleculexrouter.navigator.impl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.codetroupes.moleculexrouter.XRouter;
import com.codetroupes.moleculexrouter.annotation.DefaultExtras;
import com.codetroupes.moleculexrouter.annotation.Extra;
import com.codetroupes.moleculexrouter.annotation.Extras;
import com.codetroupes.moleculexrouter.annotation.Route;
import com.codetroupes.moleculexrouter.constant.RouteExtras;
import com.codetroupes.moleculexrouter.data.BundleWrapper;
import com.codetroupes.moleculexrouter.data.IBundleWrapper;
import com.codetroupes.moleculexrouter.navigator.ActivityNavigator;
import com.codetroupes.moleculexrouter.navigator.FragmentNavigator;
import com.codetroupes.moleculexrouter.navigator.Navigator;
import com.codetroupes.moleculexrouter.navigator.ServiceNavigator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * A helper for invoking navigator's method
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
final class NavigationMethod{

    private Class<?> mReturnType;
    private Annotation[][] mParameterAnnotations;

    private Route mRoute;
    private DefaultExtras mDefaultExtras;

    NavigationMethod(Method method){
        // init annotations
        mReturnType = method.getReturnType();
        mParameterAnnotations = method.getParameterAnnotations();
        Annotation[] methodAnnotations = method.getAnnotations();
        for(Annotation annotation : methodAnnotations){
            if(annotation instanceof Route){
                mRoute = (Route) annotation;
            }else if(annotation instanceof DefaultExtras){
                mDefaultExtras = (DefaultExtras) annotation;
            }
        }
        if(mRoute == null){
            throw new IllegalArgumentException("no Route annotation found");
        }
    }

    private static boolean isExtendsOf(Class<?> returnType, Class<?> clazz){
        return returnType == clazz || clazz.isAssignableFrom(returnType);
    }
    private static boolean isNavigator(Class<?> returnType){
        return returnType == ActivityNavigator.class
               || returnType == FragmentNavigator.class
               || returnType == ServiceNavigator.class
               || returnType == Navigator.class;
    }

    Object invoke(Object[] args){

        NavigatorBuilder builder = XRouter.getRouter().build(mRoute.path());

        IBundleWrapper extras = processExtras(args);
        if(extras != null && !extras.isEmpty()){
            builder.with(extras.getBundle());
        }

        Navigator navigator = builder.setGreenChannel(mRoute.greenChannel())
                                     .withRequestCode(mRoute.requestCode())
                                     .withFlags(mRoute.flags())
                                     .navigator();

        // check to return
        Class<?> returnType = mReturnType;
        if(returnType == Void.TYPE){
            navigator.startActivity();
        }else if(isNavigator(returnType)){
            return navigator;
        }else if(isExtendsOf(returnType, Fragment.class)){
            return navigator.fragment();
        }else if(returnType == Intent.class){
            return navigator.intent();
        }else if(returnType == Uri.class){
            return navigator.uri();
        }else{
            Object service = XRouter.getRouter().service(mRoute.path());
            if(service == null){
                throw new RuntimeException(
                        new ClassNotFoundException("no route find: " + mRoute.path())
                );
            }else{
                if(isExtendsOf(service.getClass(), mReturnType)){
                    return service;
                }else{
                    throw new RuntimeException(
                            new ClassCastException(
                                    "Couldn't convert " + service.getClass().getCanonicalName()
                                    + " to " + mReturnType.getCanonicalName()
                            )
                    );
                }
            }
        }
        return navigator;

    }

    private IBundleWrapper processExtras(Object[] args){
        IBundleWrapper bundle = new BundleWrapper();
        bundle.put(RouteExtras.Title, mRoute.title());
        // process parameter annotations
        for(int i = 0; i < mParameterAnnotations.length; i++){
            Annotation[] annotations = mParameterAnnotations[i];
            for(Annotation annotation : annotations){
                Object arg = args[i];
                if(annotation instanceof Extra){
                    String key = ((Extra) annotation).value();
                    try{
                        bundle.put(key, arg);
                    }catch(Exception e){
                        throw new IllegalArgumentException("unsupport extra:" + key + "=" + arg + " in Bundle", e);
                    }
                }else if(annotation instanceof Extras){
                    if(arg instanceof Bundle){
                        bundle.put((Bundle) arg);
                    }else if(arg instanceof Map){
                        Map<String,Object> extrasMap;
                        try{
                            extrasMap = ((Map<String,Object>) arg);
                        }catch(Exception e){
                            throw new IllegalArgumentException("@Extras only support Map<String,Object>", e);
                        }
                        for(String key : extrasMap.keySet()){
                            bundle.put(key, extrasMap.get(key));
                        }
                    }else{
                        throw new IllegalArgumentException("unsupport extras:" + arg);
                    }
                }
            }
        }
        // process default extras
        if(mDefaultExtras != null){
            int[] types = mDefaultExtras.type();
            String[] keys = mDefaultExtras.key();
            String[] values = mDefaultExtras.value();
            if(types.length != keys.length || types.length != values.length){
                throw new IllegalArgumentException("default extras must be one-to-one correspondence");
            }else{
                for(int i = 0; i < types.length; i++){
                    bundle.put(types[i], keys[i], values[i]);
                }
            }
        }
        return bundle;
    }

}
