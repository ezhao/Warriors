package com.herokuapp.ezhao.warriors;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WarriorsClient {
    private static final String API_BASE_URL = "http://api.sportradar.us/nba-t3/games/";
    private static final String CLIENT_KEY = "ntuzstafpyxetmg399ay88n8";
    private AsyncHttpClient client;

    public WarriorsClient() {
        this.client = new AsyncHttpClient();
    }

    private String getAPIScheduleURL(Date date) {
        // TODO(emily) still appears to be off by a day or something, timezone related maybe
        GregorianCalendar cDate = new GregorianCalendar();
        cDate.setTimeInMillis(date.getTime());
        int year = cDate.get(Calendar.YEAR);
        int month = cDate.get(Calendar.MONTH) + 1;
        int day = cDate.get(Calendar.DAY_OF_MONTH) + 1;
        return String.format("%s%s/%s/%s/schedule.json?api_key=%s", API_BASE_URL, year, month, day, CLIENT_KEY);
    }

    private String getAPIGameScoreURL(String gameString) {
        return String.format("%s%s/boxscore.json?api_key=%s", API_BASE_URL, gameString, CLIENT_KEY);
    }

    public void getAPISchedule(Date date, JsonHttpResponseHandler handler) {
        String url = getAPIScheduleURL(date);
        client.get(url, handler);
    }

    public void getAPIGameScore(String gameString, JsonHttpResponseHandler handler) {
        String url = getAPIGameScoreURL(gameString);
        client.get(url, handler);
    }
}
