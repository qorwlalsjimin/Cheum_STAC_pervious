package mirim.cheum_stac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        sign_up = findViewById(R.id.signupBtn);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    // When initializing your Activity, check to see if the user is currently signed in.
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    private void signUp() {
        // 이메일
        EditText emailEdit = findViewById(R.id.edit_signup_email);
        String email = emailEdit.getText().toString();
        // 비밀번호
        EditText passEdit = findViewById(R.id.edit_signup_pass);
        String password = passEdit.getText().toString();
        EditText passckEdit = findViewById(R.id.edit_signup_ckpass);
        String ckpassword = passckEdit.getText().toString();
        if (!password.equals("") && !email.equals("") && password.equals(ckpassword)) {
            // 이메일과 비밀번호가 공백이 아닌 경우
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(SignUpActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "등록되지 않았습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
        } else if (password.equals("") && email.equals("")){
            // 이메일과 비밀번호가 공백인 경우
            Toast.makeText(SignUpActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
        } else if (!password.equals(ckpassword)) {
            // 비밀번호와 확인이 일치하지 않는 경우
            Toast.makeText(SignUpActivity.this, "비밀번호 확인이 다릅니다.", Toast.LENGTH_LONG).show();
        }



    }

    Intent intent = getIntent();

}