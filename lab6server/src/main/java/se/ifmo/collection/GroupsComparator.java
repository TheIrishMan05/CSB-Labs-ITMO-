package se.ifmo.collection;

import se.ifmo.collection.models.StudyGroup;

import java.util.Comparator;

public class GroupsComparator implements Comparator<StudyGroup> {
    @Override
    public int compare(StudyGroup sg1, StudyGroup sg2) {
        return sg1.compareTo(sg2);
    }


}
