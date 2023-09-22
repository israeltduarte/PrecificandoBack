package br.isertech.com.precificando.precificandoback.util;

import br.isertech.com.precificando.precificandoback.dto.ItemDTO;
import br.isertech.com.precificando.precificandoback.entity.Item;

public class ItemTransformer {

    private ItemTransformer() {
    }

    public static Item fromDTO(ItemDTO dto) {

        return Item.builder()
                .description(dto.getDescription())
                .measurement(dto.getMeasurement())
                .build();
    }

}
