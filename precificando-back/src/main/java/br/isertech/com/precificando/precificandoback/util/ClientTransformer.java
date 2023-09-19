package br.com.isertech.myinvoice.myinvoiceback.util;

import br.com.isertech.myinvoice.myinvoiceback.dto.ClientDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Client;

public class ClientTransformer {

    private ClientTransformer() {
    }

    public static Client fromDTO(ClientDTO dto) {

        return Client.builder()
                .name(dto.getName())
                .number(dto.getNumber())
                .address1(dto.getAddress1())
                .address2(dto.getAddress2())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .zipCode(dto.getZipCode())
                .contact(dto.getContact())
                .exchange(dto.getExchange())
                .build();

    }

    public static Client updateEntity(Client client, ClientDTO clientDTO) {
        Client updatedClient = fromDTO(clientDTO);
        updatedClient.setId(client.getId());
        return updatedClient;
    }
}
