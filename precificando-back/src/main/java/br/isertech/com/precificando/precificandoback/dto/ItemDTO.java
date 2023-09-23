package br.isertech.com.precificando.precificandoback.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {

    private String description;
    private String measurement;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean isInStock;
    private String userId;

}
