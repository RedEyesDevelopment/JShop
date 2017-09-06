package jshop.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface IdentifiableRepository<ID extends Serializable> {
    List<ID> getSortedIds( Pageable pageable);
}
