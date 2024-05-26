package dal;

import context.DBContext;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blogs;
import model.Category;
import model.User;
import util.security.Security;

// Data Access Object for User operations, extending DBContext to utilize database connections
public class UserDAO extends DBContext {

    // Singleton instance of UserDAO
    private static UserDAO instance;
    // Lock object for thread-safe singleton instantiation
    private static Object lockPad = new Object();
    private Logger logger = Logger.getLogger(UserDAO.class.getName());

    // Private constructor to prevent instantiation
    private UserDAO() {}

    // Returns the singleton instance of UserDAO
    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }

    public boolean checkExistByEmail(String email) {
        boolean isDuplicate = true;
        String sql = "Select 1 From users where email =?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                isDuplicate = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }
    
    public User findUserById(int id) {
        String query = "SELECT * "
                + "FROM users "
                + "WHERE id = ?";
        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhoneNumber(rs.getString(5));
                user.setGender(rs.getBoolean(6));
                user.setDob(rs.getDate(7));
                user.setProfileImg(rs.getString(8));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setCreatedAt(rs.getDate(11));
                user.setUpdatedAt(rs.getDate(12));
                user.setRoleId(rs.getInt(13));
                user.setStatusID(rs.getInt(14));
                user.setToken(rs.getString(15));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean updatePassword(String password, String email) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, Security.encryptToSHA512(password));
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int insert(User user) {
        // SQL query with placeholders for parameterized input
        String query = "INSERT INTO [dbo].[users]\n"
                + "           ([first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone_number]\n"
                + "           ,[gender]\n"
                + "           ,[dob]\n"
                + "           ,[profile_img]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[created_at]\n"
                + "           ,[updated_at]\n"
                + "           ,[role_id]\n"
                + "           ,[status_id]\n"
                + "           ,[token])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int rowAffected = 0; // Variable to store the number of rows affected

        try {
            // Prepare the SQL query for execution
            ps = connection.prepareStatement(query);

            // Set parameters for the PreparedStatement
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setBoolean(5, user.isGender());
            ps.setDate(6, user.getDob());

            // Set the profile image path based on gender
            String imgPath = user.isGender() ? "images/pic-1.jpg" : "images/pic-5.jpg";
            ps.setString(7, imgPath);

            ps.setString(8, user.getUsername());
            ps.setString(9, user.getPassword()); // Ensure password is hashed
            ps.setDate(10, user.getCreatedAt());
            ps.setDate(11, user.getUpdatedAt());
            ps.setInt(12, user.getRoleId());
            ps.setInt(13, user.getStatusID());
            ps.setString(14, user.getToken());

            // Execute the query and get the number of rows affected
            rowAffected = ps.executeUpdate();
        } catch (SQLException e) {
            // Log the exception (if a logging framework is available)
            e.printStackTrace(); // Replace with logger in real application
        }
        return rowAffected;
    }

    // Checks if a user already exists by email
    @SuppressWarnings("all")
    public boolean checkUserExistedByUsername(String username) {
        String query = "SELECT 1\n"
                + "FROM users\n"
                + "WHERE username = ?";
        boolean isDuplicate = true;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                isDuplicate = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }

    // Finds a user by email and password
    public User findUserByEmailAndPassword(String email, String password) {
        String query = "SELECT *\n"
                + "FROM users\n"
                + "WHERE email = ? AND [password] = ?";
        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhoneNumber(rs.getString(5));
                user.setGender(rs.getBoolean(6));
                user.setDob(rs.getDate(7));
                user.setProfileImg(rs.getString(8));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setCreatedAt(rs.getDate(11));
                user.setUpdatedAt(rs.getDate(12));
                user.setRoleId(rs.getInt(13));
                user.setStatusID(rs.getInt(14));
                user.setToken(rs.getString(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Finds a user by email
    public User findUserByEmail(String email) {
        String query = "SELECT *\n"
                + "FROM users\n"
                + "WHERE email = ?";
        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhoneNumber(rs.getString(5));
                user.setGender(rs.getBoolean(6));
                user.setDob(rs.getDate(7));
                user.setProfileImg(rs.getString(8));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setCreatedAt(rs.getDate(11));
                user.setUpdatedAt(rs.getDate(12));
                user.setRoleId(rs.getInt(13));
                user.setStatusID(rs.getInt(14));
                user.setToken(rs.getString(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Updates the status of a user by token
    public int UpdateStatusByToken(String token) {
        String query = "UPDATE users\n"
                + "SET status_id = 2\n"
                + "WHERE token = ?";
        int rowAffected = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rowAffected = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowAffected;
    }

    // Finds a user by token
    public User findUserByToken(String token) {
        String query = "SELECT * \n"
                + "FROM users\n"
                + "WHERE token = ?";

        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhoneNumber(rs.getString(5));
                user.setGender(rs.getBoolean(6));
                user.setDob(rs.getDate(7));
                user.setProfileImg(rs.getString(8));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setCreatedAt(rs.getDate(11));
                user.setUpdatedAt(rs.getDate(12));
                user.setRoleId(rs.getInt(13));
                user.setStatusID(rs.getInt(14));
                user.setToken(rs.getString(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Updates the token of a user by email
    public int UpdateTokenByEmail(String token, String email) {
        String query = "UPDATE users"
                + "SET token = ?"
                + "WHERE email = ?";
        int rowAffected = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            ps.setString(2, email);
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowAffected;
    }

    public List<Blogs> searchPagingBlogs(String title, int index) {
        List<Blogs> listPage = new ArrayList<>();
        try {
            String query = "SELECT b.id, b.title, b.author_id, c.created_at, c.updated_at, content, status, thumbnail, briefinfo, c.name "
                    + "FROM categories c "
                    + "JOIN [users] u ON u.id = c.created_by "
                    + "JOIN blogs b ON b.author_id = u.id "
                    + "WHERE b.title LIKE ? "
                    + "ORDER BY b.created_at "
                    + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";

            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, (index - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                int blogId = rs.getInt(1);
                String blogTitle = rs.getString(2);
                int authorId = rs.getInt(3);
                Date createdDate = rs.getDate(4);
                Date updatedDate = rs.getDate(5);
                String content = rs.getString(6);
                boolean status = rs.getBoolean(7);
                String thumbnail = rs.getString(8);
                String briefInfo = rs.getString(9);
                String category = rs.getString(10);

                Category cat = new Category(category);
                Blogs blog = new Blogs(blogId, blogTitle, authorId, createdDate, updatedDate, content, status, thumbnail, briefInfo, cat);
                listPage.add(blog);
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

                categories.add(new Category(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
                        rs.getInt(6)));
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
    public List<Blogs> pagingBlogs(int index) {
        List<Blogs> listPage = new ArrayList<>();
        try {
            String query = "select b.id, b.title, b.author_id, b.created_at, b.updated_at, content, status, thumbnail, briefinfo, c.name \n"
                    + "FROM categories c \n"
                    + "JOIN [users] u ON u.id = c.created_by \n"
                    + "JOIN blogs b ON b.author_id = u.id\n"
                    + "order by b.created_at  \n"
                    + "offset ? rows fetch next 3\n"
                    + "rows only";

            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                int blogId = rs.getInt(1);
                String title = rs.getString(2);
                int authorId = rs.getInt(3);
                Date createdDate = rs.getDate(4);
                Date updatedDate = rs.getDate(5);
                String content = rs.getString(6);
                boolean status = rs.getBoolean(7);
                String thumbnail = rs.getString(8);
                String briefInfo = rs.getString(9);
                String category = rs.getString(10);

                Category cat = new Category(category);
                Blogs blog = new Blogs(blogId, title, authorId, createdDate, updatedDate, content, status, thumbnail, briefInfo, cat);
                listPage.add(blog);
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
    public Blogs findNewPost() {
        Blogs blogs = null;
        try {
            String query = "SELECT top 1*\n"
                    + "FROM blogs\n"
                    + "ORDER BY created_at ";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogs = new Blogs();
                blogs.setBlog_id(rs.getInt(1));
                blogs.setTitle(rs.getString(2));
                blogs.setAuthor_id(rs.getInt(3));
                blogs.setCreatedDate(rs.getDate(4));
                blogs.setUpdatedDate(rs.getDate(5));
                blogs.setContent(rs.getString(6));
                blogs.setStatus(rs.getBoolean(7));
                blogs.setThumbnail(rs.getString(8));
                blogs.setBrieinfo(rs.getString(9));

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
     * @param cid ID của danh mục cần lấy các bài viết blog.
     * @return danh sách các đối tượng Blogs.
     */
    public List<Blogs> getAllCategoriesByid(String cid) {
        List<Blogs> list = new ArrayList<>();
        try {
            String query = "SELECT b.id, b.title, b.author_id, c.created_at, c.updated_at, content, status, thumbnail, briefinfo, c.name "
                    + "FROM categories c "
                    + "JOIN [users] u ON u.id = c.created_by "
                    + "JOIN blogs b ON b.author_id = u.id "
                    + "WHERE c.id LIKE ? "
                    + "ORDER BY b.created_at ";
            ps = connection.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {

                int blogId = rs.getInt(1);
                String blogTitle = rs.getString(2);
                int authorId = rs.getInt(3);
                Date createdDate = rs.getDate(4);
                Date updatedDate = rs.getDate(5);
                String content = rs.getString(6);
                boolean status = rs.getBoolean(7);
                String thumbnail = rs.getString(8);
                String briefInfo = rs.getString(9);
                String category = rs.getString(10);

                Category cat = new Category(category);
                Blogs blog = new Blogs(blogId, blogTitle, authorId, createdDate, updatedDate, content, status, thumbnail, briefInfo, cat);
                list.add(blog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
