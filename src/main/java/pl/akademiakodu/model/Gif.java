package pl.akademiakodu.model;

import javax.persistence.*;

@Entity
public class Gif {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String username;
    private boolean favorite;
    private int categoryId;

    @Lob
    @Column(columnDefinition="mediumblob")
    private byte[] data;

    public Gif() {
    }

    public Gif(String name, String username, boolean favorite, int categoryId) {
        this.name = name;
        this.username = username;
        this.favorite = favorite;
        this.categoryId = categoryId;
    }

    public Gif(String name, String username, boolean favorite, int categoryId, byte[] data) {
        this.name = name;
        this.username = username;
        this.favorite = favorite;
        this.categoryId = categoryId;
        this.data = data;
    }

    public Gif(String name, byte[] data) {
        this.name = name;
        this.data = data;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

