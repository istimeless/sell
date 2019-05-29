package com.timeless.sell.handler;

import com.timeless.sell.config.ProjectConfig;
import com.timeless.sell.config.WeChatAccountConfig;
import com.timeless.sell.exception.ResponseStatusException;
import com.timeless.sell.exception.SellAuthorizeException;
import com.timeless.sell.exception.SellException;
import com.timeless.sell.utils.ResultVOUtil;
import com.timeless.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lijiayin
 */
@ControllerAdvice
public class SellExceptionHandler {
    
    @Autowired
    private WeChatAccountConfig accountConfig;
    
    @Autowired
    private ProjectConfig projectConfig;
    
    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
        .concat(accountConfig.getWeChatOpenAuthorize())
        .concat("/sell/wechat/qrAuthorize")
        .concat("?returnUrl=")
        .concat(projectConfig.getSell())
        .concat("/sell/seller/login"));
    }
    
    @ResponseBody
    @ExceptionHandler(value = SellException.class)
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = ResponseStatusException.class)
    public void handlerResponseStatusException(ResponseStatusException e){
        
    }
}
