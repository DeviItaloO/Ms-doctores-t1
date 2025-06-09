package com.espinoza.application.service.interfaces;

import com.espinoza.web.dto.DoctorRequestDto;
import com.espinoza.web.dto.DoctorResponseDto;

import java.util.List;

public interface IDoctorService {
    DoctorResponseDto create(DoctorRequestDto dto);
    List<DoctorResponseDto> findAll();
    DoctorResponseDto findById(Long id);
    DoctorResponseDto update(Long id, DoctorRequestDto dto);
    DoctorResponseDto updateEstado(Long id, String nuevoEstado);
    void delete(Long id);
}
