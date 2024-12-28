package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.KnowledgeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeDomainRepository extends JpaRepository<KnowledgeDomain, String> {

}
