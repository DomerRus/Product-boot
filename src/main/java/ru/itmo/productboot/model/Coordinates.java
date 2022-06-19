package ru.itmo.productboot.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Coordinates {
    @Id
    //@SequenceGenerator(name="COORDINATES_SEQUENCE", sequenceName="SQ_COORDINATES_SEQUENCE")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(name = "x")
    private Integer x;

    @Min(-687L)
    @Column(name = "y")
    private Double y; //Значение поля должно быть больше -687
}
