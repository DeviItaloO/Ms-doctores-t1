package com.espinoza.web.controller;

import com.espinoza.application.service.interfaces.IDoctorService;
import com.espinoza.web.dto.DoctorRequestDto;
import com.espinoza.web.dto.DoctorResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(@AuthenticationPrincipal Jwt jwt) {
        System.out.println("JWT CLAIMS:");
        jwt.getClaims().forEach((k, v) -> System.out.println(k + " = " + v));
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        System.out.println("JWT CLAIMS:");
        jwt.getClaims().forEach((k, v) -> System.out.println(k + " = " + v));
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto dto, @AuthenticationPrincipal Jwt jwt) {
        System.out.println("JWT CLAIMS:");
        jwt.getClaims().forEach((k, v) -> System.out.println(k + " = " + v));
        DoctorResponseDto created = doctorService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequestDto dto,  @AuthenticationPrincipal Jwt jwt) {
        System.out.println("JWT CLAIMS:");
        jwt.getClaims().forEach((k, v) -> System.out.println(k + " = " + v));
        return ResponseEntity.ok(doctorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        System.out.println("JWT CLAIMS:");
        jwt.getClaims().forEach((k, v) -> System.out.println(k + " = " + v));
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}