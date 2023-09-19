package br.com.isertech.myinvoice.myinvoiceback.util;

import br.com.isertech.myinvoice.myinvoiceback.dto.InvoiceDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Invoice;

public class InvoiceTransformer {

    private InvoiceTransformer() {}

    public static Invoice fromDTO(InvoiceDTO dto) {
        return Invoice.builder()
                .number(dto.getNumber())
                .date(dto.getDate())
                .total(dto.getTotal())
                .isPublished(dto.isPublished())
                .currency(dto.getCurrency())
                .build();
    }
}
