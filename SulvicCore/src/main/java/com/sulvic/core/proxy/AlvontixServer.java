package com.sulvic.core.proxy;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static net.minecraftforge.fml.relauncher.Side.SERVER;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.minecraftforge.fml.relauncher.SideOnly;

@Retention(RUNTIME)
@Target({TYPE, FIELD, METHOD, CONSTRUCTOR})
@SideOnly(SERVER)
public @interface AlvontixServer{}
