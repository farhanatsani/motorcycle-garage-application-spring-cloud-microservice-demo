package com.garage.parts;

import com.garage.client.parts.PartsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<PartsResponse> findAll() {
        List<Parts> partsList = partsRepository.findAll();
        List<PartsResponse> partsResponses = partsList
                .stream()
                .map(parts ->
                        PartsResponse.builder()
                                .id(parts.getId().toString())
                                .name(parts.getName())
                                .price(parts.getPrice())
                                .build())
                .collect(Collectors.toList());
        return partsResponses;
    }
    public List<PartsResponse> findById(UUID id) {
        List<Parts> partsList = partsRepository.findById(id).stream().collect(Collectors.toList());
        List<PartsResponse> partsResponses = partsList
                .stream()
                .map(parts ->
                        PartsResponse.builder()
                                .id(parts.getId().toString())
                                .name(parts.getName())
                                .price(parts.getPrice())
                                .build())
                .collect(Collectors.toList());
        return partsResponses;
    }
}
