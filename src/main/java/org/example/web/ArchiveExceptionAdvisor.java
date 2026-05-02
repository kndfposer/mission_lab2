package org.example.web;

import jakarta.servlet.http.HttpServletRequest;
import org.example.web.view.ArchiveFaultView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ArchiveExceptionAdvisor {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ArchiveFaultView> handleBadRequest(IllegalArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ArchiveFaultView(LocalDateTime.now(), 400, "Bad Request", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ArchiveFaultView> handleIo(IOException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ArchiveFaultView(LocalDateTime.now(), 400, "Bad Request", "Ошибка чтения файла", request.getRequestURI()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ArchiveFaultView> handleSize(MaxUploadSizeExceededException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ArchiveFaultView(LocalDateTime.now(), 400, "Bad Request", "Размер загружаемого файла превышает допустимый предел", request.getRequestURI()));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ArchiveFaultView> handleMultipart(MultipartException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ArchiveFaultView(LocalDateTime.now(), 400, "Bad Request", "Ошибка загрузки файла. Возможно, файл слишком большой или повреждён", request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ArchiveFaultView> handleOther(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ArchiveFaultView(LocalDateTime.now(), 500, "Internal Server Error", ex.getMessage(), request.getRequestURI()));
    }
}
