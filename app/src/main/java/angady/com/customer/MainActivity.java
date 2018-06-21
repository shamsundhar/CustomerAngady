package angady.com.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                       startActivity(new Intent(MainActivity.this, LoginActivity.class));
                       finish();
                    }
                }, 3000);
    }
}
