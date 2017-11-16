package pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth fAuten;
    private EditText user_email;
    private EditText user_senha;
    private String email;
    private String senha;
    private Button user_entrar;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView novo_cadastro;
    private static final String TAG = "AddToDatabase";



    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        overridePendingTransition(0, 0);
        View relativeLayout = findViewById(R.id.login_container);
        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        relativeLayout.startAnimation(animation);

        fAuten = FirebaseAuth.getInstance();

        if (fAuten.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));

        }

        user_email = (EditText) findViewById(R.id.user_email);
        user_senha = (EditText) findViewById(R.id.user_senha);
        user_entrar = (Button) findViewById(R.id.user_entrar);
        novo_cadastro = (TextView) findViewById(R.id.novo_cadastro);
        progressDialog = new ProgressDialog(this);

        user_email.setError(null);
        user_senha.setError(null);

        user_entrar.setOnClickListener(this);
        novo_cadastro.setOnClickListener(this);

    }

    private void LoginUsuario() {

        email = user_email.getText().toString();
        senha = user_senha.getText().toString();



        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Por favor, Digite seu Email! ", Toast.LENGTH_LONG).show();
            return;

        }

        if(TextUtils.isEmpty(senha) ){
            Toast.makeText(this,"Digite uma Senha Válida!", Toast.LENGTH_LONG).show();
            return;


        }

        progressDialog.setMessage("Registrando.. Aguarde...");
        progressDialog.show();

        fAuten.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            finish();

                            startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));

                        }else{

                            if (!verificaConexao()) {
                                Toast.makeText(getApplicationContext(), "Verifique a Conexão!", Toast.LENGTH_LONG).show();

                            }
                        }

                        progressDialog.dismiss();


                    }
                });

    }



    public boolean verificaConexao() {

        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        conectado = conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected();

        return conectado;
    }

    public void onClick(View v) {

        if (v == user_entrar) {
            LoginUsuario();

        }

        if (v == novo_cadastro) {
            startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
        }

    }


    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
