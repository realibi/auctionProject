package kz.itstep.dao;

import kz.itstep.entity.CourseEntity;
import kz.itstep.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends AbstractDao<CourseEntity>{
    private static final String SQL_SELECT_COURSES  = "SELECT * FROM public.courses";
    private static final String SQL_SELECT_COURSE_BY_ID = "SELECT * FROM public.courses where id=?";
    private static final String SQL_DELETE_COURSE_BY_ID  = "DELETE FROM public.courses where id=?";

    @Override
    public List<CourseEntity> findAll() {
        List<CourseEntity> courses = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_COURSES)){
            while (resultSet.next()){
                CourseEntity course = new CourseEntity();
                course.setId(resultSet.getInt("id"));
                course.setTitle(resultSet.getString("title"));
                course.setDescription(resultSet.getString("description"));
                course.setPrice(resultSet.getBigDecimal("price"));
                course.setStartDate(resultSet.getDate("start_date"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return courses;
    }

    @Override
    public CourseEntity findById(int id) {
        CourseEntity course = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_BY_ID)){
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    course = new CourseEntity();
                    course.setId(resultSet.getInt("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setDescription(resultSet.getString("description"));
                    course.setPrice(resultSet.getBigDecimal("price"));
                    course.setStartDate(resultSet.getDate("start_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return course;
    }

    @Override
    public boolean insert(CourseEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(CourseEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(CourseEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURSE_BY_ID)) {
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
}
