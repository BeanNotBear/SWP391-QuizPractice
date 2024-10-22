package dal;

import context.DBContext;
import dto.BlogManagerDetailDTO;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public List<Blog> searchPagingBlogs(String title, String category, int index) {
    List<Blog> listPage = new ArrayList<>();
    try {
        String query = "SELECT b.id, b.title, b.author_id, b.created_at,\n"
                + "       b.updated_at, b.content, b.thumbnail, b.briefinfo,\n"
                + "       c.name AS category_name, b.status\n"
                + "FROM blogs b\n"
                + "LEFT JOIN categories c ON b.CategoryId = c.id\n"
                + "WHERE b.title LIKE ?\n";

        if (category != null && !category.isEmpty()) {
            query += "AND b.CategoryId = ?\n";
        }

        query += "AND b.status = 1\n"  // Chỉ lấy các blog có status = 1
                + "ORDER BY b.created_at DESC\n"
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";

        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + title + "%");

        int parameterIndex = 2;
        if (category != null && !category.isEmpty()) {
            ps.setInt(parameterIndex++, Integer.parseInt(category)); // Chuyển đổi category thành số nguyên
        }

        ps.setInt(parameterIndex, (index - 1) * 6);

        rs = ps.executeQuery();
        while (rs.next()) {
            Blog blogs = new Blog();
            blogs.setBlog_id(rs.getInt(1));
            blogs.setTitle(rs.getString(2));
            blogs.setAuthor_id(rs.getInt(3));
            blogs.setCreatedDate(rs.getDate(4));
            blogs.setUpdatedDate(rs.getDate(5));
            blogs.setContent(rs.getString(6));
            blogs.setThumbnail(rs.getString(7));
            blogs.setBrieinfo(rs.getString(8));
            String categoryName = rs.getString(9);
            Category cat = new Category();
            cat.setCategory_Name(categoryName);
            blogs.setCategory(cat);
            blogs.setStatus(rs.getBoolean(10)); // Giả sử status là boolean

            listPage.add(blogs);
        }
    } catch (SQLException ex) {
        Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listPage;
}

public List<Blog> searchPagingAllBlogs(String title, String category, int index) {
    List<Blog> listPage = new ArrayList<>();
    try {
        String query = "SELECT b.id, b.title, b.author_id, b.created_at,\n"
                + "       b.updated_at, b.content, b.thumbnail, b.briefinfo,\n"
                + "       c.name AS category_name, b.status\n"
                + "FROM blogs b\n"
                + "LEFT JOIN categories c ON b.CategoryId = c.id\n"
                + "WHERE b.title LIKE ?\n";

        if (category != null && !category.isEmpty()) {
            query += "AND b.CategoryId = ?\n";
        }

        query += "ORDER BY b.created_at DESC\n"
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";

        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + title + "%");

        int parameterIndex = 2;
        if (category != null && !category.isEmpty()) {
            ps.setInt(parameterIndex++, Integer.parseInt(category)); // Chuyển đổi category thành số nguyên
        }

        ps.setInt(parameterIndex, (index - 1) * 6);

        rs = ps.executeQuery();
        while (rs.next()) {
            Blog blogs = new Blog();
            blogs.setBlog_id(rs.getInt(1));
            blogs.setTitle(rs.getString(2));
            blogs.setAuthor_id(rs.getInt(3));
            blogs.setCreatedDate(rs.getDate(4));
            blogs.setUpdatedDate(rs.getDate(5));
            blogs.setContent(rs.getString(6));
            blogs.setThumbnail(rs.getString(7));
            blogs.setBrieinfo(rs.getString(8));
            String categoryName = rs.getString(9);
            Category cat = new Category();
            cat.setCategory_Name(categoryName);
            blogs.setCategory(cat);
            blogs.setStatus(rs.getBoolean(10)); // Giả sử status là boolean

            listPage.add(blogs);
        }
    } catch (SQLException ex) {
        Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listPage;
}

