package com.garage.orderdetails.parts;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parts")
@AllArgsConstructor
public class PartsController {
    private final PartsService partsService;
    public ResponseEntity<RegisterPartsResponse> registerParts(@RequestBody RegisterPartsRequest registerPartsRequest) {

        Parts parts = partsService.registerParts(registerPartsRequest);

        RegisterPartsResponse registerPartsResponse = RegisterPartsResponse.builder()
                .id(parts.getId().toString())
                .name(parts.getName())
                .price(parts.getPrice())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(registerPartsResponse);
    }
}
