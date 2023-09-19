package br.com.isertech.myinvoice.myinvoiceback.util;

import br.com.isertech.myinvoice.myinvoiceback.dto.UserDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.MIUser;

public class MIUserTransformer {

    private MIUserTransformer() {}

    public static MIUser fromDTO(UserDTO dto) {
        return MIUser.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public static MIUser fromDTO(MIUser user, UserDTO dto) {
        MIUser updatedUser = fromDTO(dto);
        updatedUser.setId(user.getId());

        return updatedUser;
    }
}
