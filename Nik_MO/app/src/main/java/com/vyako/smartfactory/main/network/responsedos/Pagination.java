package com.vyako.smartfactory.main.network.responsedos;

/**
 * Created by nikhilm on 26-Jun-17.
 */

 /*"page_count": 1,
         "current_page": 1,
         "has_next_page": false,
         "has_prev_page": false,
         "count": 16,
         "limit": 10000*/
public class Pagination {

    private int current_page;
    private boolean has_next_page;
    private boolean has_prev_page;
    private int count;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public boolean isHas_next_page() {
        return has_next_page;
    }

    public void setHas_next_page(boolean has_next_page) {
        this.has_next_page = has_next_page;
    }

    public boolean isHas_prev_page() {
        return has_prev_page;
    }

    public void setHas_prev_page(boolean has_prev_page) {
        this.has_prev_page = has_prev_page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    private int limit;
}
