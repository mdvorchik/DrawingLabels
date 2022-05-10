package org.mipt.drawer;

import java.util.Objects;

public class Clause {
    int firstNodeId = 0;
    int secondNodeId = 0;
    boolean isFirstNodeReversed = false;
    boolean isSecondNodeReversed = false;

    public Clause(int firstNodeId, int secondNodeId, boolean isFirstNodeReversed, boolean isSecondNodeReversed) {
        this.firstNodeId = firstNodeId;
        this.secondNodeId = secondNodeId;
        this.isFirstNodeReversed = isFirstNodeReversed;
        this.isSecondNodeReversed = isSecondNodeReversed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clause clause = (Clause) o;
        return (firstNodeId == clause.firstNodeId &&
                secondNodeId == clause.secondNodeId &&
                isFirstNodeReversed == clause.isFirstNodeReversed &&
                isSecondNodeReversed == clause.isSecondNodeReversed) ||
                (firstNodeId == clause.secondNodeId &&
                        secondNodeId == clause.firstNodeId &&
                        isFirstNodeReversed == clause.isSecondNodeReversed &&
                        isSecondNodeReversed == clause.isFirstNodeReversed);
    }

    @Override
    public int hashCode() {
        return firstNodeId + secondNodeId;
    }

    @Override
    public String toString() {
        return "Clause{" +
                (isFirstNodeReversed ? "!" : "") + firstNodeId +
                "," + (isSecondNodeReversed ? "!" : "") + secondNodeId +
                '}';
    }
}
