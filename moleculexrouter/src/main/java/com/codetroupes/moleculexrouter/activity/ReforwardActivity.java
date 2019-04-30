package com.codetroupes.moleculexrouter.activity;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.codetroupes.moleculexrouter.XRouter;
import com.codetroupes.moleculexrouter.callback.NavigationCallbackWrapper;
import com.codetroupes.moleculexrouter.constant.RouteExtras;
import com.codetroupes.moleculexrouter.navigator.Router;
import com.codetroupes.moleculexrouter.navigator.impl.NavigatorBuilder;


/**
 * Activity for processing the postcard and reforwarding it to the target
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
public class ReforwardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Router router = XRouter.getRouter();
        NavigatorBuilder build = router.build(getIntent());
        if(build != null){
            Postcard postcard = build.postcard();
            postcard.navigation(this,
                                postcard.getExtras().getInt(RouteExtras.RequestCode, -1),
                                new NavigationCallbackWrapper(new NavigationCallback(){
                                    @Override
                                    public void onFound(Postcard postcard){
                                        safeFinish();
                                    }
                                    @Override
                                    public void onLost(Postcard postcard){
                                        safeFinish();
                                    }
                                    @Override
                                    public void onArrival(Postcard postcard){
                                        safeFinish();
                                    }
                                    @Override
                                    public void onInterrupt(Postcard postcard){
                                        safeFinish();
                                    }
                                }));
        }else{
            safeFinish();
        }
    }

    protected void safeFinish(){
        if(!isFinishing()){
            finish();
        }
    }

}
