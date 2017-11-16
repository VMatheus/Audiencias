package pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.R;

public class PrincipalFragment extends Fragment {

    private Button list_aud;

    private OnFragmentInteractionListener mListener;




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_principal, container, false);

        list_aud = myView.findViewById(R.id.list_aud);
        list_aud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new AudienciasFragment()).commit();


            }
        });


        return myView;

    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
