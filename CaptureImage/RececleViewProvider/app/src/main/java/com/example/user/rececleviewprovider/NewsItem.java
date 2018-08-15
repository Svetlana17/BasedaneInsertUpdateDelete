package com.example.user.rececleviewprovider;


@Entity
public class NewsItem {


    @Id
    private Long id;

    @SerializedName("author")
    private String author;


    @SerializedName("title")
    private String title;


    @SerializedName("description")
    private String description;


    @SerializedName("url")
    private String url;


    @SerializedName("urlToImage")
    private String urlToImage;


    @SerializedName("publishedAt")
    private String publishedAt;


    @Generated(hash = 280059824)
    public NewsItem(Long id, String author, String title, String description,
                    String url, String urlToImage, String publishedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    @Generated(hash = 1697690472)
    public NewsItem() {
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
