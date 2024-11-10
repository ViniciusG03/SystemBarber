package org.menosprezo.systembarber.controller;

import org.menosprezo.systembarber.dto.BarbeariaDTO;
import org.menosprezo.systembarber.model.Barbearia;
import org.menosprezo.systembarber.service.BarbeariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/barbearias")
@CrossOrigin(origins = "http://localhost:5500")
public class BarbeariaController {

    @Autowired
    private BarbeariaService barbeariaService;

    @PostMapping
    public ResponseEntity<Barbearia> criarBarbearia(@RequestBody BarbeariaDTO barbeariaDTO) {
        Barbearia novaBarbearia = barbeariaService.criarBarbearia(barbeariaDTO);
        return new ResponseEntity<>(novaBarbearia, HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Barbearia>> buscarPorLocalizacao(@RequestParam(required = false) String localizacao) {
        List<Barbearia> barbearias = barbeariaService.buscarPorLocalizacao(localizacao);
        return ResponseEntity.ok(barbearias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbearia> buscarBarbeariaPorId(@PathVariable Long id) {
        return barbeariaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todas")
    public ResponseEntity<List<Barbearia>> listarTodasBarbearias() {
        return ResponseEntity.ok(barbeariaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbearia> atualizarBarbearia(@PathVariable Long id, @RequestBody BarbeariaDTO barbeariaDTO) {
        try {
            Barbearia barbeariaAtualizada = barbeariaService.atualizarBarbearia(id, barbeariaDTO);
            return ResponseEntity.ok(barbeariaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBarbearia(@PathVariable Long id) {
        try {
            barbeariaService.deletarBarbearia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
