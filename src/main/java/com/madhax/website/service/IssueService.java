package com.madhax.website.service;

import com.madhax.website.domain.Issue;
import com.madhax.website.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue getById(Long id) {
        return issueRepository.findById(id).orElse(null);
    }

    public Set<Issue>getAll() {
        Set<Issue> issues = new HashSet<>();
        issueRepository.findAll().forEach(issues::add);
        return issues;
    }

    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    public void delete(Issue issue) {
        issueRepository.delete(issue);
    }

    public void deleteById(Long id) {
        issueRepository.deleteById(id);
    }
}
