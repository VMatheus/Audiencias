package pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;

import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Fragments.PerfilFragment;
import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Fragments.PrincipalFragment;
import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.R;

public class PrincipalActivity extends AppCompatActivity implements PrincipalFragment.OnFragmentInteractionListener {

    private AdView mAdView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content, new PrincipalFragment()).commit();

                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.content, new PerfilFragment()).commit();

                    return true;
                case R.id.navigation_notifications:

                    FirebaseAuth.getInstance().signOut();
                    Intent intentbtnAbrirActivityLogin = new Intent(PrincipalActivity.this, LoginActivity.class);
                    startActivity(intentbtnAbrirActivityLogin);
                    finish();

                    return true;
            }
            return false;
        }

    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.content, new PrincipalFragment()).commit();

        //mAdView = (AdView)findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
