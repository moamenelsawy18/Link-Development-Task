package com.task.linkdev.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @Expose
    @SerializedName("articles")
    private List<Article> articles;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("source")
    private String source;

    @Expose
    @SerializedName("top")
    private String top;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }
}
