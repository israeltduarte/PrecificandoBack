package br.com.isertech.myinvoice.myinvoiceback.error;

import br.com.isertech.myinvoice.myinvoiceback.error.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CustomErrorResponse> customException(Exception e) {
//
//        CustomErrorResponse errors = CustomErrorResponse.builder().message(e.getMessage())
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
//
//        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> invoiceNotFound(Exception e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> companyNotFound(Exception e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> clientNotFound(Exception e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> roleNotFound(Exception e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> roleAlreadyExists(Exception e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<CustomErrorResponse> operationFailed(Exception e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> userAlreadyExists(Exception e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> emailAlreadyExists(Exception e) {

        CustomErrorResponse errors = CustomErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}