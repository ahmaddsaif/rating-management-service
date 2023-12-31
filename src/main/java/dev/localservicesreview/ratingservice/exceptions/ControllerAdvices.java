package dev.localservicesreview.ratingservice.exceptions;

import dev.localservicesreview.ratingservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(
                new ExceptionDto(HttpStatus.NOT_FOUND, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InternalServerException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(InternalServerException ex) {
        return new ResponseEntity<>(
                new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(BadRequestException ex) {
        return new ResponseEntity<>(
                new ExceptionDto(HttpStatus.BAD_REQUEST, ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
