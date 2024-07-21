package dal;

import context.DBContext;
import dto.EnrollmentCountDTO;
import java.util.ArrayList;
import java.util.List;

public class StatisticDAO extends DBContext {
    
    private static StatisticDAO instance;
    private static Object lockPad = new Object();
    
    private StatisticDAO() {
    }

    /**
     * Provides a global point of access to the UserDAO instance. Implements
     * double-checked locking to ensure thread safety.
     *
     * @return the singleton instance of UserDAO
     */
    public static StatisticDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new StatisticDAO();
                }
            }
        }
        return instance;
    }
    
    public double getTotalRevenue() {
        String query = "SELECT SUM(pp.sale_price) AS TotalRevenue\n"
                + "FROM [SWP391_G6].[dbo].[Subject_Register] AS sr\n"
                + "INNER JOIN package_price AS pp ON sr.PackageId = pp.id";
        double result = 0;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int getNumberOfEnrollments() {
        String query = "SELECT COUNT(*) AS NumberOfEnrolments\n"
                + "FROM [SWP391_G6].[dbo].[Subject_Register] AS sr";
        int result = 0;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public double getTotalProfit() {
        String query = "SELECT SUM(pp.sale_price) - SUM(pp.original_price) AS TotalProfit\n"
                + "FROM [SWP391_G6].[dbo].[Subject_Register] AS sr\n"
                + "INNER JOIN package_price AS pp ON sr.PackageId = pp.id";
        double result = 0;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public List<EnrollmentCountDTO> getEnrollments() {
        String query = "SELECT sr.[SubjectId], \n"
                + "       s.name,\n"
                + "       COUNT(sr.[UserId]) AS EnrollmentCount\n"
                + "FROM [SWP391_G6].[dbo].[Subject_Register] AS sr\n"
                + "INNER JOIN subjects AS s ON sr.SubjectId = s.id\n"
                + "GROUP BY sr.[SubjectId], s.name;";
        List<EnrollmentCountDTO> enrollmentCountDTOs = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {                
                enrollmentCountDTOs.add(
                        new EnrollmentCountDTO(rs.getInt(1), 
                                rs.getString(2), 
                                rs.getInt(3))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enrollmentCountDTOs;
    }
}
