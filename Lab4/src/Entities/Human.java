package Entities;


import Enums.ActiveStatus;
import Enums.ObjectStatus;
import Enums.Status;
import Exceptions.NoMatchesException;
import Interfaces.*;

import java.util.Objects;

public abstract class Human implements LightUpAble,
        MoveAble, DescendAble, EnterLocationAble {
    private final String name;
    private ILocation location;
    private Status status;
    private ActiveStatus activeStatus;

    Human(String name, ILocation location, Status status, ActiveStatus activeStatus) {
        this.name = name;
        this.location = location;
        this.status = status;
        this.activeStatus = activeStatus;
    }

    public void setActiveStatus(ActiveStatus activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getName() {
        return name;
    }

    public ILocation getLocation() {
        return location;
    }

    public void setLocation(ILocation location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean LightUp() {
        class Match {
            private ObjectStatus objectStatus;
            private int amountOfMatches = 10;

            Match(ObjectStatus objectStatus) {
                this.objectStatus = objectStatus;
            }

            public ObjectStatus getObjectStatus() {
                return objectStatus;
            }

            public void setObjectStatus(ObjectStatus objectStatus) {
                this.objectStatus = objectStatus;
            }

            public int getAmountOfMatches() {
                return amountOfMatches;
            }

            public void setAmountOfMatches(int amountOfMatches) throws NoMatchesException {
                if (this.amountOfMatches <= 0) {
                    throw new NoMatchesException("Спички закончились");
                } else {
                    this.amountOfMatches = amountOfMatches;
                }
            }

            public boolean isBurning() {
                if (Math.random() >= 0.5 && this.getObjectStatus().equals(ObjectStatus.LIVELONG)) {
                    try {
                        this.setAmountOfMatches(this.getAmountOfMatches() - 1);
                        System.out.println("Спичка загорелась.");
                        return true;
                    } catch (NoMatchesException nme) {
                        System.out.println("\u001B[33m" + "Спички закончились" + "\u001B[0m");
                        return false;
                    }
                } else {
                    try {
                        this.setObjectStatus(ObjectStatus.BROKEN);
                        this.setAmountOfMatches(this.getAmountOfMatches() - 1);
                        this.setObjectStatus(ObjectStatus.LIVELONG);
                        System.out.println("Спичка сломалась.");
                        return false;
                    } catch (NoMatchesException nme) {
                        System.out.println("Спички закончились");
                        return false;
                    }
                }
            }
        }
        Match match = new Match(ObjectStatus.LIVELONG);
        return match.isBurning();
    }


    @Override
    public void moveToHuman(Human human) {
        if (human.getLocation().equals(this.getLocation())) {
            this.setStatus(Status.ACTIVE);
            System.out.println("Через некоторое время, " + this.getName() + " дошёл до " + human.getName());
            this.setStatus(Status.STANDING);
        }
    }

    @Override
    public void enterLocation(ILocation location) {
        if (this.getStatus().equals(Status.ACTIVE)) {
            this.setLocation(location);
        }

    }


    @Override
    public String toString() {
        return "Человек по имени " + this.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, status, activeStatus);
    }

    @Override
    public void descend(Human human) {
        human.setStatus(Status.SITTING);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Human human = (Human) o;
        return Objects.equals(name, human.name)
                && Objects.equals(location, human.location)
                && Objects.equals(status, human.status)
                && Objects.equals(activeStatus, human.activeStatus);
    }


}
