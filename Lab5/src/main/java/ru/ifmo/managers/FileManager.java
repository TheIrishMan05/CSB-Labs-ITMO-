package ru.ifmo.managers;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.ifmo.models.StudyGroup;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void writeCollectionToFile(Collection<StudyGroup> collection) {
        try (
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                Writer outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                CSVWriter csvWriter = new CSVWriter(outputStreamWriter, ICSVWriter.DEFAULT_SEPARATOR,
                        ICSVWriter.NO_QUOTE_CHARACTER, ICSVWriter.NO_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END)
        ) {
            CSVParser parser = new CSVParser();
            for (StudyGroup studyGroup : collection) {
                String[] csvObj = parser.parseLine(studyGroup.toString());
                csvWriter.writeNext(csvObj);
            }
            System.out.println("Коллекция успешно записана в файл!");
        } catch (IOException ioe) {
            System.out.println("Файл не может быть открыт!");
        }
    }

    public Collection<StudyGroup> readCollectionFromFile() {
        if (filePath != null && !filePath.isEmpty()) {
            try (FileInputStream fileInput = new FileInputStream(filePath);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInput, StandardCharsets.UTF_8);
                 CSVReader csvReader = new CSVReader(inputStreamReader)) {
                List<StudyGroup> beans = new CsvToBeanBuilder(csvReader)
                        .withType(StudyGroup.class)
                        .build()
                        .parse();
                TreeSet<StudyGroup> collection = new TreeSet<>(beans);
                System.out.println("Collection has been loaded successfully!");
                return collection;
            } catch (FileNotFoundException exception) {
                System.out.println("File haven't been found!");
            } catch (NoSuchElementException exception) {
                System.out.println("File is empty!");
            } catch (IllegalStateException | IOException exception) {
                System.out.println("");
                System.exit(0);
            }
        } else {
            System.out.println("Command line argument isn't found!");
        }
        return new TreeSet<>();
    }
}
