package com.codetroupes.moleculexrouter.annotation;

import com.codetroupes.moleculexrouter.constant.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This for navigation's default extras. You can get it from {@link android.os.Bundle} with the key {@link #key()}
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultExtras{

    @Type int[] type() default Type.String;
    String[] key();
    String[] value();

}
