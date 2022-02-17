package com.workshop.usermanagement.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workshop.usermanagement.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}