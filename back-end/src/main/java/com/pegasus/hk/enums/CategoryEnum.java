package com.pegasus.hk.enums;

import java.util.Arrays;

import lombok.NonNull;

public enum CategoryEnum {

    TRAVEL("Travel"),
    UTILITIES("Utilities"),
    CATEGORY("Category"),
    TRANSPORTATION("Transportation"),
    INCOME("Income"),
    SHOPPING("Shopping"),
    GIFTS("Gifts"),
    SUBSCRIPTION("Subscription"),
    FOOD("Food");

    private final String value;

    CategoryEnum(final String value) {
        this.value = value;
    }

    public static CategoryEnum get(@NonNull final String value) {
        return Arrays.stream(CategoryEnum.values())
                .filter(valueEnum -> value.equals(valueEnum.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    public String value() {
        return value;
    }

}
