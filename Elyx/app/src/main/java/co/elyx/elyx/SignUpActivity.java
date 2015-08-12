package co.elyx.elyx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 2/8/15.
 */
public class SignUpActivity extends Activity {

    private EditText username;
    private EditText email;
    private EditText mobile;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        username = (EditText) findViewById(R.id.username);
//        username.setError(getString(R.string.username_error_message));
        email = (EditText) findViewById(R.id.signupEmail);

        Utils.setupUI(findViewById(R.id.signupLayout),this);
        Button signUpButton = (Button) findViewById(R.id.signupButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (Utils.isOnline(SignUpActivity.this)) {

                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                /*} else {
                    Utils.showNoInternetAlert(SignUpActivity.this);
                }*/
            }
        });
    }


}
