package com.workshop.usermanagement.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workshop.usermanagement.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from user where id = ?1", nativeQuery = true)
	void deleteById(Integer id);

}