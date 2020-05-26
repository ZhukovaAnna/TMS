package ru.netology.domain.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    IssueRepository repository = new IssueRepository();

    Issue issue1 = new Issue(1, true, "ZhukovaAnna","Marc Philip", 1, new HashSet<String>(Arrays.asList("label", "label2", "label3")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Stefan Belchold", "Marc Philip")));
    Issue issue2 = new Issue(2, false, "Stefan Belchold","Kevin Cooney", 2, new HashSet<String>(Arrays.asList("label4", "label5", "label6")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Kent Beck", "David Staff")));
    Issue issue3 = new Issue(3, true, "David Staff","Kent Beck", 3, new HashSet<String>(Arrays.asList("label3", "label2", "label1")), new HashSet<String>(Arrays.asList("David Staff", "Sam Brannon", "Cristian Stain")));
    Issue issue4 = new Issue(4, false, "ZhukovaAnna","Kevin Cooney", 4, new HashSet<String>(Arrays.asList("labe6", "label5", "label4")), new HashSet<String>(Arrays.asList("ZhukovaAnna", "Sam Brannon", "Cristian Stain")));
    @Nested
    public class EmptyRepository {
        @Test
        void shouldReturnEmptyWhenFindByOpen() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByClose() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAuthor() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAuthor("ZhukovaAnna");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByLabel() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label1")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAssignee() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAssignee( new HashSet<String>(Arrays.asList("David Staff")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfClose() {
            repository.addAll(List.of());
            boolean expected = false;
            boolean actual = repository.closeById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfOpen() {
            repository.addAll(List.of());
            boolean expected = false;
            boolean actual = repository.openById(2);
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {

        @Test
        void shouldFindOpenIfOpen() {
            repository.addAll(List.of(issue1));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1));
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoOpen() {
            repository.addAll(List.of(issue2));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfClosed() {
            repository.addAll(List.of(issue2));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue2));
            Collection <Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoClosed() {
            repository.addAll(List.of(issue3));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAuthor() {
            repository.addAll(List.of(issue2));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue2));
            Collection <Issue> actual = repository.findByAuthor("Stefan Belchold");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoAuthor() {
            repository.addAll(List.of(issue3));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAuthor("Sam");
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByLabel() {
            repository.addAll(List.of(issue4));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue4));
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label4")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoLabel() {
            repository.addAll(List.of(issue1));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label25")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAssignee() {
            repository.addAll(List.of(issue1));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1));
            Collection <Issue> actual = repository.findByAssignee( new HashSet<String>(Arrays.asList("ZhukovaAnna")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAssignee() {
            repository.addAll(List.of(issue2));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAssignee( new HashSet<String>(Arrays.asList("Kevin Cooney")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldClose() {
            repository.addAll(List.of(issue1));
            boolean expected = !issue1.isOpen();
            boolean actual = repository.closeById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenClose() {
            repository.addAll(List.of(issue1));
            boolean expected = false;
            boolean actual = repository.closeById(3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfNothingToClose() {
            repository.addAll(List.of(issue4));
            boolean expected = false;
            boolean actual = repository.closeById(4);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpen() {
            repository.addAll(List.of(issue2));
            boolean expected = issue1.isOpen();
            boolean actual = repository.openById(2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenOpen() {
            repository.addAll(List.of(issue2));
            boolean expected = true;
            boolean actual = repository.openById(2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfNothingToOpen() {
            repository.addAll(List.of(issue3));
            boolean expected = false;
            boolean actual = repository.openById(3);
            assertEquals(expected, actual);
        }

    }

    @Nested
    public class MultipleItems {

        @Test
        void shouldFindOpenIfOpen() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            Collection<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1, issue3));
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoOpen() {
            repository.addAll(List.of(issue2,issue4));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfClosed() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue2,issue4));
            Collection <Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoClosed() {
            repository.addAll(List.of(issue1,issue3));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAuthor() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1,issue4));
            Collection <Issue> actual = repository.findByAuthor("ZhukovaAnna");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoAuthor() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAuthor("Sam");
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByLabel() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue3));
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label1")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoLabel() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label15")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAssignee() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue3,issue4));
            Collection <Issue> actual = repository.findByAssignee( new HashSet<String>(Arrays.asList("Sam Brannon")));
            assertEquals(expected, actual);
        }
        
        @Test
        void shouldClose() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            boolean expected = !issue1.isOpen();
            boolean actual = repository.closeById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenClose() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            boolean expected = false;
            boolean actual = repository.closeById(3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfNothingToClose() {
            repository.addAll(List.of(issue2,issue4));
            boolean expected = false;
            boolean actual = repository.closeById(4);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpen() {
            repository.addAll(List.of(issue2,issue4));
            boolean expected = issue1.isOpen();
            boolean actual = repository.openById(4);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenOpen() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            boolean expected = false;
            boolean actual = repository.openById(5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfNothingToOpen() {
            repository.addAll(List.of(issue1,issue3));
            boolean expected = false;
            boolean actual = repository.openById(1);
            assertEquals(expected, actual);
        }
    }
}


