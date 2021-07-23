package com.mongodb.example.repository;

import com.mongodb.example.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 自定义仓储类
 * @Author lktbz
 * @Date 2021/07/23
 */
@Repository
public interface EmployeeRepository  extends MongoRepository<Employee,Long> {
}
