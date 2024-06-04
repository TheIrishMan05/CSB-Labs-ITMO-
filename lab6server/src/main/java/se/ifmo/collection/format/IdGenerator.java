package se.ifmo.collection.format;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final Path PATH_OF_ID_STORAGE = Path.of("ID");
    private static IdGenerator instance = null;
    private AtomicLong currentId;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        if (instance == null) instance = new IdGenerator();
        return instance;
    }

    public synchronized long generateId() {
        long id = currentId.incrementAndGet();
        try {
            Files.write(PATH_OF_ID_STORAGE, String.valueOf(id).getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }
}
