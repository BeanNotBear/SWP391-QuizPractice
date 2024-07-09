package dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import context.DBContext;
import dto.PricePackageDTO;
import dto.SubjectPackagePriceDTO;
import java.sql.SQLException;

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
            ps.setInt(2,pricePackageDTO.getDuration());
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
}
