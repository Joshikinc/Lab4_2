package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletContext;

@WebServlet("/editGame")
public class EditGame extends HttpServlet {
	private static final long serialVersionUID = 4L;
	Connection connection;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = getServletContext();

		try {
			// Загружаем драйвер PostgreSQL и устанавливаем соединение с базой данных
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/games_database", "josh", "123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Получаем параметр запроса (индекс игры) и формируем запрос на выборку данных игры
		String query = String.format("select * from games where game_index=" + request.getParameter("id"));
		try {
			// Создаем и выполняем SQL-запрос
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				// Создаем объект Game из данных игры и добавляем его в атрибуты запроса
				Game tempGame = new Game(resultSet.getString("name"), resultSet.getString("genre"),
						resultSet.getString("platform"), resultSet.getInt("year"), resultSet.getInt("price"),
						resultSet.getInt("game_index"));
				request.setAttribute("game", tempGame);
			}
			// Перенаправляем на страницу редактирования игры
			sc.getRequestDispatcher("/jsp/edit.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
