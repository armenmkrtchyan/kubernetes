package com.synisys.armen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author armen.mkrtchyan
 */
@Repository
public class VitessRepository {

	private AtomicInteger idGenerator;
	@Value("${db.host}")
	private String dbHost;
	@Value("${db.port}")
	private String dbPort;
	@Value("${db.initialId}")
	private Integer dbInitialId;
	private String dbUrl;

	@PostConstruct
	private void init() {
		dbUrl = String.format("jdbc:vitess://%s:%s/test_keyspace/unused", dbHost, dbPort);
		idGenerator = new AtomicInteger(dbInitialId);
	}

	@Nonnull
	public Connection createConnection()
			throws SQLException {
		try {
			Class.forName("com.flipkart.vitess.jdbc.VitessDriver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(dbUrl, null);
	}

	public void insertProject(@Nonnull String name) {
		String query = "INSERT INTO project (time_created_ns, name) VALUES ( ?, ?)";
		try (Connection con = createConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
//			preparedStatement.setInt(1, idGenerator.incrementAndGet());
			preparedStatement.setLong(1, System.nanoTime());
			preparedStatement.setString(2, name);
			preparedStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteAllProject() {
		String query = "delete from project ";
		try (Connection con = createConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
