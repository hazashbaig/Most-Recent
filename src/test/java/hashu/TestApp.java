package hashu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hashu.utilities.RandomGenerators.randomString;
import static org.junit.jupiter.api.Assertions.*;

public class TestApp {
    private final int validMaxCapacity = 10;

    @Test
    void initially_number_of_items_must_be_zero() {
        MostRecent mostRecent = new MostRecent(validMaxCapacity);

        assertEquals(0, mostRecent.retrieve().size());
    }

    @Test
    void if_one_item_is_added_then_number_of_items_should_be_one() {
        MostRecent mostRecent = new MostRecent(validMaxCapacity);
        mostRecent.add(randomString());

        assertEquals(1, mostRecent.retrieve().size());
    }

    @Test
    void there_cannot_be_more_items_than_max_capacity() {
        MostRecent mostRecent = new MostRecent(3);
        mostRecent.add(randomString());
        mostRecent.add(randomString());
        mostRecent.add(randomString());
        mostRecent.add(randomString());

        assertEquals(3, mostRecent.retrieve().size());
    }

    @Test
    void max_capacity_cannot_be_less_than_one() {
        assertDoesNotThrow(() -> new MostRecent(1));
        assertThrows(InvalidCapacity.class, () -> new MostRecent(0));
        assertThrows(InvalidCapacity.class, () -> new MostRecent(-1));
    }

    @Test
    void can_retrieve_previously_added_item() {
        MostRecent mostRecent = new MostRecent(validMaxCapacity);

        String item = randomString();
        mostRecent.add(item);

        assertTrue(mostRecent.retrieve().contains(item));
    }

    @Test
    void can_retrieve_all_previously_added_items() {
        MostRecent mostRecent = new MostRecent(validMaxCapacity);

        List<String> previouslyAddedItems = new ArrayList<>();
        for (int i = 0; i < validMaxCapacity; i++) {
            String item = randomString();
            mostRecent.add(item);
            previouslyAddedItems.add(item);
        }

        assertTrue(mostRecent.retrieve().containsAll(previouslyAddedItems));
    }

    @Test
    void cannot_add_more_than_max_capacity() {
        MostRecent mostRecent = new MostRecent(2);
        String item1 = randomString();
        String item2 = randomString();
        String item3 = randomString();
        mostRecent.add(item1);
        mostRecent.add(item2);
        mostRecent.add(item3);

        assertTrue(mostRecent.retrieve().containsAll(Arrays.asList(item2, item3)));
        assertFalse(mostRecent.retrieve().contains(item1));
    }

    @Test
    void if_previously_added_item_is_added_again_then_number_of_items_should_not_change_and_the_added_item_should_become_most_frequent() {
        MostRecent mostRecent = new MostRecent(validMaxCapacity);
        String item1 = randomString();
        String item2 = randomString();

        mostRecent.add(item1);
        mostRecent.add(item2);
        mostRecent.add(item1);

        assertEquals(2, mostRecent.retrieve().size());
        assertEquals(Arrays.asList(item1, item2), mostRecent.retrieve());
    }

    @Test
    void when_items_are_retrieved_order_should_be_from_most_recent_to_least_recent() {
        MostRecent mostRecent = new MostRecent(validMaxCapacity);
        String item1 = randomString();
        String item2 = randomString();
        String item3 = randomString();

        mostRecent.add(item1);
        mostRecent.add(item2);
        mostRecent.add(item3);

        assertEquals(Arrays.asList(item3, item2, item1), mostRecent.retrieve());
    }

    @Test
    void if_previously_added_item_is_added_again_and_max_capacity_is_reached_then_added_item_should_be_made_most_recent_instead_of_adding_again() {
        MostRecent mostRecent = new MostRecent(2);
        String item1 = randomString();
        String item2 = randomString();

        mostRecent.add(item1);
        mostRecent.add(item2);
        mostRecent.add(item1);

        assertEquals(Arrays.asList(item1, item2), mostRecent.retrieve());
    }
}
