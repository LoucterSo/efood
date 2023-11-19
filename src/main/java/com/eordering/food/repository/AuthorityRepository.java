package com.eordering.food.repository;

import com.eordering.food.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
