package com.garage.orderdetails.parts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartsRepository extends JpaRepository<Parts, UUID> {
}
