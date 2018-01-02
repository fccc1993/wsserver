package com.websocket.server.service;

import com.mongodb.DB;
import com.websocket.server.bean.LiveInfoCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by fccc on 2017/12/29.
 */
@Service
public class LiveInfoCountServiceImpl implements LiveInfoCountService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void test() {
        Set<String> colls = this.mongoTemplate.getCollectionNames();
        for (String coll : colls) {
            System.out.println("CollectionName=" + coll);
        }
        DB db = this.mongoTemplate.getDb();
        System.out.println("db=" + db.toString());
    }

    public void createCollection() {
        if (!this.mongoTemplate.collectionExists(LiveInfoCount.class)) {
            this.mongoTemplate.createCollection(LiveInfoCount.class);
        }
    }

    public List<LiveInfoCount> findList(int skip, int limit) {
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Direction.ASC, "id")));
        query.skip(skip).limit(limit);
        return this.mongoTemplate.find(query,LiveInfoCount.class);
    }

    public List<LiveInfoCount> findListByUname(String uname) {
        Query query = new Query();
        query.addCriteria(new Criteria("uname").is(uname));
        return this.mongoTemplate.find(query, LiveInfoCount.class);
    }

    public LiveInfoCount findOne(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return this.mongoTemplate.findOne(query, LiveInfoCount.class);
    }

    public void insert(LiveInfoCount entity) {
        this.mongoTemplate.insert(entity);
    }

//    public void update(LiveInfoCount entity) {
//        Query query = new Query();
//        query.addCriteria(new Criteria("_id").is(entity.getId()));
//        Update update = new Update();
//        update.set("uname", entity.getUname());
//        update.set("uid", entity.getUid());
//        this.mongoTemplate.updateFirst(query, update, LiveInfoCount.class);
//    }
}
