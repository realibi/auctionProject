package kz.itstep.dao;

import kz.itstep.entity.Item;
import kz.itstep.entity.Sale;
import kz.itstep.pool.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ItemDao extends AbstractDao<Item> {
    private static final String SQL_SELECT_ITEMS_ALL = "SELECT * FROM public.items";
    private static final String SQL_SELECT_ITEMS_AVAILABLE = "SELECT * FROM public.items where end_time>?";
    private static final String SQL_SELECT_ITEM_BY_USER_ID = "SELECT * FROM public.items where user_id=?";
    private static final String SQL_INSERT_ITEM =
            "insert into public.items (name, category_id, start_price, current_price, start_time, end_time, user_id, last_updated_user_id) values(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ITEM_BY_ID = "DELETE FROM public.items where id=?";
    private static final String SQL_SELECT_ITEM_BY_ID = "SELECT * FROM public.items where id=?";
    private static final String SQL_UPDATE_ITEM_CURRENT_PRICE_BY_ID = "UPDATE public.items SET current_price=?, last_updated_user_id=? where id=?";
    private static final String SQL_UPDATE_ITEM_USER_ID_BY_ID = "UPDATE public.items SET user_id=? where id=?";
    private static final String SQL_UPDATE_ITEM_DATES_BY_ID = "UPDATE public.items SET start_date=?, end_date=? where id=?";

    public List<Item> getItemsByUserId(int id){
        List<Item> items = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM_BY_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = setItemParameters(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return items;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ITEMS_ALL)) {
            while (resultSet.next()) {
                Item item = setItemParameters(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return items;
    }

    public List<Item> findAllAvailableItems() {
        List<Item> items = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEMS_AVAILABLE)) {
            Timestamp currentDate = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            System.out.println(currentDate);

            preparedStatement.setTimestamp(1, currentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = setItemParameters(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    item = setItemParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return item;
    }

    @Override
    public boolean insert(Item entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ITEM)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getCategoryId());
            preparedStatement.setInt(3, entity.getStartPrice());
            preparedStatement.setInt(4, entity.getCurrentPrice());
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
            preparedStatement.setTimestamp(6, entity.getEndTime());
            preparedStatement.setInt(7, entity.getUserId());
            preparedStatement.setInt(8, entity.getLastUpdatedUserId());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            System.out.println("item wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(Item entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try (PreparedStatement updateItem = connection.prepareStatement(SQL_UPDATE_ITEM_USER_ID_BY_ID)) {
            updateItem.setInt(1, entity.getUserId());
            updateItem.setInt(2, entity.getId());

            SaleDao saleDao = new SaleDao();
            Sale sale = new Sale();
            sale.setItemId(entity.getId());
            sale.setUserId(entity.getLastUpdatedUserId());
            sale.setTime(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
            saleDao.insert(sale);

            updateItem.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            System.out.println("item wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }

        return updated;
    }

    public boolean sendToAuction(int id){
        int days = 7;
        Calendar newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_MONTH, days);

        boolean updated = false;

        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ITEM_CURRENT_PRICE_BY_ID)) {
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(newDate.getTime().getTime()));
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            System.out.println("item wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }

        return updated;
    }

    public boolean updateItemCurrentPrice(Item item, int newPrice, int userId){
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        if(item != null && item.getCurrentPrice() < newPrice){
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ITEM_CURRENT_PRICE_BY_ID)) {
                preparedStatement.setInt(1, newPrice);
                preparedStatement.setInt(2, userId);
                preparedStatement.setInt(3, item.getId());
                preparedStatement.executeUpdate();
                updated = true;
            } catch (SQLException e) {
                System.out.println("item wasn't inserted!" + e.getMessage());
            } finally {
                ConnectionPool.getConnectionPool().releaseConnection(connection);
            }
        }

        return updated;
    }

    @Override
    public boolean delete(Item entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ITEM_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    public void updateItems(){
        List<Item> items = findAll();
        Timestamp currentDate = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        for(Item item: items){
            if(item.getEndTime().before(currentDate)){
                item.setUserId(item.getLastUpdatedUserId());
                update(item);
            }
        }
    }

    private Item setItemParameters(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setName(resultSet.getString("name"));
        item.setCategoryId(resultSet.getInt("category_id"));
        item.setStartPrice(resultSet.getInt("start_price"));
        item.setCurrentPrice(resultSet.getInt("current_price"));
        item.setStartTime(resultSet.getTimestamp("start_time"));
        item.setEndTime(resultSet.getTimestamp("end_time"));
        item.setUserId(resultSet.getInt("user_id"));
        item.setLastUpdatedUserId(resultSet.getInt("last_updated_user_id"));
        return item;
    }
}
