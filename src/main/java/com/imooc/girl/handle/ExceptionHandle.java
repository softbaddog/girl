package com.imooc.girl.handle;

import com.imooc.girl.domain.Result;
import com.imooc.girl.exception.GirlException;
import com.imooc.girl.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {

        if (e instanceof GirlException) {
            GirlException girlException = (GirlException)e;
            return ResultUtil.error(girlException.getCode(), e.getMessage());
        } else {
            return ResultUtil.error(-1, "未知错误");
        }
    }
}
