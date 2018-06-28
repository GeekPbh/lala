package com.pbh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GobleAdvice {

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public Map<String, Object> ValiException(BindException be) {
		 Map<String,Object> map = new HashMap<String,Object>();
		 BindingResult result = be.getBindingResult();
		  if (result.hasErrors()) {  
	            List<ObjectError> list = result.getAllErrors();  
	            for (ObjectError error : list) {  
	                FieldError ferror =  (FieldError) error;
	                map.put(ferror.getField(), ferror.getDefaultMessage());
	            }  
	  
	       }
	    return map;
	}
}
