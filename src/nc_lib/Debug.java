package nc_lib;

import android.app.AlertDialog;
import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

public class Debug extends Application {
	
	public void nc_dialog(String msg){
		new AlertDialog.Builder(this)
			.setTitle("提示")
			.setMessage(msg)
			.setPositiveButton("确定",null)
			.show();
	}
	
	public void nc_toast(String str)  
    {  
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);  
        //设置toast显示的位置  
        toast.setGravity(Gravity.TOP, 0, 220);  
        //显示该Toast  
        toast.show();
    }
	
}

