package com.codeminton.tutorialapi.util;

import java.util.Locale;

public final class SlugUtils {

    private SlugUtils() {
    }

    public static String toSlug(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Slug source cannot be null.");
        }

        String slug = value.trim()
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+|-+$", "")
                .replaceAll("-{2,}", "-");

        if (slug.isBlank()) {
            throw new IllegalArgumentException("A valid slug could not be generated from the supplied value.");
        }

        return slug;
    }
}
