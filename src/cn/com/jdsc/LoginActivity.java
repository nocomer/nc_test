package cn.com.jdsc;

import cn.com.jdsc.R;
import nc.study.SimulationTestMainActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_login);
		System.out.println("Login");

		Button login_comfirm_button = (Button) findViewById(R.id.login_comfirm_button);

		login_comfirm_button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("Login is called");
				DisplayToast("Login...");
				View view = findViewById(R.layout.fragmentmain);

				Intent intent = new Intent(LoginActivity.this,
						MainMenuActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
			}
		});

	}

	public void DisplayToast(String str) {
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
		// 设置toast显示的位置
		toast.setGravity(Gravity.TOP, 0, 220);
		// 显示该Toast
		toast.show();
	}
}
