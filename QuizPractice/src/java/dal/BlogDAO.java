/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import model.Category;

/**
 *
 * @author DELL
 */
public class BlogDAO extends DBContext {

    private static BlogDAO instance;
    private static Object lockPad = new Object();

    private BlogDAO() {
    }
    /**
     * Provides a global point of access to the UserDAO instance. Implements
     * double-checked locking to ensure thread safety.
     *
     * @return the singleton instance of UserDAO
     */
    public static BlogDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new BlogDAO();
                }
            }
        }
        return instance;
    }

    public List<Blog> searchPagingBlogs(String title, int index) {
        List<Blog> listPage = new ArrayList<>();
        try {
            String query = "SELECT b.id, b.title, b.author_id, c.created_at,\n"
                    + "      c.updated_at, content, thumbnail, briefinfo,\n"
                    + "      c.name\n"
                    + "      FROM categories c \n"
                    + "      JOIN [users] u ON u.id = c.created_by \n"
                    + "      JOIN blogs b ON b.author_id = u.id \n"
                    + "      WHERE b.title LIKE ?\n"
                    + "      ORDER BY b.created_at desc\n"
                    + "      OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";

            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, (index - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blogs = new Blog(); // Tạo đối tượng Blogs mới
                blogs.setBlog_id(rs.getInt(1));
                blogs.setTitle(rs.getString(2));
                blogs.setAuthor_id(rs.getInt(3));
                blogs.setCreatedDate(rs.getDate(4));
                blogs.setUpdatedDate(rs.getDate(5));
                blogs.setContent(rs.getString(6));
                blogs.setThumbnail(rs.getString(7));
                blogs.setBrieinfo(rs.getString(8));
                String category = rs.getString(9);
                Category cat = new Category(category);
                blogs.setCategory(cat);
                listPage.add(blogs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPage;
    }

    /**
     * Phương thức này dùng để đếm số lượng bài viết blog dựa trên tiêu đề.
     *
     * @param title Tiêu đề cần đếm.
     * @return Số lượng bài viết blog có tiêu đề chứa từ khóa tìm kiếm.
     */
    public int countBlogsByTitle(String title) {        
        try {
            String query = "SELECT COUNT(*) FROM blogs WHERE title LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Phương thức này dùng để lấy danh sách tất cả các danh mục.
     *
     * @return Danh sách các đối tượng Category.
     */
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {

            String query = "SELECT * FROM categories";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category ca = new Category();
                ca.setId(rs.getInt(1));
                ca.setCategory_Name(rs.getString(2));
                ca.setCreated_at(rs.getDate(3));
                ca.setUpDate_at(rs.getDate(4));
                ca.setCreated_by(rs.getInt(5));
                ca.setUpdate_by(rs.getInt(6));
                categories.add(ca);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    /**
     * Phương thức này dùng để lấy số lượng bài viết blog hiện có trong
     * database.
     *
     * @return Số lượng bài viết blog.
     */
    public int getBlogs() {
        try {

            String query = "select COUNT(*) from blogs";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Phương thức này dùng để phân trang cho các bài viết blog.
     *
     * @param index Chỉ số của trang cần lấy (bắt đầu từ 1).
     * @return Danh sách các bài viết blog trên trang được chỉ định.
     */
    public List<Blog> pagingBlogs(int index) {
        List<Blog> listPage = new ArrayList<>();
        try {

            String query = "select b.id, b.title, b.author_id, b.created_at,\n"
                    + "        b.updated_at, content, thumbnail, briefinfo, c.name\n"
                    + "         FROM categories c \n"
                    + "          JOIN [users] u ON u.id = c.created_by \n"
                    + "           JOIN blogs b ON b.author_id = u.id\n"
                    + "            order by b.created_at desc \n"
                    + "          offset ? rows fetch next 6\n"
                    + "           rows only";

            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {

                Blog blogs = new Blog(); // Tạo đối tượng Blogs mới
                blogs.setBlog_id(rs.getInt(1));
                blogs.setTitle(rs.getString(2));
                blogs.setAuthor_id(rs.getInt(3));
                blogs.setCreatedDate(rs.getDate(4));
                blogs.setUpdatedDate(rs.getDate(5));
                blogs.setContent(rs.getString(6));
                blogs.setThumbnail(rs.getString(7));
                blogs.setBrieinfo(rs.getString(8));
                String category = rs.getString(9);
                Category cat = new Category(category);
                blogs.setCategory(cat);
                listPage.add(blogs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPage;
    }

    /**
     * Phương thúc này dùng để lấy thông tin của bài blogs mới nhất.
     *
     * @return trả về bài viết được cập nhật mới nhất
     */
    public Blog findNewPost() {

        Blog blogs = new Blog(); // Tạo đối tượng Blogs mới
        try {
            String query = "SELECT TOP 1 * FROM blogs ORDER BY created_at desc";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogs.setBlog_id(rs.getInt(1));
                blogs.setTitle(rs.getString(2));
                blogs.setAuthor_id(rs.getInt(3));
                blogs.setCreatedDate(rs.getDate(4));
                blogs.setThumbnail(rs.getString(8));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }

    /**
     * Phương thức này dùng để lấy ra tất cả các bài viết blog liên quan đến một
     * danh mục cụ thể thông qua ID danh mục.
     *
     * @param cid lấy các bài viết blog theo id.
     * @param cid ID của danh mục cần lấy các bài viết blog.
     * @return danh sách các đối tượng Blogs.
     */
    public List<Blog> getAllCategoriesByid(String cid) {
        List<Blog> list = new ArrayList<>();
        try {

            String query = "SELECT b.id, b.title, b.author_id, c.created_at,\n"
                    + "     c.updated_at, content, thumbnail, briefinfo, c.name\n"
                    + "     FROM categories c \n"
                    + "     JOIN [users] u ON u.id = c.created_by \n"
                    + "     JOIN blogs b ON b.author_id = u.id \n"
                    + "     WHERE c.id LIKE ?\n"
                    + "     ORDER BY b.created_at desc";
            ps = connection.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {

                Blog blogs = new Blog(); // Tạo đối tượng Blogs mới
                blogs.setBlog_id(rs.getInt(1));
                blogs.setTitle(rs.getString(2));
                blogs.setAuthor_id(rs.getInt(3));
                blogs.setCreatedDate(rs.getDate(4));
                blogs.setUpdatedDate(rs.getDate(5));
                blogs.setContent(rs.getString(6));
                blogs.setThumbnail(rs.getString(7));
                blogs.setBrieinfo(rs.getString(8));
                String category = rs.getString(9);
                Category cat = new Category(category);
                blogs.setCategory(cat);
                list.add(blogs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Phương thức này dùng để lấy thông tin cụ thể của blog theo id
     *
     * @param id lấy các bài viết blog theo id.
     */
    public Blog getBlogById(String id) {
        Blog blogs = null;
        String query = "SELECT b.id, b.title, b.author_id, b.created_at,\n"
                + "                b.updated_at, content, b.thumbnail, b.briefinfo, c.name\n"
                + "                 ,u.last_name + ' ' + u.first_Name as fullName\n"
                + "                    FROM categories c \n"
                + "                   JOIN [Users] u ON u.id = c.created_By \n"
                + "                   JOIN blogs b ON b.author_id = u.id \n"
                + "                    WHERE  b.id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogs = new Blog();
                blogs.setBlog_id(rs.getInt(1));
                blogs.setTitle(rs.getString(2));
                blogs.setAuthor_id(rs.getInt(3));
                blogs.setCreatedDate(rs.getDate(4));
                blogs.setUpdatedDate(rs.getDate(5));
                blogs.setContent(rs.getString(6));
                blogs.setThumbnail(rs.getString(7));
                blogs.setBrieinfo(rs.getString(8));
                String category = rs.getString(9);
                Category cat = new Category(category);
                blogs.setCategory(cat); // Set the category name
                blogs.setBlog_firstName(rs.getString(10)); // Set the first name
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }
    
    public List<Blog> listTop8Blog() {
        List<Blog> listBlogs = new ArrayList<>();
        try {
            String query = "SELECT TOP 8 * FROM blogs order by created_at desc";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                int author_id = rs.getInt(3);
                Date created_at = rs.getDate(4);
                Date updated_at = rs.getDate(5);
                String content = rs.getString(6);
                
                int status = rs.getInt(7);
                String thumbnail = rs.getString(8);
                String briefinfo = rs.getString(9);
                
                Blog  blog = new Blog();
                blog.setBlog_id(id);
                blog.setTitle(title);
                blog.setAuthor_id(author_id);
                blog.setCreatedDate(created_at);
                blog.setUpdatedDate(updated_at);
                blog.setContent(content);
                blog.setStatus(true);
                blog.setThumbnail(thumbnail);
                blog.setBrieinfo(briefinfo);
                
                listBlogs.add(blog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBlogs;
    }

    public static void main(String[] args) {
        BlogDAO b = getInstance();
        System.out.println(b.getBlogById("24"));
    }

}
