package nc.lib;

import android.app.AlertDialog;
import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

public class Debug extends Application {
	
	public void nc_dialog(String msg){
		new AlertDialog.Builder(this)
			.setTitle("��ʾ")
			.setMessage(msg)
			.setPositiveButton("ȷ��",null)
			.show();
	}
	
	public void nc_toast(String str)  
    {  
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);  
        //����toast��ʾ��λ��  
        toast.setGravity(Gravity.TOP, 0, 220);  
        //��ʾ��Toast  
        toast.show();
    }
	
}

