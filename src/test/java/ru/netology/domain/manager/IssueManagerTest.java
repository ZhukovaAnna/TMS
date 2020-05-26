package ru.netology.domain.manager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    IssueRepository repository=new IssueRepository();
    IssueManager manager=new IssueManager(repository);

    Issue issue1 = new Issue(1, true, "ZhukovaAnna","Marc Philip", 1, new HashSet<String>(Arrays.asList("label", "label2", "label3")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Stefan Belchold", "Marc Philip")));
    Issue issue2 = new Issue(2, false, "Stefan Belchold","Kevin Cooney", 2, new HashSet<String>(Arrays.asList("label4", "label5", "label6")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Kent Beck", "David Staff")));
    Issue issue3 = new Issue(3, true, "David Staff","Kent Beck", 3, new HashSet<String>(Arrays.asList("label3", "label2", "label1")), new HashSet<String>(Arrays.asList("David Staff", "Sam Brannon", "Cristian Stain")));

@Nested
    public class Empty{

    @Test
    void shouldReturnEmptyIfNothingToSortByNew() {
        manager.addAll(List.of());
        Collection<Issue> expected = new ArrayList<>();
        Collection <Issue> actual = manager.sortByNew();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNothingToSortByOld() {
        manager.addAll(List.of());
        Collection <Issue> expected = new ArrayList<>();
        Collection <Issue> actual = manager.sortByOld();
        assertEquals(expected, actual);
    }
}

@Nested
    public class OneItems{

    @Test
    void shouldReturnOneToSortByNew() {
        manager.addAll(List.of(issue1));
        Collection<Issue> expected=new ArrayList<>();
        expected.add(issue1);
        Collection<Issue> actual = manager.sortByNew();
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnOneToSortByOld(){
        manager.addAll(List.of(issue2));
        Collection<Issue> expected=new ArrayList<>();
        expected.add(issue2);
        Collection<Issue> actual = manager.sortByOld();
        assertEquals(expected,actual);
    }
}

@Nested
    public class MoreItems{

    @Test
    void shouldAdd(){
        manager.issueAdd(issue2);
        manager.issueAdd(issue3);
        Collection <Issue> expected = new ArrayList<>();
        expected.addAll(List.of(issue2,issue3));
        Collection <Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByNew() {
        manager.addAll(List.of(issue1,issue2,issue3));
        Collection <Issue> expected = new ArrayList<>();
        expected.addAll(List.of(issue1,issue2,issue3));
        Collection <Issue> actual = manager.sortByNew();
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByOld() {
        manager.addAll(List.of(issue1,issue2,issue3));
        Collection <Issue> expected = new ArrayList<>();
        expected.addAll(List.of(issue3,issue2,issue1));
        Collection <Issue> actual = manager.sortByOld();
        assertEquals(expected, actual);
    }
}
}