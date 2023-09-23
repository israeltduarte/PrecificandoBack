//package br.isertech.com.precificando.precificandoback.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;
//import org.springframework.hateoas.RepresentationModel;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Builder
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Stock extends RepresentationModel<Stock> implements Serializable {
//
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(generator = "ituser-uuid-generator")
//    @GenericGenerator(
//            name = "ituser-uuid-generator",
//            strategy = "br.isertech.com.precificando.precificandoback.util.IserUUIDGenerator",
//            parameters = @Parameter(name = "prefix", value = "Stock")
//    )
//    private String id;
//    private LocalDateTime created;
//    private LocalDateTime updated;
//    @OneToOne
//    @JsonBackReference
//    private ITUser user;
//
//
//}
//
