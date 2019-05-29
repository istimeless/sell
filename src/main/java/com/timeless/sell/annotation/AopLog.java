package com.timeless.sell.annotation;

import java.lang.annotation.*;

/**
 * @author lijiayin
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopLog {
}
