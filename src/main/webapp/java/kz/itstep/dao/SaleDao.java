package kz.itstep.dao;

import kz.itstep.entity.Item;
import kz.itstep.entity.Sale;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SaleDao extends AbstractDao<Sale> {
    private static final String SQL_INSERT_SALE = "insert into public.sales (item_id, user_id, time) values(?, ?, ?)";
    private static final String SQL_SELECT_SALES = "select * from public.sales";
    private static final String SQL_SELECT_USER_SALES_LIMIT_5 = "select * from public.sales where user_id=? limit 5";

    @Override
    public List<Sale> findAll() {
        return null;
    }

    public List<Sale> getUserTopFive(int userId){
        List<Sale> sales = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_SALES_LIMIT_5)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Sale sale = setSaleParameters(resultSet);
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return sales;
    }

    private Sale setSaleParameters(ResultSet resultSet) throws SQLException{
        Sale sale = new Sale();
        sale.setId(resultSet.getInt("id"));
        sale.setItemId(resultSet.getInt("item_id"));
        sale.setUserId(resultSet.getInt("user_id"));
        sale.setTime(resultSet.getTimestamp("time"));
        return sale;
    }

    @Override
    public Sale findById(int id) {
        return null;
    }

    @Override
    public boolean insert(Sale entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try (PreparedStatement insertSales = connection.prepareStatement(SQL_INSERT_SALE)) {
            insertSales.setInt(1, entity.getItemId());
            insertSales.setInt(2, entity.getUserId());
            insertSales.setTimestamp(3, entity.getTime());
            insertSales.executeQuery();
            updated = true;
        } catch (SQLException e) {
            System.out.println("sale wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }

        return updated;
    }

    @Override
    public boolean update(Sale entity) {
        return false;
    }

    @Override
    public boolean delete(Sale entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
