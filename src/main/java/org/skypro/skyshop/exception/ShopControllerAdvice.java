package org.skypro.skyshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {
        // Указываем уникальный машиночитаемый код ошибки
        // и передаем сообщение, которое было сформировано в StorageService
        ShopError shopError = new ShopError("PRODUCT_NOT_FOUND", e.getMessage());

        // Возвращаем ResponseEntity с телом ошибки и HTTP-кодом 404
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
