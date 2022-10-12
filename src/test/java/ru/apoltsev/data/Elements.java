package ru.apoltsev.data;

public enum Elements {
    BOOKS("КНИГИ"),
    SOUVENIRS("СУВЕНИРЫ");

    private final String title;

    Elements(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {

        return title;
    }
}

