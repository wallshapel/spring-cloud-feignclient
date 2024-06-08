package com.moto.moto.controllers;

import com.moto.moto.entities.Moto;
import com.moto.moto.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/motos")
@CrossOrigin(origins = "*")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Moto>> index() {
        List<Moto> motos = motoService.index();
        if (motos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Moto> show(@PathVariable("id") Long id) {
        Moto moto = motoService.show(id);
        if (moto == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(moto);
    }

    @GetMapping("/user/{userId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Moto>>showByUserId(@PathVariable Long userId) {
        List<Moto> motos = motoService.byUserId(userId);
        if (motos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Moto> store(@RequestBody Moto moto) {
        Moto newMoto = motoService.store(moto);
        URI location = URI.create("/motos/" + newMoto.getId());
        return ResponseEntity.created(location).build(); // Devuelve en los headers una llave llamada Location cuyo valor es la url para acceder al recurso creado
    }

}
