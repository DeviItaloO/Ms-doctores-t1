package com.espinoza.application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaDto(
        Long id,
        LocalDate fecha,
        LocalTime hora,
        String paciente,
        Long doctorId,
        String estado,
        String notas
) {}
