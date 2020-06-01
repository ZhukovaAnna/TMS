package ru.netology.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();

    private Issue issue1 = new Issue(1, true, "ZhukovaAnna", "Marc Philip", 1, new HashSet<String>(Arrays.asList("label", "label2", "label3")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Stefan Belchold", "Marc Philip")));
    private Issue issue2 = new Issue(2, false, "Stefan Belchold", "Kevin Cooney", 2, new HashSet<String>(Arrays.asList("label4", "label5", "label6")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Kent Beck", "David Staff")));
    private Issue issue3 = new Issue(3, true, "David Staff", "Kent Beck", 3, new HashSet<String>(Arrays.asList("label3", "label2", "label1")), new HashSet<String>(Arrays.asList("David Staff", "Sam Brannon", "Cristian Stain")));
    private Issue issue4 = new Issue(4, false, "ZhukovaAnna", "Kevin Cooney", 4, new HashSet<String>(Arrays.asList("labe6", "label5", "label4")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Sam Brannon", "Cristian Stain")));

    @Nested
    public class EmptyRepository {
        @Test
        void shouldReturnEmptyWhenFindByOpen() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByClose() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfClose() {
            repository.closeById(1);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfOpen() {
            repository.openById(2);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {

        @Test
        void shouldFindOpenIfOpen() {
            repository.save(issue1);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoOpen() {
            repository.save(issue2);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfClosed() {
            repository.save(issue2);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoClosed() {
            repository.save(issue3);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldClose() {
            repository.save(issue1);
            repository.closeById(1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenClose() {
            repository.save(issue1);
            repository.closeById(2);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfNothingToClose() {
            repository.save(issue4);
            repository.closeById(2);
            List<Issue> expected = new ArrayList<>(List.of(issue4));
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpen() {
            repository.addAll(List.of(issue2));
            repository.openById(2);
            List<Issue> expected = new ArrayList<>(List.of(issue2));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenOpen() {
            repository.save(issue2);
            List<Issue> expected = new ArrayList<>(List.of(issue2));
            List<Issue> actual = repository.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfNothingToOpen() {
            repository.save(issue3);
            List<Issue> expected = new ArrayList<>(List.of(issue3));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

    }

    @Nested
    public class MultipleItems {

        @Test
        void shouldFindOpenIfOpen() {
            repository.addAll(List.of(issue1, issue2, issue3, issue4));
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoOpen() {
            repository.addAll(List.of(issue2, issue4));
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfClosed() {
            repository.addAll(List.of(issue1, issue2, issue3, issue4));
            List<Issue> expected = new ArrayList<>(List.of(issue2, issue4));
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoClosed() {
            repository.addAll(List.of(issue1, issue3));
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }
    }
}



