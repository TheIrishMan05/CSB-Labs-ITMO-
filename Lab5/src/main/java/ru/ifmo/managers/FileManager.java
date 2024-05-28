package ru.ifmo.managers;

import com.opencsv.CSVParser;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import ru.ifmo.models.*;
import ru.ifmo.utils.GroupsComparator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Manager for interaction with files
 */
public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts collection to string in CSV format
     *
     * @param studyGroups (collection)
     * @return Collection in string interpretation
     */
    private String collectionToCsvString(Collection<StudyGroup> studyGroups) {
        try {
            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter, ICSVWriter.DEFAULT_SEPARATOR,
                    ICSVWriter.NO_QUOTE_CHARACTER, ICSVWriter.NO_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END);
            CSVParser parser = new CSVParser();
            for (StudyGroup studyGroup : studyGroups) {
                String[] strings = parser.parseLine(studyGroup.toString());
                csvWriter.writeNext(strings);
            }
            return stringWriter.toString();
        } catch (Exception e) {
            System.out.println("Error of serialization!");
            return null;
        }
    }

    /**
     * Writes collection to file
     *
     * @param collection (collection)
     */
    public void writeCollectionToFile(Collection<StudyGroup> collection) {
        try (
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                Writer outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
        ) {
            var csv = collectionToCsvString(collection);
            assert csv != null;
            outputStreamWriter.write(csv);
            outputStreamWriter.flush();
            System.out.println("Collection has been written successfully!");
        } catch (IOException ioe) {
            System.out.println("File can't be opened!");
        } catch (NullPointerException exception) {
            System.out.println("Collection can't be null");
        }
    }

    private StudyGroup parseLine(String line) {
        try {

            String[] elementData = line.split(",");
            StudyGroup element = new StudyGroup(Long.parseLong(elementData[0]), elementData[1],
                    new Coordinates(Long.parseLong(elementData[2]), Float.parseFloat(elementData[3])),
                    LocalDateTime.parse(elementData[4]),
                    Integer.parseInt(elementData[5]), Integer.parseInt(elementData[6]),
                    Long.parseLong(elementData[7]), Semester.valueOf(elementData[8]),
                    new Person(elementData[9], LocalDate.parse(elementData[10]),
                            Color.valueOf(elementData[11]), new Location(Long.parseLong(elementData[12]),
                            Integer.parseInt(elementData[13]), elementData[14])));
            if (element.validate()){
                return element;
            } else {
                System.out.println("Some data in element " + element.getID() + " is invalid!");
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }


    public SortedSet<StudyGroup> readCollectionFromFile() {
        if (Files.notExists(Path.of(filePath))) {
            System.out.println("File doesn't exists");
            return new TreeSet<>();
        }

        if (!Files.isReadable(Path.of(filePath))) {
            System.out.println("Can't read file");
            return new TreeSet<>();
        }

        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Command line argument isn't found!");
            return new TreeSet<>();
        }


        TreeSet<StudyGroup> collection = new TreeSet<>(new GroupsComparator());
        try (FileInputStream fileInput = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInput, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(inputStreamReader)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isBlank()) continue;

                StudyGroup studyGroup = parseLine(line);

                if (studyGroup == null) {
                    System.out.printf("Element %s is invalid%n", line);
                    continue;
                }

                collection.add(studyGroup);
            }

            System.out.println("Collection has been loaded successfully!");

            return collection;
        } catch (IllegalStateException | IOException exception) {
            System.out.println("Unpredictable error! " + exception.getMessage());
            return new TreeSet<>();
        }
    }
}
