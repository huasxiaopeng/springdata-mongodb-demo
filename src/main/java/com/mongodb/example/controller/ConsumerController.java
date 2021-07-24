package com.mongodb.example.controller;

import com.mongodb.example.domain.Person;
import com.mongodb.example.domain.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


/**
 * @Description springdata 官方例子学习
 * @Author lktbz
 * @Date 2021/07/24
 */
@RestController
public class ConsumerController {
  private static Logger logger=  LoggerFactory.getLogger(ConsumerController.class);
    @Autowired
    private MongoTemplate mongoTemplate;
    @GetMapping("/save")
    public void save(){
        Person p=new Person("Bob",33);
        Person insert = mongoTemplate.insert(p);
        logger.error("添加的对象为："+insert);
        //构建查询条件
        Criteria criteria = where("age").is(33);
        //构建请求
        Query query=new Query();
        query.addCriteria(criteria);
        //查询
        Person one = mongoTemplate.findOne(query, Person.class);
        logger.error("根据条件查询出的文档为："+one);
        /**
         * 官方demo
         *mongoTemplate.findOne(query(where("age").is(33)), Person.class);
         */
    }

    /**
     *  插入复杂对象
     */
    @GetMapping("/save/list")
    public void saveAll(){
        List<Person> ps=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            Person p=new Person();
            p.setName("Tom"+i);
            p.setAge(18+i);
            p.setId(String.valueOf(i));
            ps.add(p);
        }
        School school=new School();
        school.setName("北京一中");
        school.setAddress("北京市海淀区");
        school.setLevelType("市一级学校单位");
        school.setPersonList(ps);
        List<Person> pss=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            Person p=new Person();
            p.setName("jiji"+i);
            p.setAge(18+i);
            p.setId(String.valueOf(i));
            ps.add(p);
        }
        School schools=new School();
        schools.setName("北京十一中");
        schools.setAddress("北京市昌平区");
        schools.setLevelType("区级学校单位");
        schools.setPersonList(pss);
        List<School>asc=new ArrayList<>();
        asc.add(school);
        asc.add(schools);
        Collection<School> schools1 = mongoTemplate.insert(asc, School.class);
        logger.error("插入的值为："+schools1);
    }

    @GetMapping("/find/modify")
    public void findAndModify(){
        mongoTemplate.insert(new Person("Tom", 21));
        mongoTemplate.insert(new Person("Dick", 22));
        mongoTemplate.insert(new Person("Harry", 23));
        /**
         * 构建查询与修改demno
         */
        Query query = new Query(where("firstName").is("Harry"));
        Update update = new Update().inc("age", 1);

        Person oldValue = mongoTemplate.update(Person.class)
                .matching(query)
                .apply(update)
                .findAndModifyValue(); // return's old person object
        logger.error("更改后，之前的值为："+oldValue);

        /**
         *  查询新添加的值
          */
        List<Person> newValue = mongoTemplate.query(Person.class)
                .matching(query).all();
        System.out.println(newValue);
    }
}
