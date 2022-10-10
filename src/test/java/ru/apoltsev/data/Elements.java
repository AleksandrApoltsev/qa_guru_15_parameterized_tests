package ru.apoltsev.data;

public enum Elements {
    BOOKS("КНИГИ"),
    SOUVENIRS("СУВЕНИРЫ");

    private final String titleElements;

    private Elements(final String titleElements1) {
        this.titleElements = titleElements1;
    }

    public String getElements() {
        return titleElements;
    }
}
