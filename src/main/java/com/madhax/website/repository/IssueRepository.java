package com.madhax.website.repository;

import com.madhax.website.domain.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue, Long> {

}
