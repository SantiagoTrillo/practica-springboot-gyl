package com.gyl.CrudGyl.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalles_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer cantidadProducto;
    @Column(nullable = false)
    private Double precioUnitario;
    @Column(nullable = false)
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
}