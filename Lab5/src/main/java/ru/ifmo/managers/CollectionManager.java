package ru.ifmo.managers;

import ru.ifmo.models.Semester;
import ru.ifmo.models.StudyGroup;
import ru.ifmo.utils.GroupsComparator;

import java.time.LocalDateTime;
import java.util.NavigableSet;
import java.util.TreeSet;

public class CollectionManager {
    private NavigableSet<StudyGroup> collection = new TreeSet<StudyGroup>(new GroupsComparator());
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
        loadCollection();
    }

    private void loadCollection() {
        collection = (TreeSet<StudyGroup>) fileManager.readCollectionFromFile();
        lastSaveTime = LocalDateTime.now();
    }

    public NavigableSet<StudyGroup> getCollection() {
        return collection;
    }

    public void saveCollection() {
        fileManager.writeCollectionToFile(collection);
        lastSaveTime = LocalDateTime.now();
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public String collectionType() {
        return collection.getClass().getName();
    }

    public int collectionSize() {
        return collection.size();
    }

    public StudyGroup getById(Long id) {
        for (StudyGroup studyGroup : collection) {
            if (studyGroup.getID().equals(id)) return studyGroup;
        }
        return null;
    }

    public StudyGroup getFirst() {
        if (collection.isEmpty()) return null;
        return collection.first();
    }

    public StudyGroup getLast() {
        if (collection.isEmpty()) return null;
        return collection.last();
    }

    public StudyGroup getByValue(StudyGroup studyGroupToFind) {
        for (StudyGroup studyGroup : collection) {
            if (studyGroup.equals(studyGroupToFind)) return studyGroup;
        }
        return null;
    }

    public Integer getCountOfStudyGroupsLS(Semester semInput) throws IllegalArgumentException {
        Integer countOfStudyGroups = 0;
        for (StudyGroup studyGroup : collection) {
            if (studyGroup.getSemesterEnum().ordinal() < semInput.ordinal()) {
                countOfStudyGroups += 1;
            }
        }
        return countOfStudyGroups;
    }

    public void addToCollecton(StudyGroup studyGroup) {
        collection.add(studyGroup);
    }

    public void removeFromCollection(StudyGroup studyGroup) {
        collection.remove(studyGroup);
    }

    public void removeGreater(StudyGroup studyGroupToCompare) {
        collection.removeIf(studyGroup -> studyGroup.compareTo(studyGroupToCompare) > 0);
    }

    public void removeLower(StudyGroup studyGroupToCompare) {
        collection.removeIf(studyGroup -> studyGroup.compareTo(studyGroupToCompare) < 0);
    }

    public void printAscending() {
        for (StudyGroup studyGroup : collection) {
            System.out.println(studyGroup);
        }
    }

    public void clearCollection() {
        collection.clear();
    }

    public long generateID() {
        if (collection.isEmpty()) return 1;
        return collection.last().getID();
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Collection is empty!";

        String info = "";
        for (StudyGroup studyGroup : collection) {
            info += studyGroup;
            if (studyGroup != collection.last()) info += ",\n";

        }
        return info;
    }
}
