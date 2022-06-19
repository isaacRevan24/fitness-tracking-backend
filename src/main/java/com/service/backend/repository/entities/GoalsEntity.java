package com.service.backend.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "goals")
@Table(name = "goals")
public class GoalsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 693609396463190403L;

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "steps")
    private Integer steps;

}
