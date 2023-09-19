package br.isertech.com.precificando.precificandoback.util;

import br.isertech.com.precificando.precificandoback.dto.UserDTO;
import br.isertech.com.precificando.precificandoback.entity.MIUser;

public class MIUserTransformer {

    private MIUserTransformer() {
    }

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
