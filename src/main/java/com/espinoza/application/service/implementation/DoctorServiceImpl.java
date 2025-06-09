package com.espinoza.application.service.implementation;


import com.espinoza.application.mapper.DoctorMapper;
import com.espinoza.application.service.interfaces.IDoctorService;
import com.espinoza.domain.event.DoctorEstadoEvent;
import com.espinoza.domain.model.Doctor;
import com.espinoza.domain.model.EstadoDoctor;
import com.espinoza.domain.repository.IDoctorRepository;
import com.espinoza.web.dto.DoctorRequestDto;
import com.espinoza.web.dto.DoctorResponseDto;
import com.espinoza.config.kafkaProducerComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final kafkaProducerComponent kafkaProducer;

    @Override
    public DoctorResponseDto create(DoctorRequestDto dto) {
        Doctor doctor = doctorMapper.toEntity(dto);
        Doctor saved = doctorRepository.save(doctor);
        return doctorMapper.toDto(saved);
    }

    @Override
    public List<DoctorResponseDto> findAll() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponseDto findById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));
    }

    @Override
    public DoctorResponseDto update(Long id, DoctorRequestDto dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));

        doctor.setNombre(dto.getNombre());
        doctor.setEspecialidad(dto.getEspecialidad());
        doctor.setEstado(EstadoDoctor.valueOf(dto.getEstado().toUpperCase()));

        Doctor updated = doctorRepository.save(doctor);
        return doctorMapper.toDto(updated);
    }

    @Override
    public DoctorResponseDto updateEstado(Long id, String nuevoEstado) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));

        doctor.setEstado(EstadoDoctor.valueOf(nuevoEstado.toUpperCase()));
        Doctor actualizado = doctorRepository.save(doctor);

        DoctorEstadoEvent evento = DoctorEstadoEvent.builder()
                .doctorId(actualizado.getId())
                .nuevoEstado(actualizado.getEstado().name())
                .nombre(actualizado.getNombre())
                .build();

        kafkaProducer.enviarEventoCambiarEstado(evento);

        return doctorMapper.toDto(actualizado);
    }

    @Override
    public void delete(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));
        doctorRepository.delete(doctor);
    }
}