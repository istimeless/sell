package com.timeless.sell.vo;

/**
 * @author lijiayin
 */
public class MathUtil {

    private static final Double RANGE = 0.01;
    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean euqals(Double d1, Double d2){
        Double result = Math.abs(d1 - d2);
        if(result < RANGE){
            return true;
        }
        return false;
    }
}

