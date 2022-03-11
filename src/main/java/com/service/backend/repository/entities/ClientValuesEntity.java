package com.service.backend.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Severiano Atencio
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "client_values")
@Table(name = "client_values")
public class ClientValuesEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4791257399037842444L;

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "height")
    private Double height;

    @Column(name = "height_metric", length = 2)
    private String heightMetric;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "weight_metric", length = 2)
    private String weightMetric;

}
