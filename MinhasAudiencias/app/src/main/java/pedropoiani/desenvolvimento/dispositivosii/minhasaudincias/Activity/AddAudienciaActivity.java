package pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.R;

public class AddAudienciaActivity extends AppCompatActivity {

    private EditText edit_numerodoprocesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_audiencia);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);

   return true;

    }

}
