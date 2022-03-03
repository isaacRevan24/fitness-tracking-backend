package com.service.backend.repository.entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "client")
@Table(name = "client")
public class ClientEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6959746196273371712L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Integer age;

}
