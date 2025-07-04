package com.espinoza.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDto {
    private Long id;
    private String nombre;
    private String especialidad;
    private String estado;
}
