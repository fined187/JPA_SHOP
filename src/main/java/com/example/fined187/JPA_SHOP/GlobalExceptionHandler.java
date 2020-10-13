package com.example.fined187.JPA_SHOP;

import com.example.fined187.JPA_SHOP.domain.dto.ApiResponse;
import com.example.fined187.JPA_SHOP.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Controller
//  log4j ==> log file로 묶어줌.
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    ResponseEntity<?> NotFoundException(NotFoundException e) {

        log.error(e.getMessage());

//      실제 운영중인 어플리케이션 또는 모바일 겸용 . . .
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail("NOT_FOUND", e.getMessage()));

//      API : 지도, 주소, 은행, 공공기관 API, 준 공공기관 제공하는 API,
//      날씨, 휴일 ...

//      return ResponseEntity.ok().body("{}");          //  잘 받아왔지만 데이터가 비어있다.
    }
}
