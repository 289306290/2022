package com.springbase.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
/**
 * 加了这个注解,spring不会扫描了,
 */
public @interface Encrypt {
}
