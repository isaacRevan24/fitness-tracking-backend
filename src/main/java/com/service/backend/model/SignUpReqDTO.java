package com.service.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@ToString
@Builder
public class SignUpReqDTO implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 1680905458137628336L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Integer age;

    @NotBlank
    @Length(min = 0, max = 2)
    private String weightMetric;

    @NotNull
    private Double weight;

    @NotBlank
    @Length(min = 0, max = 2)
    private String heightMetric;

    @NotNull
    private Double height;

    public SignUpReqDTO(String username, String password, Integer age, String weightMetric, Double weight, String heightMetric, Double height) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.weightMetric = weightMetric;
        this.weight = weight;
        this.heightMetric = heightMetric;
        this.height = height;
    }

    public SignUpReqDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWeightMetric() {
        return weightMetric;
    }

    public void setWeightMetric(String weightMetric) {
        this.weightMetric = weightMetric;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHeightMetric() {
        return heightMetric;
    }

    public void setHeightMetric(String heightMetric) {
        this.heightMetric = heightMetric;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
