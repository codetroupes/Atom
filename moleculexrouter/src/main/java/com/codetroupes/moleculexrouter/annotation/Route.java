package com.codetroupes.moleculexrouter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define the route in navigator interfaces
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Route{

    /** path for navigation, You can get it from {@link android.os.Bundle} with {@link com.codetroupes.moleculexrouter.constant.RouteExtras#PathTo} */
    String path();

    /**
     * request code for activity navigation, you can get it from {@link android.os.Bundle} with
     * {@link com.codetroupes.moleculexrouter.constant.RouteExtras#RequestCode}
     */
    int requestCode() default -1;

    /** flags for activity navigation, you can get it from {@link android.os.Bundle} with {@link com.codetroupes.moleculexrouter.constant.RouteExtras#Flags} */
    int flags() default -1;

    /**
     * interceptor won't intercep this route if setting true, you can get it from {@link android.os.Bundle} with
     * {@link com.codetroupes.moleculexrouter.constant.RouteExtras#GreenChannel}
     */
    boolean greenChannel() default false;

    /** You can get it from {@link android.os.Bundle} with {@link com.codetroupes.moleculexrouter.constant.RouteExtras#Title} */
    String title() default "";

}