public int countAllBlogsByTitleAndCategory(String title, String category) {
    int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM blogs b\n"
                + "LEFT JOIN categories c ON b.CategoryId = c.id\n"
                + "WHERE b.title LIKE ?";

        if (category != null && !category.isEmpty()) {
            query += " AND b.CategoryId = ?";
        }

        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + title + "%");

        int parameterIndex = 2;
        if (category != null && !category.isEmpty()) {
            ps.setInt(parameterIndex, Integer.parseInt(category)); // Chuyển đổi category thành số nguyên
        }

        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return count;
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
            String query = "SELECT TOP 1 * FROM blogs where status='1' ORDER BY created_at desc ";
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
    public Blog getBlogById(int id) {
        Blog blogs = null;
        String query = "SELECT b.id, b.title, b.author_id, b.created_at, b.updated_at, b.content, \n"
                + "       b.thumbnail, b.briefinfo, c.name AS category_name,\n"
                + "       u.full_name, u.email, u.phone_number, b.status\n"
                + "FROM blogs b\n"
                + "LEFT JOIN categories c ON b.CategoryId = c.id\n"
                + "LEFT JOIN [Users] u ON b.author_id = u.id\n"
                + "WHERE b.id = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
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
                blogs.setEmail_blog(rs.getString(11));
                blogs.setNumber_blog(rs.getString(12));
                blogs.setStatus(rs.getBoolean(13));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }

    public List<Blog> listTop8Blog() {
        List<Blog> listBlogs = new ArrayList<>();
        try {
            String query = "SELECT TOP 4 * \n"
                    + "FROM blogs \n"
                    + "WHERE status = 1 \n"
                    + "ORDER BY created_at DESC";

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

                Blog blog = new Blog();
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

    public boolean updateBlog(BlogManagerDetailDTO blog) {
        boolean updated = false;
        String query = "UPDATE blogs SET title = ?, CategoryId = (SELECT id FROM categories WHERE name = ?), content = ?, status = ?, briefinfo = ?, thumbnail=? WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getCategoryName());
            ps.setString(3, blog.getContent());
            ps.setInt(4, blog.isStatus() ? 1 : 0);

            ps.setString(5, blog.getBriefinfo());
            ps.setString(6, blog.getThumbnail());
            ps.setInt(7, blog.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }

    public BlogManagerDetailDTO getBlogDetailDTOById(int id) {
        BlogManagerDetailDTO blogs = new BlogManagerDetailDTO();
        String query = "select b.id, b.title, c.name, b.content, b.status, b.thumbnail, b.briefinfo \n"
                + "from blogs b left join categories c on b.CategoryId = c.id \n"
                + "where b.id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogs.setId(rs.getInt(1));
                blogs.setTitle(rs.getString(2));

                blogs.setContent(rs.getString(4));
                blogs.setThumbnail(rs.getString(6));
                blogs.setBriefinfo(rs.getString(7));
                blogs.setCategoryName(rs.getString(3));
                blogs.setStatus(rs.getBoolean(5));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }

    public boolean addBlog(String title, String briefinfo, String content, boolean status, String thumbnail, int categoryId, int authorId) {
        String query = "INSERT INTO [dbo].[blogs] "
                + "([title], [author_id], [created_at], [updated_at], [content], [status], [thumbnail], [briefinfo], [CategoryId]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, title);
            ps.setInt(2, authorId); // Thêm giá trị cho cột author_id
            ps.setDate(3, new Date(System.currentTimeMillis()));
            ps.setDate(4, new Date(System.currentTimeMillis()));
            ps.setString(5, content);
            ps.setInt(6, status ? 1 : 0); // Chuyển đổi boolean thành int (1 cho true, 0 cho false)
            ps.setString(7, thumbnail);
            ps.setString(8, briefinfo);
            ps.setInt(9, categoryId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public int getCategoryIdByName(String categoryName) {
        int categoryId = -1;
        String query = "SELECT id FROM categories WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, categoryName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoryId = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }
    
      public static void main(String[] args) {
        BlogDAO b = new BlogDAO();
        System.out.println(b.addBlog("abc", "abc", "abc", true, "null", 1, 3));
    }

public int countBlogsByTitleAndCategory(String title, String category) {
    int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM blogs b\n"
                + "LEFT JOIN categories c ON b.CategoryId = c.id\n"
                + "WHERE b.title LIKE ?\n"
                + "AND b.status = 1"; // Only count blogs with status = 1

        if (category != null && !category.isEmpty()) {
            query += " AND b.CategoryId = ?";
        }

        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + title + "%");

        if (category != null && !category.isEmpty()) {
            ps.setInt(2, Integer.parseInt(category)); // Convert category to integer if it's not empty
        }

        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return count;
}

}
