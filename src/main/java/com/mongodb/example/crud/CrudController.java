package com.mongodb.example.crud;

import com.mongodb.example.domain.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;
/**
 * @Description  这是简单的crud 操作，还有复杂的条件查询，条件更新，条件过滤等会在后面补上
 * @Author lktbz
 * @Date 2021/07/23
 */
@RestController
public class CrudController {
    private static final Log log = LogFactory.getLog(CrudController.class);
    @Autowired
    private MongoTemplate mongoTemplatel;
    @GetMapping("/crud")
    public void crud(){
        Person p=new Person("joe",34);
        mongoTemplatel.insert(p);
        log.info("insert"+p);

        Person byId = mongoTemplatel.findById(p.getId(), Person.class);
        log.info("found"+byId);

        mongoTemplatel.updateFirst(query(where("name").is("joe")),update("age",38),Person.class);
        p=mongoTemplatel.findOne(query(where("name").is("joe")),Person.class);
        log.info("Updated: " + p);

        mongoTemplatel.remove(p);

    }
}
