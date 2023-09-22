package br.isertech.com.precificando.precificandoback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {

    private String description;
    private String measurement;
    private String stockId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean isInStock;

}
