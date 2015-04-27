package com.herokuapp.ezhao.warriors.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Game {
    private static final String WARRIORS_ID = "GSW";
    String id;
    String title;
    String homeTeam;
    String awayTeam;
    int homePoints;
    int awayPoints;

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(int homePoints) {
        this.homePoints = homePoints;
    }

    public int getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(int awayPoints) {
        this.awayPoints = awayPoints;
    }

    public static Game fromJson(JSONObject jsonObject) {
        Game game = new Game();
        try {
            game.setId(jsonObject.getString("id"));
            game.setTitle(jsonObject.getString("title"));
            JSONObject homeTeam = jsonObject.getJSONObject("home");
            JSONObject awayTeam = jsonObject.getJSONObject("away");
            game.setHomeTeam(homeTeam.getString("name"));
            game.setAwayTeam(awayTeam.getString("name"));
            game.setHomePoints(homeTeam.getInt("points"));
            game.setAwayPoints(awayTeam.getInt("points"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return game;
    }

    public static String gameIdFromScheduleJson(JSONObject scheduleJson) {
        try {
            JSONArray jsonGames = scheduleJson.getJSONArray("games");
            for (int i = 0; i < jsonGames.length(); i++) {
                JSONObject jsonGame = jsonGames.getJSONObject(i);
                String homeTeam = jsonGame.getJSONObject("home").getString("alias");
                String awayTeam = jsonGame.getJSONObject("away").getString("alias");
                if (homeTeam.equals(WARRIORS_ID) || awayTeam.equals(WARRIORS_ID)) {
                    return jsonGame.getString("id");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
