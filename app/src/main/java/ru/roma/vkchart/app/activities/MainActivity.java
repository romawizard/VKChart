package ru.roma.vkchart.app.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.roma.vkchart.R;
import ru.roma.vkchart.ui.dialogs.FragmentDialogs;
import ru.roma.vkchart.data.api.model_response.DialogModelResponse;
import ru.roma.vkchart.utils.MyLog;

public class MainActivity extends AppCompatActivity  implements FragmentDialogs.OnFragmentInteractionListener{

    private static final String FRAGMENT_DIALOG = "fragmentDialogs" ;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    FragmentDialogs fragmentDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadFragment();

//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void loadFragment (){

        android.support.v4.app.FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        fragmentDialog = (FragmentDialogs) getSupportFragmentManager().findFragmentByTag(FRAGMENT_DIALOG);
        if (fragmentDialog == null) {
            MyLog.log("null in FragmentDialogs");
            fragmentDialog = new FragmentDialogs();
        }
        fTran.replace(R.id.frame_layout, fragmentDialog, FRAGMENT_DIALOG).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
