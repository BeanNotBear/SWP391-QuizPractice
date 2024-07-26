package dal;

import context.DBContext;
import dto.RegistrationDTO;
import dto.SubjectPackagePriceDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PricePackageDAO extends DBContext {

    public static PricePackageDAO instance = null;
    public static Object lockPad = new Object();

    private PricePackageDAO() {
    }

    public static PricePackageDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new PricePackageDAO();
                }
            }
        }
        return instance;
    }

    public int getNumberOfPricePackagesBySubjectId(int subjectId) {
        String query = "SELECT COUNT(price_package_id)\n"
                + "FROM [SWP391_G6].[dbo].[subject_has_price_package]\n"
                + "WHERE subject_id = ?";
        int numberOfPricePackages = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                numberOfPricePackages = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfPricePackages;
    }

    public boolean insert(SubjectPackagePriceDTO pricePackageDTO) {
        boolean isSuccess = false;
        String query = "INSERT INTO [dbo].[package_price]\n"
                + "           ([name]\n"
                + "           ,[duration]\n"
                + "           ,[sale_price]\n"
                + "           ,[price]\n"
                + "           ,[original_price])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pricePackageDTO.getPackageName());
            ps.setInt(2, pricePackageDTO.getDuration());
            ps.setDouble(3, pricePackageDTO.getSalePrice());
            ps.setDouble(4, pricePackageDTO.getPrice());
            ps.setDouble(5, pricePackageDTO.getOriginalPrice());
            ps.executeUpdate();
            ps.close();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean addSubjectPricePackage(int packageId, int subjectId) {
        boolean updated = false;
        String query = "insert into subject_has_price_package values(?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectId);
            ps.setInt(2, packageId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                updated = true;
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle exception properly in a production environment
        }
        return updated;
    }

    public boolean deleteSubjectPricePackage(int packageId, int subjectId) {
        boolean updated = false;
        String query = "delete from subject_has_price_package where subject_id=? and price_package_id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectId);
            ps.setInt(2, packageId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                updated = true;
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle exception properly in a production environment
        }
        return updated;
    }

    public boolean UpdatePricePackageById(SubjectPackagePriceDTO pricePackageDTO) {
        boolean isSuccess = false;
        String query = "UPDATE [dbo].[package_price]\n"
                + "   SET [name] = ?\n"
                + "      ,[duration] = ?\n"
                + "      ,[sale_price] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[original_price] = ?\n"
                + " WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(6, pricePackageDTO.getId());
            ps.setString(1, pricePackageDTO.getPackageName());
            ps.setInt(2, pricePackageDTO.getDuration());
            ps.setDouble(3, pricePackageDTO.getSalePrice());
            ps.setDouble(4, pricePackageDTO.getPrice());
            ps.setDouble(5, pricePackageDTO.getOriginalPrice());
            ps.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<RegistrationDTO> getRegistrationListPagination(int page, int recordsPerPage, String search) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("DECLARE @StartRow INT = ?;\n")
                .append("DECLARE @EndRow INT = ?;\n")
                .append("DECLARE @SearchTerm NVARCHAR(100) = ?;\n")
                .append("\n")
                .append("WITH PaginatedResults AS (\n")
                .append("    SELECT sr.UserId, \n")
                .append("           u.full_name,\n")
                .append("           CASE \n")
                .append("               WHEN u.gender = 1 THEN 'Male' \n")
                .append("               ELSE 'Female' \n")
                .append("           END AS gender,\n")
                .append("           u.email,\n")
                .append("           u.phone_number,\n")
                .append("           sr.SubjectId, \n")
                .append("           s.name AS subject_name,\n")
                .append("           sr.PackageId,\n")
                .append("           pp.name AS package_name,\n")
                .append("           pp.sale_price,\n")
                .append("           CAST(pp.duration AS VARCHAR(10)) + ' ' + \n")
                .append("           CASE \n")
                .append("               WHEN pp.duration > 1 THEN 'months' \n")
                .append("               ELSE 'month' \n")
                .append("           END AS duration,\n")
                .append("           sr.Status,\n")
                .append("           ROW_NUMBER() OVER (ORDER BY sr.UserId) AS RowNum,\n")
                .append("           u.profile_img\n")
                .append("    FROM [SWP391_G6].[dbo].[Subject_Register] AS sr\n")
                .append("    INNER JOIN users AS u ON sr.UserId = u.id\n")
                .append("    INNER JOIN subjects AS s ON sr.SubjectId = s.id\n")
                .append("    INNER JOIN [dbo].[package_price] AS pp ON sr.PackageId = pp.id\n")
                .append("    WHERE \n")
                .append("           CAST(sr.UserId AS NVARCHAR) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR u.full_name LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR CASE WHEN u.gender = 1 THEN 'Male' ELSE 'Female' END LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR u.email LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR u.phone_number LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR CAST(sr.SubjectId AS NVARCHAR) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR s.name LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR CAST(sr.PackageId AS NVARCHAR) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR pp.name LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR CAST(pp.sale_price AS NVARCHAR) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR (CAST(pp.duration AS VARCHAR(10)) + ' ' + \n")
                .append("           CASE \n")
                .append("               WHEN pp.duration > 1 THEN 'months' \n")
                .append("               ELSE 'month' \n")
                .append("           END) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR sr.Status LIKE '%' + @SearchTerm + '%'\n")
                .append(")\n")
                .append("SELECT *\n")
                .append("FROM PaginatedResults\n")
                .append("WHERE RowNum BETWEEN @StartRow AND @EndRow;");

        String queryString = queryBuilder.toString();

        int start = (page - 1) * recordsPerPage + 1;
        int end = start + recordsPerPage - 1;
        List<RegistrationDTO> registrationDTOs = new ArrayList<>();
        try {
            ps = connection.prepareStatement(queryString);
            ps.setInt(1, start);
            ps.setInt(2, end);
            ps.setString(3, search);
            rs = ps.executeQuery();

            while (rs.next()) {
                registrationDTOs.add(
                        new RegistrationDTO(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6),
                                rs.getString(7),
                                rs.getInt(8),
                                rs.getString(9),
                                rs.getDouble(10),
                                rs.getString(11),
                                rs.getString(12),
                                rs.getString(14)
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return registrationDTOs;
    }

    public int getNumberOfPageRegistration(String search) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("DECLARE @SearchTerm NVARCHAR(100) = ?;\n")
                .append("\n")
                .append("WITH PaginatedResults AS (\n")
                .append("    SELECT sr.UserId, \n")
                .append("           u.full_name,\n")
                .append("           CASE \n")
                .append("               WHEN u.gender = 1 THEN 'Male' \n")
                .append("               ELSE 'Female' \n")
                .append("           END AS gender,\n")
                .append("           u.email,\n")
                .append("           u.phone_number,\n")
                .append("           sr.SubjectId, \n")
                .append("           s.name AS subject_name,\n")
                .append("           sr.PackageId,\n")
                .append("           pp.name AS package_name,\n")
                .append("           pp.sale_price,\n")
                .append("           CAST(pp.duration AS VARCHAR(10)) + ' ' + \n")
                .append("           CASE \n")
                .append("               WHEN pp.duration > 1 THEN 'months' \n")
                .append("               ELSE 'month' \n")
                .append("           END AS duration,\n")
                .append("           sr.Status,\n")
                .append("           u.profile_img,\n")
                .append("           ROW_NUMBER() OVER (ORDER BY sr.UserId) AS RowNum\n")
                .append("    FROM [SWP391_G6].[dbo].[Subject_Register] AS sr\n")
                .append("    INNER JOIN users AS u ON sr.UserId = u.id\n")
                .append("    INNER JOIN subjects AS s ON sr.SubjectId = s.id\n")
                .append("    INNER JOIN [dbo].[package_price] AS pp ON sr.PackageId = pp.id\n")
                .append("    WHERE \n")
                .append("           CAST(sr.UserId AS NVARCHAR) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR u.full_name LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR CASE WHEN u.gender = 1 THEN 'Male' ELSE 'Female' END LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR u.email LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR u.phone_number LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR CAST(sr.SubjectId AS NVARCHAR) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR s.name LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR CAST(sr.PackageId AS NVARCHAR) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR pp.name LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR CAST(pp.sale_price AS NVARCHAR) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR (CAST(pp.duration AS VARCHAR(10)) + ' ' + \n")
                .append("           CASE \n")
                .append("               WHEN pp.duration > 1 THEN 'months' \n")
                .append("               ELSE 'month' \n")
                .append("           END) LIKE '%' + @SearchTerm + '%'\n")
                .append("       OR sr.Status LIKE '%' + @SearchTerm + '%'\n")
                .append(")\n")
                .append("SELECT CEILING(CAST(COUNT(*) AS FLOAT) / 5) AS NumberOfPage\n")
                .append("FROM PaginatedResults;");

        String queryString = queryBuilder.toString();
        int numberOfPages = 0;
        try {
            ps = connection.prepareStatement(queryString);
            ps.setString(1, search);
            rs = ps.executeQuery();
            if (rs.next()) {
                numberOfPages = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfPages;
    }
}
