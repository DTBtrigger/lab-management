package org.example.labmanagement.controller;

import org.example.labmanagement.exception.Code;
import org.example.labmanagement.exception.XException;
import org.example.labmanagement.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(XException.class)
    public ResultVO handleXexception(XException e) {
        if (e.getCode() != null) {
            return ResultVO.error(e.getCode());
        }
        return ResultVO.error(e.getNumber(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e) {
        return ResultVO.error(Code.ERROR, e.getMessage());
    }
}
