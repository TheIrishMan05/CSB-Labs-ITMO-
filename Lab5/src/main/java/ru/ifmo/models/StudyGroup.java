package ru.ifmo.models;

import ru.ifmo.utils.Element;

import java.time.LocalDateTime;
import java.util.Objects;

public class StudyGroup extends Element {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer expelledStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private Long shouldBeExpelled; //Значение поля должно быть больше 0, Поле может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null

    public StudyGroup(Long id, String name, Coordinates coordinates,
                      LocalDateTime creationDate, Integer studentsCount,
                      Integer expelledStudents, Long shouldBeExpelled,
                      Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.shouldBeExpelled = shouldBeExpelled;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }


    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public Integer getExpelledStudents() {
        return expelledStudents;
    }

    public Long getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(studentsCount, that.studentsCount) && Objects.equals(expelledStudents, that.expelledStudents) && Objects.equals(shouldBeExpelled, that.shouldBeExpelled) && semesterEnum == that.semesterEnum && Objects.equals(groupAdmin, that.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentsCount, expelledStudents, shouldBeExpelled, semesterEnum, groupAdmin);
    }

    @Override
    public String toString() {
        return id +
                "," + name + '\'' +
                "," + coordinates +
                "," + creationDate +
                "," + studentsCount +
                "," + expelledStudents +
                "," + shouldBeExpelled +
                "," + semesterEnum +
                "," + groupAdmin + "\n";
    }


    @Override
    public int compareTo(Element element) {
        return (int) (this.getID() - element.getID());
    }


    @Override
    public Long getID() {
        return id;
    }
}