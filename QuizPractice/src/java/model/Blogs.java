/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Lớp này đại diện cho một blog trong hệ thống. Bao gồm các thuộc tính như ID,
 * tiêu đề, ID tác giả, ngày tạo, ngày cập nhật, nội dung, trạng thái, ảnh thu
 * nhỏ, thông tin ngắn gọn, danh mục và người dùng liên quan.
 *
 * @author DELL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
