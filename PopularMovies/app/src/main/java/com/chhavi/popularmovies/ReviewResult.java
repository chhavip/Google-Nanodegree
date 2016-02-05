package com.chhavi.popularmovies;

import java.util.ArrayList;

/**
 * Created by chhavi on 5/2/16.
 */
public class ReviewResult {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    int page;

    public ArrayList<ReviewResultInner> getResults() {
        return results;
    }

    public void setResults(ArrayList<ReviewResultInner> results) {
        this.results = results;
    }

    ArrayList<ReviewResultInner> results;

    public class ReviewResultInner{

        String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        String author;
        String content;
        String url;

    }
}
