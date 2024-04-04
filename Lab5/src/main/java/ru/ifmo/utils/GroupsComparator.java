package ru.ifmo.utils;

import java.util.Comparator;

public class GroupsComparator implements Comparator<Element> {
    @Override
    public int compare(Element e1, Element e2) {
        return e1.compareTo(e2);
    }
}
