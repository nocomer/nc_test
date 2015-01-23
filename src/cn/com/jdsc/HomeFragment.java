package cn.com.jdsc;

import cube.table.CubeTableActivity;
import cn.com.jdsc.R;
import nc.lib.testlibActivity;
import nc.study.SimulationTestMainActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("HomeFragment");

		View view = inflater.inflate(R.layout.main_home, container, false);

		Button login_comfirm_button = (Button) view
				.findViewById(R.id.practice_comfirm_button);
	

		login_comfirm_button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				DisplayToast("Practice...");
				Intent intent = new Intent(getActivity(),
						SimulationTestMainActivity.class);
				startActivity(intent);
				// getActivity().finish();
			}
		});
		
		Button cube_table_button = (Button) view
				.findViewById(R.id.cube_table_button);
		
		cube_table_button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				DisplayToast("CubeTable Practice...");
				Intent intent = new Intent(getActivity(),
						CubeTableActivity.class);
				startActivity(intent);
				// getActivity().finish();
			}
		});
		
		
		Button practice_comfirm_button_test = (Button) view
				.findViewById(R.id.practice_comfirm_button_test);
		
		practice_comfirm_button_test.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//DisplayToast("CubeTable Practice...");
				Intent intent = new Intent(getActivity(),
						testlibActivity.class);
				startActivity(intent);
				// getActivity().finish();
			}
		});
		
		

		return view;
	}

	public void DisplayToast(String str) {
		Toast toast = Toast.makeText(getActivity(), str, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP, 0, 220);
		toast.show();
	}
}
