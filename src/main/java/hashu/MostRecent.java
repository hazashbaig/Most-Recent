package hashu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MostRecent {
    private final int maxCapacity;
    private final List<String> items = new ArrayList<>();

    public MostRecent(int maxCapacity) {
        if (maxCapacity < 1) throw new InvalidCapacity();
        this.maxCapacity = maxCapacity;
    }

    public void add(String item) {
        items.remove(item);
        items.add(item);

        if (items.size() > maxCapacity) items.remove(0);
    }

    public List<String> retrieve() {
        List<String> copy = new ArrayList<>(items);
        Collections.reverse(copy);
        return copy;
    }
}
