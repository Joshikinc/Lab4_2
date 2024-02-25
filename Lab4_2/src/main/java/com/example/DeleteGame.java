package com.example;

import com.mysql.cj.jdbc.Driver;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/deleteGame")
public class DeleteGame extends HttpServlet {
	private static final long serialVersionUID = 3L;
	private Connection connection;

	// При GET-запросе перенаправляем на страницу чтения игр
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		sc.getRequestDispatcher("/jsp/read.jsp").forward(req, resp);
	}

	// При POST-запросе удаляем игру из базы данных
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try {
			// Загружаем драйвер PostgreSQL и устанавливаем соединение с базой данных
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/games_database", "josh", "123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Получаем параметр запроса (индекс игры) и формируем запрос на удаление
		String query = String.format("delete from games where game_index='" + request.getReader().readLine() + "'");
		try {
			// Создаем и выполняем SQL-запрос
			Statement statement = connection.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Перенаправляем на страницу записи игр
		response.sendRedirect("jsp/write.jsp");
	}
}
