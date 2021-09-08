package models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Playlist {
    public String name;
    public String description;
    @JsonIgnore
    public boolean isPublic;

    public Playlist(String name, String description, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }



    @JsonGetter("public")
    public boolean getThePublic () {
        return isPublic;
    }


}
