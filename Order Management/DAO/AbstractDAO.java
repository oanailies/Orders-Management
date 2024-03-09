/**
 * The AbstractDAO class provides generic database operations for a specific entity type.
 * @param <T> the type of the entity
 */
package org.tpIliesOana.dao;

import org.tpIliesOana.connection.ConnectionFactory;

import java.sql.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructs a new AbstractDAO instance.
     */

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Retrieves the names of all fields in the entity class.
     * @return an array of field names
     */
    public String[] getFieldNames() {
        int count = 0;
        Field[] fields = type.getDeclaredFields();
        String[] fieldNames = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fieldNames[count++] = fields[i].getName();
        }

        return fieldNames;
    }


    /**
     * Creates the SQL insert query for the entity.
     * @return the insert query string
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        for (Field f : type.getDeclaredFields()) {
            if (!f.equals(type.getDeclaredFields()[0]))
                sb.append(f.getName() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES (?, ?, ?, ?)");
        return sb.toString();
    }

    /**
     * Creates the SQL update query for the entity.
     * @return the update query string
     */
    private String createUpdateQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE ");
        queryBuilder.append(type.getSimpleName());
        queryBuilder.append(" SET ");

        Field[] fields = type.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (i != 0) {
                queryBuilder.append(fields[i].getName());
                queryBuilder.append("=?, ");
            }
        }

        queryBuilder.deleteCharAt(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE ");
        queryBuilder.append("ID = ?");

        return queryBuilder.toString();
    }
    /**
     * Creates the SQL select query for a specific field.
     * @param field the field to search by
     * @return the select query string
     */
    private String createSelectQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field + " =? ");

        return stringBuilder.toString();
    }

    /**
     * Creates the SQL select query to retrieve all entities.
     * @return the select query string
     */
    private String createSelectQueryAll() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM ");
        queryBuilder.append(type.getSimpleName());
        return queryBuilder.toString();
    }
    /**
     * Creates the SQL delete query for an entity.
     * @return to delete query string
     */

    private String createDeleteQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("DELETE FROM ");
        queryBuilder.append(type.getSimpleName());
        queryBuilder.append(" WHERE ");
        queryBuilder.append(type.getSimpleName().toLowerCase());
        queryBuilder.append("ID = ?");
        return queryBuilder.toString();
    }

    /**
     * Creates a deleted query for the object's class based on the ID field.
     *
     * @return To delete query as a string.
     */

    private String createDeleteOrdersQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("DELETE FROM ");
        queryBuilder.append(type.getSimpleName());
        queryBuilder.append(" WHERE ID = ?");
        return queryBuilder.toString();
    }

    /**
     * Deletes an object from the database by its ID.
     *
     * @param id The ID of the object to delete.
     */
    public void delete(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteOrdersStatement = null;
        PreparedStatement deleteProductStatement = null;
        try {
            deleteOrdersStatement = dbConnection.prepareStatement(createDeleteOrdersQuery(), Statement.NO_GENERATED_KEYS);
            deleteOrdersStatement.setInt(1, id);
            deleteOrdersStatement.executeUpdate();
            deleteProductStatement = dbConnection.prepareStatement(createDeleteQuery(), Statement.NO_GENERATED_KEYS);
            deleteProductStatement.setInt(1, id);
            deleteProductStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO DELETE!" + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteProductStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * Finds an object in the database by its ID.
     *
     * @param id The ID of the object to find.
     * @return The found object, or null if not found.
     */

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("ID");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO FIND BY ID! " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates objects from the ResultSet obtained from a database query.
     *
     * @param resultSet The ResultSet containing the query results.
     * @return A list of created objects.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);

                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    } catch (IntrospectionException e) {
                        continue;
                    }
                }

                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            LOGGER.log(Level.WARNING, "Error occurred while creating objects: " + e.getMessage());
        }

        return list;
    }

    /**
     * Converts a list of objects into a two-dimensional array of strings.
     *
     * @param objects The list of objects to convert.
     * @return The converted array of strings.
     */
    public String[][] convertListOfObjectsToArray(List<T> objects) {
        if (objects.isEmpty()) {
            return new String[0][0];
        }

        int rows = objects.size();
        int columns = objects.get(0).getClass().getDeclaredFields().length;
        String[][] array = new String[rows][columns];

        int rowIndex = 0;
        int columnIndex = 0;

        for (T object : objects) {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                try {
                    Object value = field.get(object);
                    array[rowIndex][columnIndex++] = String.valueOf(value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            rowIndex++;
            columnIndex = 0;
        }

        return array;
    }


    /**
     * Inserts an object into the database.
     *
     * @param t The object to insert.
     * @return The inserted object.
     */
    public T insert(T t) {
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement insertStatement = null;
        int i = 1;
        try {
            insertStatement = dbConnection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            for (Field f : type.getDeclaredFields()) {
                if (!f.equals(type.getDeclaredFields()[0])) {
                    f.setAccessible(true);
                    Object obj = f.get(t);
                    insertStatement.setString(i, obj.toString());
                    i++;
                }

            }
            insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                Field f = type.getDeclaredField( "ID");
                f.setAccessible(true);
                f.set(t, id);
            }
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }



    /**
     * Retrieves all objects of the specified type from the database.
     *
     * @return A list of all objects found in the database, or null if an error occurred.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQueryAll();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error occurred while finding all objects: " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Updates an object in the database with the specified ID.
     *
     * @param object The object to update in the database.
     * @param id     The ID of the object to update.
     * @return The updated object if the update was successful, or null if an error occurred.
     */
    public T update(T object, int id) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int parameterIndex = 1;

        try {
            updateStatement = connection.prepareStatement(createUpdateQuery(), Statement.NO_GENERATED_KEYS);

            for (Field field : type.getDeclaredFields()) {
                if (!field.equals(type.getDeclaredFields()[0])) {
                    field.setAccessible(true);
                    Object value = field.get(object);
                    updateStatement.setString(parameterIndex, String.valueOf(value));
                    parameterIndex++;
                }
            }

            updateStatement.setString(parameterIndex, String.valueOf(id));
            updateStatement.executeUpdate();

            return object;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error occurred while updating object: " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }

        return null;
    }




}
