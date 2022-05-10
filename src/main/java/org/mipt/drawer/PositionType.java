package org.mipt.drawer;

public enum PositionType {
    RIGHT_BOTTOM("RT"),
    LEFT_BOTTOM("LT"),
    LEFT_TOP("LB"),
    RIGHT_TOP("RB");

    String name;

    PositionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
