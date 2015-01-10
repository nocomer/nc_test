package cn.com.jdsc;

import cn.com.jdsc.R;
import nc_study.SimulationTestMainActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PracticeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("practiceFragment");

		View view = inflater.inflate(R.layout.simulation_page_single_selection, container, false);

		// View view = getActivity().findViewById(R.layout.practice);
		// Intent intent = new Intent(getActivity(),
		// SimulationTestActivity.class);
		// startActivity(intent);

		// getActivity().finish();
		// PracticeActivity.this.finish();

		return view;
	}
}
