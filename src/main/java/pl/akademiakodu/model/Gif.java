package pl.akademiakodu.model;

public class Gif {
    private String name;
    private String username;

    private boolean favorite;

    public Gif(String name, String username, boolean favourite) {
        this.name = name;
        this.username = username;
        this.favorite = favourite;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

