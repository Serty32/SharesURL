package com.example.sinelnikovserhii.sharesurl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sinelnikovserhii on 02.08.17.
 */

public interface Anotation {

    @Target(ElementType.LOCAL_VARIABLE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Annotation{
        int day();
        int month();
        int year();
    }
}
