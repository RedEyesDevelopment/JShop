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

    @Value("${cache.names.users}")
    private String cacheName;

    @Autowired
    @PostConstruct
    private void init(UserCache userCache, CacheManager cacheManager, UserDao userDao){
        this.self = userCache;
        this.jpaRepository = userDao;
        this.cache = cacheManager.getCache(cacheName);
    }
}
