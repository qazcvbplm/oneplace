package com.ops.dto;

import org.hibernate.validator.constraints.NotBlank;

public class ActivityAdd {

    @NotBlank
    private String title;
    @NotBlank
    private String subTitle;
    @NotBlank
    private String image;

    
    private String richText;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }
}
