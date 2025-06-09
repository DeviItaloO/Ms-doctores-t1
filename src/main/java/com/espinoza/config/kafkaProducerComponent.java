package com.espinoza.config;

import com.espinoza.domain.event.DoctorEstadoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class kafkaProducerComponent {
    private final KafkaTemplate<String, DoctorEstadoEvent> kafkaTemplate;
    public void enviarEventoCambiarEstado(DoctorEstadoEvent event){
    kafkaTemplate.send("doctor-topic", event);
    log.info("Evento de kafka enviado {}", event);
    }
}
