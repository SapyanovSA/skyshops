package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {

    private final UUID id;
    private final String name;
    private final String content;

    public Article(UUID id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "\n" + content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article a)) return false;
        return Objects.equals(name, a.name);
    }
}
