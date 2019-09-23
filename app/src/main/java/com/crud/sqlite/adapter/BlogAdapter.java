package com.crud.sqlite.adapter;



public class BlogAdapter {
    private String id,title_blog, desc_blog,tgl_post;

    public BlogAdapter(String id,String title_blog, String desc_blog) {
        this.id = id;
        this.title_blog = title_blog;
        this.desc_blog = desc_blog;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle_blog(String title_blog) {
        this.title_blog = title_blog;
    }

    public String getTitle_blog() {
        return title_blog;
    }

    public void setDesc_blog(String desc_blog) {
        this.desc_blog = desc_blog;
    }

    public String getDesc_blog() {
        return desc_blog;
    }
}

