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
                + "INNER JOIN package_price AS pp ON sr.PackageId = pp.id\n"
                + "WHERE sr.[status] = 'done'";
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
        String query = "SELECT COUNT(*)\n"
                + "FROM [SWP391_G6].[dbo].[Subject_Register] AS sr\n"
                + "WHERE Status = 'done'";
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

    public int getNumberOfUnprocessedEnrollments() {
        String query = "SELECT COUNT(*)\n"
                + "FROM [SWP391_G6].[dbo].[Subject_Register] AS sr\n"
                + "WHERE Status = 'pending'";
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
                + "INNER JOIN package_price AS pp ON sr.PackageId = pp.id\n"
                + "WHERE sr.[status] = 'done'";
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

    public String getNumberRegisterAsJson() {
        String query = "WITH Months AS\n"
                + "  (SELECT 1 AS MONTH\n"
                + "   UNION ALL SELECT MONTH + 1\n"
                + "   FROM Months\n"
                + "   WHERE MONTH < 12 )\n"
                + "SELECT m.Month,\n"
                + "       COUNT(sr.PackageId) AS RegisterCount\n"
                + "FROM Months AS m\n"
                + "LEFT JOIN Subject_Register AS sr ON MONTH(sr.CreatedAt) = m.Month\n"
                + "AND YEAR(sr.CreatedAt) = YEAR(GETDATE())\n"
                + "LEFT JOIN package_price AS pp ON pp.id = sr.PackageId\n"
                + "GROUP BY m.Month\n"
                + "ORDER BY m.Month\n"
                + "FOR JSON AUTO";
        String jsonData = new String("");
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                jsonData = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public String getProfitEachMonthAsJson() {
        String query = "WITH Months AS (\n"
                + "    SELECT 1 AS Month\n"
                + "    UNION ALL\n"
                + "    SELECT Month + 1\n"
                + "    FROM Months\n"
                + "    WHERE Month < 12\n"
                + ")\n"
                + "SELECT \n"
                + "    m.Month,\n"
                + "    ISNULL(SUM(pp.sale_price) - SUM(pp.original_price), 0) AS Profit\n"
                + "FROM \n"
                + "    Months AS m\n"
                + "LEFT JOIN \n"
                + "    Subject_Register AS sr ON MONTH(sr.CreatedAt) = m.Month\n"
                + "    AND YEAR(sr.CreatedAt) = YEAR(GETDATE()) AND sr.Status = 'done'\n"
                + "LEFT JOIN \n"
                + "    package_price AS pp ON pp.id = sr.PackageId\n"
                + "GROUP BY \n"
                + "    m.Month\n"
                + "ORDER BY \n"
                + "    m.Month\n"
                + "FOR JSON AUTO";
        String jsonData = new String("");
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                jsonData = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public String getRevenueEachMonthAsJson() {
        String query = "WITH Months AS (\n"
                + "    SELECT 1 AS Month\n"
                + "    UNION ALL\n"
                + "    SELECT Month + 1\n"
                + "    FROM Months\n"
                + "    WHERE Month < 12\n"
                + ")\n"
                + "SELECT \n"
                + "    m.Month,\n"
                + "    ISNULL(SUM(pp.sale_price), 0) AS Revenue\n"
                + "FROM \n"
                + "    Months AS m\n"
                + "LEFT JOIN \n"
                + "    Subject_Register AS sr ON MONTH(sr.CreatedAt) = m.Month\n"
                + "    AND YEAR(sr.CreatedAt) = YEAR(GETDATE()) AND sr.Status = 'done'\n"
                + "LEFT JOIN \n"
                + "    package_price AS pp ON pp.id = sr.PackageId\n"
                + "GROUP BY \n"
                + "    m.Month\n"
                + "ORDER BY \n"
                + "    m.Month\n"
                + "FOR JSON AUTO";
        String jsonData = new String("");
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                jsonData = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public double getRevenuePerCustomer() {
        String query = "DECLARE @NumberOfCustomer INT\n"
                + "\n"
                + "SELECT @NumberOfCustomer = COUNT(*)\n"
                + "FROM users\n"
                + "WHERE role_id = 1\n"
                + "\n"
                + "SELECT SUM(pp.sale_price) / @NumberOfCustomer AS TotalRevenue\n"
                + "FROM [SWP391_G6].[dbo].[Subject_Register] AS sr\n"
                + "INNER JOIN package_price AS pp ON sr.PackageId = pp.id\n"
                + "WHERE sr.[status] = 'done'";
        double result = 0.0d;
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

    public int getNumberOfUser() {
        String query = "SELECT COUNT(*)\n"
                + "FROM users";
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
}
