package br.isertech.com.precificando.precificandoback.util;

import br.isertech.com.precificando.precificandoback.dto.StockDTO;
import br.isertech.com.precificando.precificandoback.entity.Stock;

public class StockTransformer {

    public static Stock fromDTO(StockDTO dto) {

        return Stock.builder()
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .build();
    }

}
