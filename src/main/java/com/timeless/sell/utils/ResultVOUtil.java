package com.timeless.sell.utils;

import com.timeless.sell.vo.ResultVO;

/**
 * @author lijiayin
 */
public class ResultVOUtil {
    
    public static ResultVO success(Object object){
        return ResultVO.builder().code(0).msg("成功")
                .data(object).build();
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code, String msg){
        return ResultVO.builder().code(code).msg(msg).build();
    }
}
