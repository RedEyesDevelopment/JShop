package jshop.services;

import jshop.model.UserEntity;
import jshop.storage.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl extends AbstractService<UserEntity, Integer>{
    AbstractService self;

    @Autowired
    public UserServiceImpl(UserCache userCache) {
        this.cache = userCache;
    }

    @PostConstruct
    private void init(){
        this.self = this;
    }
}
