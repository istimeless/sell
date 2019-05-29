package com.timeless.sell.utils;


import com.timeless.sell.enums.CodeEnum;

/**
 * @author lijiayin
 */
public class EnumUtil {
    
    public static <C, T extends CodeEnum<C>>T getByCode(C code , Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
