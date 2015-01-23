package nc.lib;  
  
import cn.com.jdsc.R;
import android.os.Bundle;  
import android.app.Activity;  
import android.view.Gravity;
import android.view.Menu;  
import android.widget.TextView;  
import android.widget.Toast;
  
public class testlibActivity extends Activity {  
    // statement of native function.Means it has been realized by native layer.  
    public native String TestFromJNI();  
    // Load library,No Prefix and Suffix.  
    static {    
        System.loadLibrary("nc_lib_test");
       
    }
    

    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
/*        setContentView(R.layout.main_login);  
        TextView myTextView=(TextView)findViewById(R.id.tvChoiseA);  
        myTextView.setText(TestFromJNI()); */ 
        DisplayToast(TestFromJNI());
    }  
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        //getMenuInflater().inflate(R.menu.activity_hello_world, menu);  
        return true;
    }
    
	public void DisplayToast(String str) {
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP, 0, 220);
		toast.show();
	}
  
} 