package com.pritam.repository;

import com.pritam.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Long> {
    Optional<CartEntity> findByProductIdAndUserId(Long productId, Long userId);
    List<CartEntity> findAllByUserId(Long userId);
}
