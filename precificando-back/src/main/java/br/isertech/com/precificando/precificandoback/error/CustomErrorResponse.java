package br.com.isertech.myinvoice.myinvoiceback.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomErrorResponse {

    private int status;
    private String message;

}
