package jshop.repositories;

import jshop.model.GrantedAuthorityEntity;
import jshop.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrantedAuthorityDao extends JpaRepository<GrantedAuthorityEntity, Integer>, IdentifiableRepository {
    @Override
    @Query("select p.id from granted_authority p")
    List<Integer> getSortedIds(Pageable pageable);
}
