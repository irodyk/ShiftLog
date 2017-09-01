package com.deputy.shiftlog.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
