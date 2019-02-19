package com.madhax.website.service;

import com.madhax.website.domain.Issue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by James Cathcart on 2/18/2019
 */
@RunWith(SpringRunner.class)
public class IssueServiceTest {

    @Mock
    IssueService issueService;

    @Test
    public void getByIdTest() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Issue Title");

        when(issueService.getById(anyLong())).thenReturn(issue);
        Issue returnedIssue = issueService.getById(1L);

        assertEquals("Issue Title", returnedIssue.getTitle());
        verify(issueService, times(1)).getById(anyLong());
    }

    @Test
    public void getAllTest() {
        Set<Issue> issues = new HashSet<>();
        Issue issue1 = new Issue();
        issue1.setId(1L);
        Issue issue2 = new Issue();
        issue2.setId(2L);

        issues.add(issue1);
        issues.add(issue2);

        when(issueService.getAll()).thenReturn(issues);
        Set<Issue> returnedIssues = issueService.getAll();

        assertEquals(2, returnedIssues.size());
        verify(issueService, times(1)).getAll();
    }

    @Test
    public void saveTest() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Issue Title");

        when(issueService.save(any())).thenReturn(issue);

        Issue returnedIssue = issueService.save(issue);

        assertEquals("Issue Title", returnedIssue.getTitle());
        verify(issueService, times(1)).save(any());
    }

    @Test
    public void deleteTest() {
        Issue issue = new Issue();
        issueService.delete(issue);
        verify(issueService, times(1)).delete(any());
    }

    @Test
    public void deleteByIdTest() {
        Issue issue = new Issue();
        issue.setId(1L);
        issueService.deleteById(1L);
        verify(issueService, times(1)).deleteById(anyLong());
    }
}