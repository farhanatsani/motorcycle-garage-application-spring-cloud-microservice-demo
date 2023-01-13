package com.garage.client.parts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient("parts")
public interface PartsClient {
    @GetMapping("/parts/{id}")
    List<PartsResponse> getParts(@PathVariable("id") UUID id);
}
