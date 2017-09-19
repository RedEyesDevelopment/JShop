package jshop.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>, IdentifiableRepository { {
}
