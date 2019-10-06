package com.example.main.entregas;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends CrudRepository<Entregas, Integer> {}
