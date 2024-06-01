package dal;

import context.DBContext;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Slider;
import model.Subject;

/**
 *
 * @author Admin
 */
// Data Access Object for User operations, extending DBContext to utilize database connections
public class SliderDAO extends DBContext {

    // Singleton instance of UserDAO
    private static SliderDAO instance;
    // Lock object for thread-safe singleton instantiation
    private static Object lockPad = new Object();

    // Private constructor to prevent instantiation
    private SliderDAO() {
    }

    // Returns the singleton instance of SliderDAO
    public static SliderDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new SliderDAO();
                }
            }
        }
        return instance;
    }
    
    //Query
     public List<Slider> listTop8Slider() {
        List<Slider> listSliders = new ArrayList<>();
        try {
            String query = "SELECT TOP 8 * FROM slider order by ID desc";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String subTitle = rs.getString(3);
                String content = rs.getString(4);
                String image = rs.getString(5);
                String linkUrl = rs.getString(6);
                Date createdAt = rs.getDate(7);
                int createdBy = rs.getInt(8);
                int status = rs.getInt(9);
                
                Slider slider = new Slider(id, title, subTitle, content, image, linkUrl, createdAt, createdBy, status);
                listSliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSliders;
    }
    
}