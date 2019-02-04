package com.madhax.website.repository;

import com.madhax.website.domain.Bug;
import org.springframework.data.repository.CrudRepository;

public interface BugRepository extends CrudRepository<Bug, Long> {

}
