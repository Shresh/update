package com.example.assignment.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "battery",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"}, name = "unique_battery_name"),
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Battery extends BaseId {
    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotNull
    @NotBlank
    @Column(name = "postcode")
    private String postcode;
    @NotNull
    @Column(name = "capacity")
    private int capacity;
}
