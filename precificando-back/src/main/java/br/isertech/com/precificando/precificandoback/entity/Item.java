package br.isertech.com.precificando.precificandoback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item extends RepresentationModel<Item> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "ituser-uuid-generator")
    @GenericGenerator(
            name = "ituser-uuid-generator",
            strategy = "br.isertech.com.precificando.precificandoback.util.IserUUIDGenerator",
            parameters = @Parameter(name = "prefix", value = "Item")
    )
    private String id;
    private String description;
    private String measurement;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean isInStock;
    @ManyToOne
    @JsonBackReference
    private Stock stock;
}

