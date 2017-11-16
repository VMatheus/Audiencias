package pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import com.melnykov.fab.ObservableScrollView;
import com.melnykov.fab.ScrollDirectionListener;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Activity.AddAudienciaActivity;
import pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.R;

public class AudienciasFragment extends Fragment {

    private FloatingActionButton floatingActionButton;




    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_audiencias_fragment,
                container, false);



        floatingActionButton = view.findViewById(R.id.fab4);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), AddAudienciaActivity.class));

            }

        });

        return view;
    }


}