package com.michel.mall_test.extra.exceptions;

import com.michel.mall_test.extra.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.ok(BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(false)
                .withMessage("There is no user found.")
                .withData(null)
                .build());
    }

    @ExceptionHandler(FailedSaveFileException.class)
    public ResponseEntity<BaseResponse> handleFailedSaveFileException(FailedSaveFileException ex) {
        return ResponseEntity.ok(BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(false)
                .withMessage(ex.getMessage())
                .withData(null)
                .build());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<BaseResponse> handleRecordNotFoundException(RecordNotFoundException ex) {
        return ResponseEntity.ok(BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(false)
                .withMessage(ex.getMessage())
                .withData(null)
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc){
        StringBuilder builder = new StringBuilder();
        exc.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    builder.append("[").append(fieldName).append("]: ").append(errorMessage).append(" ");
                });
        return ResponseEntity.ok(BaseResponse.BaseResponseBuilder.aBaseResponse()
                        .withSuccess(false)
                        .withMessage(builder.toString())
                        .withData(null)
                .build());
    }


}
