package jshop.storage;

import jshop.model.UserEntity;
import jshop.repositories.UserDao;
import lombok.Getter;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserCache extends AbstractCacheStorage<UserEntity, Integer> {
    private AbstractCacheStorage self;

    @Autowired
    private CacheManager cacheManager;

    @Value("${cache.names.users}")
    private String userCacheName;

    @Autowired
    public UserCache(UserDao userDao) {
        this.jpaRepository = userDao;
    }

    @PostConstruct
    private void init(){
        this.self = this;
        Cache ache = cacheManager.getCache(userCacheName);
        this.cache = ache;
    }
}
