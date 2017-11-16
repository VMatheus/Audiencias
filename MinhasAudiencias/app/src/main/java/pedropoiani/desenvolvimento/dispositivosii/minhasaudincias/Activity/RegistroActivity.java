package pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.R;


public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth fAuten;
    private EditText novo_email;
    private EditText nova_senha;
    private String email;
    private String senha;
    private Button novo_registro;
    private ProgressDialog progressDialog;
    private TextView ja_cadastrado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fAuten = FirebaseAuth.getInstance();

        if (fAuten.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));

        }

        initViews();
    }

    private void initViews() {

        novo_email = (EditText) findViewById(R.id.novo_email);
        nova_senha = (EditText) findViewById(R.id.nova_senha);
        novo_registro = (Button) findViewById(R.id.novo_registro);
        ja_cadastrado = (TextView) findViewById(R.id.ja_cadastrado);
        progressDialog = new ProgressDialog(this);

        novo_email.setError(null);
        nova_senha.setError(null);

        novo_registro.setOnClickListener(this);
        ja_cadastrado.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == novo_registro) {
            novoUsuario();

        }

        if (v == ja_cadastrado) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

    }

    private void novoUsuario() {

        email = novo_email.getText().toString();
        senha = nova_senha.getText().toString();



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

        fAuten.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
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
}
