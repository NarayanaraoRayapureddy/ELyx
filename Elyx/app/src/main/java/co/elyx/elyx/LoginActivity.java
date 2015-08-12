package co.elyx.elyx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by admin on 2/8/15.
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Utils.setupUI(findViewById(R.id.loginLayout),this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (Utils.isOnline(LoginActivity.this)) {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                /*} else {
                    Utils.showNoInternetAlert(LoginActivity.this);
                }*/
            }
        });
    }
}
