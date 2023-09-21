package br.isertech.com.precificando.precificandoback.error.exception;

import java.io.Serial;

public class StockNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public StockNotFoundException(String message) {
        super(message);
    }

}
