package hk.gavin.navik.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import hk.gavin.navik.R;
import hk.gavin.navik.activity.NavigationActivity;
import hk.gavin.navik.map.NavikMapFragment;

public class NavigationFragment extends Fragment {

    NavikMapFragment mNavikMapFragment;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((NavigationActivity) getActivity()).component().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mNavikMapFragment = (NavikMapFragment) getFragmentManager().findFragmentById(R.id.navikMap);
    }
}