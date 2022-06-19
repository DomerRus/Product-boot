package ru.itmo.productboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.productboot.model.Coordinates;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
}
