package dal;

import context.DBContext;

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
}
