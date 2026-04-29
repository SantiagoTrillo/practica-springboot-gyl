package com.gyl.CrudGyl.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime fechaVenta;
    @Column(nullable = false)
    private Double total;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}