package ru.netology.manager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

    private Issue issue1 = new Issue(1, true, "ZhukovaAnna", "Marc Philip", 1, new HashSet<String>(Arrays.asList("label", "label2", "label3")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Stefan Belchold", "Marc Philip")));
    private Issue issue2 = new Issue(2, false, "Stefan Belchold", "Kevin Cooney", 2, new HashSet<String>(Arrays.asList("label4", "label5", "label6")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Kent Beck", "David Staff")));
    private Issue issue3 = new Issue(3, true, "David Staff", "Kent Beck", 3, new HashSet<String>(Arrays.asList("label3", "label2", "label1")), new HashSet<String>(Arrays.asList("David Staff", "Sam Brannon", "Cristian Stain")));

    @Nested
    public class Empty {

        @Test
        void shouldReturnEmptyIfNothingToSortByNew() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.sortByNew();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNothingToSortByOld() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.sortByOld();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAuthor() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.findByAuthor("ZhukovaAnna");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByLabel() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.findByLabel(new HashSet<String>(Collections.singletonList("label1")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAssignee() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.findByAssignee(new HashSet<String>(Collections.singletonList("ZhukovaAnna")));
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class OneItems {

        @Test
        void shouldReturnOneToSortByNew() {
            manager.issueAdd(issue1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.sortByNew();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnOneToSortByOld() {
            manager.issueAdd(issue2);
            List<Issue> expected = new ArrayList<>(List.of(issue2));
            List<Issue> actual = manager.sortByOld();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAuthor() {
            manager.issueAdd(issue3);
            List<Issue> expected = new ArrayList<>(List.of(issue3));
            List<Issue> actual = manager.findByAuthor("David Staff");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoAuthor() {
            manager.issueAdd(issue2);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.findByAuthor("Marc Philip");
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByLabel() {
            manager.issueAdd(issue3);
            List<Issue> expected = new ArrayList<>(List.of(issue3));
            List<Issue> actual = manager.findByLabel(new HashSet<String>(Collections.singletonList("label2")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoLabel() {
            manager.issueAdd(issue2);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.findByLabel(new HashSet<String>(Collections.singletonList("label100")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAssignee() {
            manager.issueAdd(issue1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.findByAssignee(new HashSet<String>(Collections.singletonList("ZhukovaAnna")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAssignee() {
            manager.issueAdd(issue1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.findByAssignee(new HashSet<String>(Collections.singletonList("Marc Philip")));
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MoreItems {

        @Test
        void shouldAdd() {
            manager.issueAdd(issue2);
            manager.issueAdd(issue3);
            List<Issue> expected = new ArrayList<>(List.of(issue2, issue3));
            List<Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNew() {
            manager.addAll(List.of(issue1, issue2, issue3));
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue3));
            List<Issue> actual = manager.sortByNew();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOld() {
            manager.addAll(List.of(issue1, issue2, issue3));
            List<Issue> expected = new ArrayList<>(List.of(issue3, issue2, issue1));
            List<Issue> actual = manager.sortByOld();
            assertEquals(expected, actual);
        }
    }
}