package pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Modelos.User;
import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.R;
import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.utils.GetDataFromFirebase;

public class PerfilFragment extends Fragment implements View.OnClickListener {

   private static final String TAG = "AddToDataBase";
   private Button salvar_perfil;
   private EditText username, nOab;
   private String userId;

   //Declarações FireBase DataBase
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        salvar_perfil = (Button)getView().findViewById(R.id.salvar_perfil);
        username = (EditText)getView().findViewById(R.id.username);
        nOab = (EditText)getView().findViewById(R.id.nOab);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: Added information to database: \n" +
                        dataSnapshot.getValue());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        salvar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Submit pressed.");
                String nameuser = username.getText().toString();
                String noab = nOab.getText().toString();


                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + nameuser + "\n" +
                        "email: " + noab + "\n"

                );

                //handle the exception if the EditText fields are null
                if(!nameuser.equals("") && !noab.equals("")){
                    User userInformation = new User(nameuser, noab );
                    myRef.child("users").child(userId).setValue(userInformation);
                    toastMessage("New Information has been saved.");
                    username.setText("");
                    nOab.setText("");

                }else{
                    toastMessage("Fill out all the fields");
                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }
}









