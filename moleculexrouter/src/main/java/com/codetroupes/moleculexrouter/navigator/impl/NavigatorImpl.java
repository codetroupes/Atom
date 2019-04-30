package com.codetroupes.moleculexrouter.navigator.impl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.codetroupes.moleculexrouter.XRouter;
import com.codetroupes.moleculexrouter.activity.ReforwardActivity;
import com.codetroupes.moleculexrouter.callback.RouteCallback;
import com.codetroupes.moleculexrouter.constant.RouteExtras;
import com.codetroupes.moleculexrouter.navigator.Navigator;
import com.codetroupes.moleculexrouter.utils.TypeUtils;


/**
 * The implementation of {@link Navigator}
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
class NavigatorImpl implements Navigator {

    private Postcard mPostcard;

    NavigatorImpl(Postcard postcard){
        mPostcard = postcard;
    }

    @Override
    public <T extends Fragment> T fragment(){
        return (T) mPostcard.navigation();
    }

    @Override
    public <T extends IProvider> T service(){
        return (T) mPostcard.navigation();
    }

    @Override
    public void startActivity(){
        startActivityForResult(null);
    }

    @Override
    public void startActivityForResult(RouteCallback callback){
        int requestCode = -1;
        Bundle extras = mPostcard.getExtras();
        if(extras != null){
            // check the request code
            requestCode = extras.getInt(RouteExtras.RequestCode, requestCode);
            if(requestCode == -1){
                requestCode = 200;
            }
        }
        XRouter.getRouter().startActivityForResult(mPostcard, requestCode, callback);
    }

    @Override
    public Uri uri(){
        Uri uri = mPostcard.getUri();
        if(uri != null){
            return uri;
        }
        String path = mPostcard.getPath();
        path = path.replaceAll("\\/+", "/");
        if(path.startsWith("/")){
            path = path.substring(1);
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(XRouter.SCHEME)
               .authority(XRouter.AUTHORITY)
               .appendPath(path);
        Bundle extras = mPostcard.getExtras();
        if(extras != null){
            for(String key : extras.keySet()){
                Object value = extras.get(key);
                if(value != null){
                    // check the value's type
                    if(TypeUtils.isFundamentalType(value)){
                        builder.appendQueryParameter(key, value.toString());
                    }else{
                        throw new IllegalArgumentException("unsupport query:" + value);
                    }
                }
            }
        }
        return builder.build();
    }

    @Override
    public Intent intent(){
        //build a reforwaring activity intent for processing the target
        Intent intent = new Intent(XRouter.getRouter().getContext(), ReforwardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = mPostcard.getUri();
        if(uri != null){
            intent.setData(mPostcard.getUri());
        }else{
            intent.putExtra(RouteExtras.PathTo, mPostcard.getPath());
        }
        Bundle extras = mPostcard.getExtras();
        if(extras != null && !extras.isEmpty()){
            intent.putExtras(extras);
        }
        return intent;
    }

}
