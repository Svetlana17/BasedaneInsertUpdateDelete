package com.example.user.picasopetrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

        @SerializedName("page")
        private long page;

        @SerializedName("total_results")
        private long totalResults;

        @SerializedName("total_pages")
        private long totalPages;

        @SerializedName("results")
        private List<Movie> results;

        public long getPage() { return page; }
        public void setPage(long value) { this.page = value; }

        public long getTotalResults() { return totalResults; }
        public void setTotalResults(long value) { this.totalResults = value; }

        public long getTotalPages() { return totalPages; }
        public void setTotalPages(long value) { this.totalPages = value; }

        public List<Movie> getResults() { return results; }
        public void setResults(List<Movie> value) { this.results = value; }

}
