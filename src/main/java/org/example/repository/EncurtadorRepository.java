package org.example.repository;

import org.example.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncurtadorRepository extends JpaRepository<Url, Long> {
    public Url findByUrlEncurtada(String urlEncurtada);
}
