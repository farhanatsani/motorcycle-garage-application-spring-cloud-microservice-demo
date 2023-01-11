package com.garage.orderdetails.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PartsService {
    private final PartsRepository partsRepository;
    public Parts registerParts(RegisterPartsRequest registerPartsRequest) {
        Parts parts = Parts.builder()
                .name(registerPartsRequest.getName())
                .price(registerPartsRequest.getPrice())
                .build();
        return partsRepository.save(parts);
    }
}
