package br.com.isertech.myinvoice.myinvoiceback.util;

import br.com.isertech.myinvoice.myinvoiceback.dto.ItemDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Invoice;
import br.com.isertech.myinvoice.myinvoiceback.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemTransformer {

    private ItemTransformer() {}

    public static List<Item> fromList(List<ItemDTO> dtoList, Invoice invoice) {
        List<Item> list = new ArrayList<>();
        dtoList.stream().map(ItemTransformer::fromDto).forEach(item -> {
            item.setInvoice(invoice);
            list.add(item);
        });
        return list;
    }

    private static Item fromDto(ItemDTO dto) {
        return Item.builder()
                .description(dto.getDescription())
                .hours(dto.getHours())
                .hourRate(dto.getHourRate())
                .total(dto.getTotal())
                .build();
    }
}
