package com.herokuapp.ezhao.warriors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

public class ScoreTodayFragment extends Fragment {
    private Date date;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_today, container, false);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvDate.setText(date.toString());
        return view;
    }
}
