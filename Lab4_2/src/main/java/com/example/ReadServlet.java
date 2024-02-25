package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/read")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private Connection connection;

	// При GET-запросе перенаправляем на страницу чтения игр
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		sc.getRequestDispatcher("/jsp/read.jsp").forward(req, resp);
	}

	// При POST-запросе возвращаем список игр в формате JSON
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Загружаем драйвер PostgreSQL и устанавливаем соединение с базой данных
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/games_database", "josh", "123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Устанавливаем тип содержимого как JSON
		response.setContentType("application/json");
		// Создаем объект Gson для преобразования списка игр в JSON
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String query = String.format("select * from games");
		ArrayList<Game> games = new ArrayList<>();
		try {
			// Создаем и выполняем SQL-запрос для получения списка игр
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				// Создаем объект Game из данных игры и добавляем его в список игр
				Game tempGame = new Game(resultSet.getString("name"), resultSet.getString("genre"),
						resultSet.getString("platform"), resultSet.getInt("year"), resultSet.getInt("price"),
						resultSet.getInt("game_index"));
				games.add(tempGame);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Преобразуем список игр в JSON и отправляем клиенту
		PrintWriter out = response.getWriter();
		String jsonArrayString = "";
		if (games.size() != 0) {
			jsonArrayString = gson.toJson(games);
		}
		out.print(jsonArrayString);
		out.close();
	}
}
