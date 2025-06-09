package com.espinoza.application.client;

import com.espinoza.application.dto.CitaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-citas")
public interface CitaClient {

    @GetMapping("/api/citas/{id}")
    CitaDto obtenerCitaPorId(@PathVariable Long id);
}
