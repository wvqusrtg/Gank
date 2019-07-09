package com.jdqm.gank.api;

import java.util.List;

/**
 * Created by Jdqm on 2019-07-08.
 */
public class GankResponse {
    private boolean error;
    private List<GankEntry> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankEntry> getResults() {
        return results;
    }

    public void setResults(List<GankEntry> results) {
        this.results = results;
    }
}
