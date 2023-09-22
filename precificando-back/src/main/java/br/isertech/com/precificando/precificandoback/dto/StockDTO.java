package br.isertech.com.precificando.precificandoback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDTO {

    private String userId;
    private List<ItemDTO> items;
    private LocalDateTime created;
    private LocalDateTime updated;

}
