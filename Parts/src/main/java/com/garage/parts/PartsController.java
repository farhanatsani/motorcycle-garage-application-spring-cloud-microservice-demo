package com.garage.parts;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/parts")
@AllArgsConstructor
public class PartsController {
    private final PartsService partsService;
    @PostMapping
    public ResponseEntity<RegisterPartsResponse> registerParts(@RequestBody RegisterPartsRequest registerPartsRequest) {

        log.info("New Parts Registration {}", registerPartsRequest);

        Parts parts = partsService.registerParts(registerPartsRequest);

        RegisterPartsResponse registerPartsResponse = RegisterPartsResponse.builder()
                .id(parts.getId().toString())
                .name(parts.getName())
                .price(parts.getPrice())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(registerPartsResponse);
    }
    @GetMapping
    public ResponseEntity<List<PartsResponse>> findAll() {

        List<PartsResponse> responses = partsService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<PartsResponse>> findById(@PathVariable UUID id) {
        List<PartsResponse> responses = partsService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }
}
