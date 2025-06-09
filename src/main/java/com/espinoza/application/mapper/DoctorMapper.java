package com.espinoza.application.mapper;

import com.espinoza.domain.model.Doctor;
import com.espinoza.domain.model.EstadoDoctor;
import com.espinoza.web.dto.DoctorRequestDto;
import com.espinoza.web.dto.DoctorResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor toEntity(DoctorRequestDto dto) {
        return new Doctor(
                null,
                dto.getNombre(),
                dto.getEspecialidad(),
                EstadoDoctor.valueOf(dto.getEstado().toUpperCase())
        );
    }

    public DoctorResponseDto toDto(Doctor entity) {
        return new DoctorResponseDto(
                entity.getId(),
                entity.getNombre(),
                entity.getEspecialidad(),
                entity.getEstado().name()
        );
    }
}

