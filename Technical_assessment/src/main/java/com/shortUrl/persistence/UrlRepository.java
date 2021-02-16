package com.shortUrl.persistence;

import com.shortUrl.dto.UrlDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UrlRepository extends JpaRepository<UrlDTO, Long> {

    List<UrlDTO> findAll();

    UrlDTO findById(long id);
}
