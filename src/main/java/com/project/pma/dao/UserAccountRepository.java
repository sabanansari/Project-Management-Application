package com.project.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

	void save(UserAccount user);

}
