package com.nicolappli.mynews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewYorkTimesAPI {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("response")
    @Expose
    private Response response;

    public List<Result> getResults() {
        return results;
    }

    public Response getResponse() {
        return response;
    }

    // For Top Stories & Most Popular & Search Articles
    public class Result {

        @SerializedName("section")
        @Expose
        private String section;
        @SerializedName("subsection")
        @Expose
        private String subsection;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("web_url")
        @Expose
        private String webUrl;
        @SerializedName("published_date")
        @Expose
        private String publishedDate;
        @SerializedName("multimedia")
        @Expose
        private List<Multimedium> multimedia = null;
        @SerializedName("media")
        @Expose
        private List<Medium> media = null;

        // search articles
        @SerializedName("pub_date")
        @Expose
        private String pubDate;
        @SerializedName("news_desk")
        @Expose
        private String newsDesk;
        @SerializedName("snippet")
        @Expose
        private String snippet;

        public String getSection() {
            return section;
        }

        public String getSubsection() {
            return subsection;
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

        public String getWebUrl() {
            return webUrl;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public List<Multimedium> getMultimedia() {
            return multimedia;
        }

        public List<Medium> getMedia() {
            return media;
        }

        //search articles
        public String getPubDate() {
            return pubDate;
        }

        public String getNewsDesk() {
            return newsDesk;
        }

        public String getSnippet() {
            return snippet;
        }
    }

    // For Top Stories
    public class Multimedium {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }
    }

    // For Most Popular
    public class Medium {

        @SerializedName("media-metadata")
        @Expose
        private List<MediaMetadatum> mediaMetadata = null;

        public List<MediaMetadatum> getMediaMetadata() {
            return mediaMetadata;
        }
    }

    // For Most Popular
    public class MediaMetadatum {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }
    }

    // For Search Articles
    public class Response {

        @SerializedName("docs")
        @Expose
        private List<Result> docs = null;

        public List<Result> getDocs() {
            return docs;
        }
    }
}
