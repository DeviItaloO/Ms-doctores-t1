package com.espinoza.domain.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DoctorEstadoEvent {
    private Long doctorId;
    private String nombre;
    private String nuevoEstado;
}
