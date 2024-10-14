package exercise.repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStmt.setString(1, product.getTitle());
            preparedStmt.setInt(2, product.getPrice());
            preparedStmt.executeUpdate();
            ResultSet entriesId = preparedStmt.getGeneratedKeys();
            if (entriesId.next()) {
                Long id = entriesId.getLong(1);
                product.setId(id);
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id=?";
        try (var conn = dataSource.getConnection();
             var preparedStmt = conn.prepareStatement(sql)) {
            preparedStmt.setLong(1, id);
            ResultSet entries = preparedStmt.executeQuery();
            if (entries.next()) {
                String title = entries.getString("title");
                int price = entries.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }

    public static List<Product> getEntities() throws SQLException {
        String sql = "SELECT * FROM products";
        try (var conn = dataSource.getConnection();
             var preparedStmt = conn.prepareStatement(sql)) {
            ResultSet entries = preparedStmt.executeQuery();
            List<Product> result = new ArrayList<>();
            if (entries.next()) {
                String title = entries.getString("title");
                int price = entries.getInt("price");
                Long id = entries.getLong("id");
                Product product = new Product(title, price);
                product.setId(id);
                result.add(product);
            }
            return result;
        }
    }
    // END
}