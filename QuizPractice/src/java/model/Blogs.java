/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 * Lớp này đại diện cho một blog trong hệ thống. Bao gồm các thuộc tính như ID,
 * tiêu đề, ID tác giả, ngày tạo, ngày cập nhật, nội dung, trạng thái, ảnh thu
 * nhỏ, thông tin ngắn gọn, danh mục và người dùng liên quan.
 *
 * @author DELL
 */
public class Blogs {

    /**
     * ID của blog.
     */
    private int blog_id;

    /**
     * Tiêu đề của blog.
     */
    private String title;

    /**
     * ID của tác giả.
     */
    private int author_id = 5;

    /**
     * Ngày tạo blog.
     */
    private Date createdDate;

    /**
     * Ngày cập nhật blog.
     */
    private Date updatedDate;

    /**
     * Nội dung của blog.
     */
    private String content;

    /**
     * Trạng thái của blog (hoạt động hoặc không).
     */
    private boolean status;

    /**
     * Hình ảnh thu nhỏ của blog.
     */
    private String thumbnail;

    /**
     * Thông tin ngắn gọn về blog.
     */
    private String brieinfo;

    /**
     * Danh mục của blog.
     */
    private Category category;

    /**
     * Đối tượng User liên quan đến blog.
     */
    private User blogUser_id;

    /**
     * Đối tượng User của tác giả blog.
     */
    private User blogAuthor_id;

    /**
     * Tên của tác giả blog.
     */
    private User blog_firstName;

    /**
     * Họ của tác giả blog.
     */
    private User blog_lastName;

    /**
     * Constructor mặc định.
     */
    public Blogs() {
    }

    /**
     * Constructor có tham số để khởi tạo đối tượng Blogs với các giá trị cụ
     * thể.
     *
     * @param blog_id ID của blog.
     * @param title Tiêu đề của blog.
     * @param author_id ID của tác giả.
     * @param createdDate Ngày tạo blog.
     * @param updatedDate Ngày cập nhật blog.
     * @param content Nội dung của blog.
     * @param status Trạng thái của blog.
     * @param thumbnail Hình ảnh thu nhỏ của blog.
     * @param brieinfo Thông tin ngắn gọn về blog.
     * @param category Danh mục của blog.
     */
    public Blogs(int blog_id, String title, int author_id, Date createdDate,
            Date updatedDate, String content, boolean status,
            String thumbnail, String brieinfo, Category category) {
        this.blog_id = blog_id;
        this.title = title;
        this.author_id = author_id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.content = content;
        this.status = status;
        this.thumbnail = thumbnail;
        this.brieinfo = brieinfo;
        this.category = category;
    }

    public User getBlog_firstName() {
        return blog_firstName;
    }

    public void setBlog_firstName(User blog_firstName) {
        this.blog_firstName = blog_firstName;
    }

    public User getBlog_lastName() {
        return blog_lastName;
    }

    public void setBlog_lastName(User blog_lastName) {
        this.blog_lastName = blog_lastName;
    }

    public String getBrieinfo() {
        return brieinfo;
    }

    public void setBrieinfo(String brieinfo) {
        this.brieinfo = brieinfo;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getBlogUser_id() {
        return blogUser_id;
    }

    public void setBlogUser_id(User blogUser_id) {
        this.blogUser_id = blogUser_id;
    }

    public User getBlogAuthor_id() {
        return blogAuthor_id;
    }

    public void setBlogAuthor_id(User blogAuthor_id) {
        this.blogAuthor_id = blogAuthor_id;
    }

}
