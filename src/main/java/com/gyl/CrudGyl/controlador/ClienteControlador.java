package com.gyl.CrudGyl.controlador;

import com.gyl.CrudGyl.dto.request.ClienteRequestDto;
import com.gyl.CrudGyl.dto.response.ClienteResponseDto;
import com.gyl.CrudGyl.servicio.ClienteServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {
    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {this.clienteServicio = clienteServicio;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto crear(@Valid @RequestBody ClienteRequestDto dto) {return clienteServicio.crear(dto);}

    @GetMapping
    public List<ClienteResponseDto> listar() {return clienteServicio.listar();}

    @GetMapping("/{idBuscado}")
    public ClienteResponseDto buscarPorId(@PathVariable Long idBuscado) {
        return clienteServicio.buscarPorId(idBuscado);
    }

    @GetMapping("/nombre")
    public List<ClienteResponseDto> buscarPorNombre(@RequestParam String nombre) {
        return clienteServicio.buscarPorNombre(nombre);
    }

    @PutMapping("/{idBuscado}")
    public ClienteResponseDto actualizar(@PathVariable Long idBuscado, @Valid @RequestBody ClienteRequestDto dto) {
        return clienteServicio.actualizar(idBuscado, dto);
    }

    @DeleteMapping("/{idBuscado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idBuscado) {clienteServicio.eliminar(idBuscado);}
}