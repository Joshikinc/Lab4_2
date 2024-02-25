package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.jdbc.Driver;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	private Connection connection;
	private static final long serialVersionUID = 1L;

	// Метод обработки GET-запросов
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		sc.getRequestDispatcher("/jsp/write.jsp").forward(req, resp);
	}

	// Метод обработки POST-запросов
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Загружаем драйвер PostgreSQL и устанавливаем соединение с базой данных
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/games_database", "josh", "123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Создаем объект Gson для преобразования JSON в объект Java
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// Читаем JSON-данные из запроса и преобразуем их в объект Game
		String data = request.getReader().readLine();
		Game game = gson.fromJson(data, Game.class);
		// Формируем SQL-запрос на добавление новой игры
		String query = String.format("insert into games(name, genre, platform, year, price) values ('%s','%s','%s','%d','%d')", game.getName(), game.getGenre(), game.getPlatform(), game.getYear(), game.getPrice());
		try {
			// Создаем и выполняем SQL-запрос
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
