package ru.netology.domain.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class IssueManager {
    private IssueRepository repository;

    IssueManager(IssueRepository repository) {
        this.repository = repository;
    }
    public void issueAdd(Issue items){
        repository.save (items);
    }

    public Collection<Issue> getAll(){
        return repository.findAll();
    }

    public boolean addAll(Collection<Issue> items){
        return repository.addAll(items);
    }

    public Collection<Issue> sortByNew() {
        Comparator byNew = Comparator.naturalOrder();
        Collection<Issue> issues = new ArrayList<>();
        issues.addAll(repository.findAll());
        ((ArrayList<Issue>) issues).sort(byNew);
        return issues;
    }

    public Collection<Issue> sortByOld() {
        Comparator byOld = Comparator.reverseOrder();
        Collection<Issue> issues = new ArrayList<>();
        issues.addAll(repository.findAll());
        ((ArrayList<Issue>) issues).sort(byOld);
        return issues;
    }

}
