package com.testtask.ui.model;

public enum ProductSort {

    NAME_ASC("az"),
    NAME_DESC("za"),
    PRICE_LOW_TO_HIGH("lohi"),
    PRICE_HIGH_TO_LOW("hilo");

    private final String value;

    ProductSort(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
