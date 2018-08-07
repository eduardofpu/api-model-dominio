package br.com.handler.all;


import br.com.error.Detail;
import br.com.error.ErrorDetail;
import br.com.error.ErrorValidation;
import br.com.error.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerAll extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?>  handleResourceNotFoundExcepition(ResourceNotFoundException rfnExcepition){
        Detail rnfDetails =  ErrorDetail.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not found")
                .detail(rfnExcepition.getMessage())
                .developerMessage(rfnExcepition.getClass().getName())
                .field("")
                .fieldMessage("")
                .build();
        return  new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvExcepition,
                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = manvExcepition.getBindingResult().getFieldErrors();
        String field = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        Detail rnfDetails =  ErrorValidation.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad request")
                .detail("Field validation error")
                .developerMessage(manvExcepition.getClass().getName())
                .field(field)
                .fieldMessage(fieldMessage)
                .build();
        return  new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
    }

    //Obter menssagem para todos os metodos de forma padronizada
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {

        Detail errorDetails =  ErrorDetail.builder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Internal exception")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .field("")
                .fieldMessage("")
                .build();
        return new ResponseEntity<Object>(errorDetails, headers, status);
    }
}
