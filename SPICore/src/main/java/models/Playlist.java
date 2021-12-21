package models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Playlist {
    private final String name;
    private final String description;
    @JsonIgnore
    private final boolean isPublic;

    public Playlist(String name, String description, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @JsonGetter("public")
    public boolean isPublic() {
        return isPublic;
    }
}
