package com.project.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
