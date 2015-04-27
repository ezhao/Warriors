package com.herokuapp.ezhao.warriors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.herokuapp.ezhao.warriors.models.Game;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONObject;
import java.util.Date;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ScoreTodayFragment extends Fragment {
    private Date date;
    private WarriorsClient warriorsClient;
    @InjectView(R.id.tvDate) TextView tvDate;
    @InjectView(R.id.tvHomeTeam) TextView tvHomeTeam;
    @InjectView(R.id.tvHomeScore) TextView tvHomeScore;
    @InjectView(R.id.tvAwayTeam) TextView tvAwayTeam;
    @InjectView(R.id.tvAwayScore) TextView tvAwayScore;

    public static ScoreTodayFragment newInstance(Date date) {
        ScoreTodayFragment scoreTodayFragment = new ScoreTodayFragment();
        Bundle args = new Bundle();
        args.putSerializable("date", date);
        scoreTodayFragment.setArguments(args);
        return scoreTodayFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = (Date) getArguments().getSerializable("date");
        warriorsClient = new WarriorsClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_today, container, false);
        ButterKnife.inject(this, view);

        tvDate.setText(date.toString());
        warriorsClient.getAPISchedule(date, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String gameId = Game.gameIdFromScheduleJson(response);
                onReceiveGameId(gameId);
            }
        });
        return view;
    }

    public void onReceiveGameId(String gameId) {
        if (gameId == null) {
            tvHomeTeam.setText("No game today");
            return;
        }
        Log.i("EMILY", gameId);

        // TODO(emily) add a spinner or something
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        warriorsClient.getAPIGameScore(gameId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Game game = Game.fromJson(response);
                tvHomeTeam.setText(game.getHomeTeam());
                tvHomeScore.setText(String.valueOf(game.getHomePoints()));
                tvAwayTeam.setText(game.getAwayTeam());
                tvAwayScore.setText(String.valueOf(game.getAwayPoints()));
            }
        });
    }
}
