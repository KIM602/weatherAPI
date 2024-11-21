package com.test.weatherapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in the format YYYY-MM-DD")
    private String date; // 날짜

    @NotBlank(message = "Region is required")
    @Size(max = 50, message = "Region should not exceed 50 characters")
    private String region; // 지역

    @NotBlank(message = "Weather description is required")
    private String weather; // 날씨 상태 (맑음, 비, 눈 등)

    @NotNull(message = "Temperature is required")
    @DecimalMin(value = "-50.0", message = "Temperature should not be below -50")
    private Double temperature; // 온도
}
