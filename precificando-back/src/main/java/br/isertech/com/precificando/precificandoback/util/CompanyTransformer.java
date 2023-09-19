package br.com.isertech.myinvoice.myinvoiceback.util;

import br.com.isertech.myinvoice.myinvoiceback.dto.CompanyDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Company;

public class CompanyTransformer {

    private CompanyTransformer() {}

    public static Company fromDTO(CompanyDTO dto) {

        return Company.builder()
                .name(dto.getName())
                .fantasyName(dto.getFantasyName())
                .number(dto.getNumber())
                .address1(dto.getAddress1())
                .address2(dto.getAddress2())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .zipCode(dto.getZipCode())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }
}
