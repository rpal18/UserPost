package com.rohit.user_post.Dto;

import java.util.List;

public class PostResponse {
    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;

    private Integer totalElements;
    private Integer totalPageNumber;

    private boolean isLast;

    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(Integer totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public PostResponse(List<PostDto> content, Integer pageNumber, Integer pageSize, Integer totalElements, Integer totalPageNumber, boolean isLast) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPageNumber = totalPageNumber;
        this.isLast = isLast;
    }

    public PostResponse() {
    }
}
