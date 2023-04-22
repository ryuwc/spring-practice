package jpabook.jpashop.controller;

import jpabook.jpashop.exception.CommonResponse;
import jpabook.jpashop.exception.Exception;
import jpabook.jpashop.exception.InvalidateUserException;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final MemberService memberService;

    @ExceptionHandler(InvalidateUserException.class)
    // 409 상태 코드 반환
    @ResponseStatus(HttpStatus.CONFLICT)
    private CommonResponse customException(InvalidateUserException e) {
        log.error(e.getMessage());
        return memberService.getErrorResponse(Exception.MY_EXCEPTION.getCode(), Exception.MY_EXCEPTION.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // NotEmpty, NotNull 등의 validation 에러를 처리하는 핸들러
    private CommonResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return memberService.getErrorResponse(400, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

}
