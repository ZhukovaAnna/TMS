package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    IssueManager(IssueRepository repository) {
        this.repository = repository;
    }
    public void issueAdd(Issue items){
        repository.save (items);
    }

    public List<Issue> getAll(){
        return (List<Issue>) repository.findAll();
    }

    public boolean addAll(List<Issue> items){
        return repository.addAll(items);
    }

    public List<Issue> sortByNew() {
        Comparator byNew = Comparator.naturalOrder();
        List<Issue> issues = (List<Issue>) repository.findAll();
        issues.sort(byNew);
        return issues;
    }

    public List<Issue> sortByOld() {
        Comparator byOld = Comparator.reverseOrder();
        List<Issue> issues = (List<Issue>) repository.findAll();
        issues.sort(byOld);
        return issues;
    }

    public List<Issue> findByAuthor(String author) {
        Predicate<String> byAuthor = t -> t.equals(author);
        List<Issue> issues = new ArrayList<>();
        for (Issue item : repository.findAll()){
            if (byAuthor.test(item.getAuthor())) issues.add(item);
        }
        return issues;
    }

    public List<Issue> findByLabel(Set<String> label) {
        Predicate<Set<String>> byLabel = t -> t.containsAll(label);
        List<Issue> issues = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (byLabel.test(item.getIssueLabels())) {
                issues.add(item);
            }
        }
        return issues;
    }

    public List<Issue> findByAssignee(Set<String> assignee) {
        Predicate<Set<String>> byLabel = t -> t.containsAll(assignee);
        List<Issue> issues = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (byLabel.test(item.getIssueAssignee())) {
                issues.add(item);
            }
        }
        return issues;
    }

}
