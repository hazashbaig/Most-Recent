package hashu.utilities;

import java.util.UUID;

public class RandomGenerators {
    public static String randomString() {
        return UUID.randomUUID().toString();
    }
}
