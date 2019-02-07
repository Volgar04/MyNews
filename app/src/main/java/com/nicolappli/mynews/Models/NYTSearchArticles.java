package com.nicolappli.mynews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NYTSearchArticles {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private Response response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {

        @SerializedName("docs")
        @Expose
        private List<Doc> docs = null;
        @SerializedName("meta")
        @Expose
        private Meta meta;

        public List<Doc> getDocs() {
            return docs;
        }

        public void setDocs(List<Doc> docs) {
            this.docs = docs;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

    }

    public class Multimedium {

        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("subtype")
        @Expose
        private String subtype;
        @SerializedName("caption")
        @Expose
        private Object caption;
        @SerializedName("credit")
        @Expose
        private Object credit;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("legacy")
        @Expose
        private Legacy legacy;
        @SerializedName("subType")
        @Expose
        private String subType;
        @SerializedName("crop_name")
        @Expose
        private String cropName;

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public Object getCaption() {
            return caption;
        }

        public void setCaption(Object caption) {
            this.caption = caption;
        }

        public Object getCredit() {
            return credit;
        }

        public void setCredit(Object credit) {
            this.credit = credit;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Legacy getLegacy() {
            return legacy;
        }

        public void setLegacy(Legacy legacy) {
            this.legacy = legacy;
        }

        public String getSubType() {
            return subType;
        }

        public void setSubType(String subType) {
            this.subType = subType;
        }

        public String getCropName() {
            return cropName;
        }

        public void setCropName(String cropName) {
            this.cropName = cropName;
        }

    }

    public class Meta {

        @SerializedName("hits")
        @Expose
        private Integer hits;
        @SerializedName("offset")
        @Expose
        private Integer offset;
        @SerializedName("time")
        @Expose
        private Integer time;

        public Integer getHits() {
            return hits;
        }

        public void setHits(Integer hits) {
            this.hits = hits;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

    }

    public class Legacy {

        @SerializedName("xlarge")
        @Expose
        private String xlarge;
        @SerializedName("xlargewidth")
        @Expose
        private Integer xlargewidth;
        @SerializedName("xlargeheight")
        @Expose
        private Integer xlargeheight;
        @SerializedName("wide")
        @Expose
        private String wide;
        @SerializedName("widewidth")
        @Expose
        private Integer widewidth;
        @SerializedName("wideheight")
        @Expose
        private Integer wideheight;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("thumbnailwidth")
        @Expose
        private Integer thumbnailwidth;
        @SerializedName("thumbnailheight")
        @Expose
        private Integer thumbnailheight;

        public String getXlarge() {
            return xlarge;
        }

        public void setXlarge(String xlarge) {
            this.xlarge = xlarge;
        }

        public Integer getXlargewidth() {
            return xlargewidth;
        }

        public void setXlargewidth(Integer xlargewidth) {
            this.xlargewidth = xlargewidth;
        }

        public Integer getXlargeheight() {
            return xlargeheight;
        }

        public void setXlargeheight(Integer xlargeheight) {
            this.xlargeheight = xlargeheight;
        }

        public String getWide() {
            return wide;
        }

        public void setWide(String wide) {
            this.wide = wide;
        }

        public Integer getWidewidth() {
            return widewidth;
        }

        public void setWidewidth(Integer widewidth) {
            this.widewidth = widewidth;
        }

        public Integer getWideheight() {
            return wideheight;
        }

        public void setWideheight(Integer wideheight) {
            this.wideheight = wideheight;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Integer getThumbnailwidth() {
            return thumbnailwidth;
        }

        public void setThumbnailwidth(Integer thumbnailwidth) {
            this.thumbnailwidth = thumbnailwidth;
        }

        public Integer getThumbnailheight() {
            return thumbnailheight;
        }

        public void setThumbnailheight(Integer thumbnailheight) {
            this.thumbnailheight = thumbnailheight;
        }

    }

    public class Doc {

        @SerializedName("web_url")
        @Expose
        private String webUrl;
        @SerializedName("snippet")
        @Expose
        private String snippet;
        @SerializedName("multimedia")
        @Expose
        private List<Multimedium> multimedia = null;
        @SerializedName("pub_date")
        @Expose
        private String pubDate;
        @SerializedName("document_type")
        @Expose
        private String documentType;
        @SerializedName("news_desk")
        @Expose
        private String newsDesk;
        @SerializedName("type_of_material")
        @Expose
        private String typeOfMaterial;
        @SerializedName("score")
        @Expose
        private Double score;

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getSnippet() {
            return snippet;
        }

        public void setSnippet(String snippet) {
            this.snippet = snippet;
        }

        public List<Multimedium> getMultimedia() {
            return multimedia;
        }

        public void setMultimedia(List<Multimedium> multimedia) {
            this.multimedia = multimedia;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getNewsDesk() {
            return newsDesk;
        }

        public void setNewsDesk(String newsDesk) {
            this.newsDesk = newsDesk;
        }

        public String getTypeOfMaterial() {
            return typeOfMaterial;
        }

        public void setTypeOfMaterial(String typeOfMaterial) {
            this.typeOfMaterial = typeOfMaterial;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

    }

}

