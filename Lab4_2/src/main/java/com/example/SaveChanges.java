package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletContext;

@WebServlet("/saveChanges")
public class SaveChanges extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = getServletContext();
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
		// Формируем SQL-запрос на обновление данных игры
		String query = String.format("update games set name='%s', genre='%s', platform='%s', year='%d', price='%d' where game_index='%d'",
				game.getName(), game.getGenre(), game.getPlatform(), game.getYear(), game.getPrice(), game.getIdInDatabase());
		try {
			// Создаем и выполняем SQL-запрос
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Перенаправляем на страницу чтения игр
		sc.getRequestDispatcher("/jsp/read.jsp").forward(request, response);
	}

}
