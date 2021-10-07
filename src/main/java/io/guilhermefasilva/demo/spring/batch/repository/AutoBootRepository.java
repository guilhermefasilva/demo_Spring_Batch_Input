package io.guilhermefasilva.demo.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.guilhermefasilva.demo.spring.batch.models.AutoBoot;

@Repository
public interface AutoBootRepository extends JpaRepository<AutoBoot, Long> {

}
