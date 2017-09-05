package jshop.repositories;

import jshop.model.GrantedAuthorityEntity;
import jshop.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrantedAuthorityDao extends JpaRepository<GrantedAuthorityEntity, Integer>, IdentifiableRepository {
}
