package com.capg.main.advice;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.capg.main.exception.InvalidOrderrException;

@RestControllerAdvice
public class MyAdviceForException {

    public MyAdviceForException() {
        System.out.println("advice object is created");
    }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ae) {

        Map<String, String> map = new LinkedHashMap<>();
         
        ae.getBindingResult().getFieldErrors().forEach(error->{
                    map.put(error.getField(), error.getDefaultMessage());
        });
        
        return map;
    }
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvalidOrderrException.class)
	public Map<String,String> handlInvalidUser(InvalidOrderrException ae){
			Map<String,String> map=new LinkedHashMap<>();
				
			map.put("order-error", ae.getMessage());
			return map;
	}
   
}