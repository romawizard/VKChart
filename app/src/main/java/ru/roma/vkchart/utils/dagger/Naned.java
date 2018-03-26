package ru.roma.vkchart.utils.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Ilan on 26.03.2018.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Naned {

    String value()  default "";
}
