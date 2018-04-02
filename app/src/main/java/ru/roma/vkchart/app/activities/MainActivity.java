package ru.roma.vkchart.app.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.roma.vkchart.R;
import ru.roma.vkchart.ui.dialogs.DialogsFragment;
import ru.roma.vkchart.ui.messages.MessageFragment;
import ru.roma.vkchart.utils.MyLog;

public class MainActivity extends AppCompatActivity  implements DialogsFragment.OnFragmentInteractionListener{

    private static final String FRAGMENT_DIALOG = "fragmentDialogs";
    private static final String FRAGMENT_MESSAGE = "fragmentMessage";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private DialogsFragment dialogFragment;
    private MessageFragment  messageFragment;

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
        dialogFragment = (DialogsFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_DIALOG);
        if (dialogFragment == null) {
            MyLog.log("null in DialogsFragment");
            dialogFragment = new DialogsFragment();
        }
        fTran.replace(R.id.frame_layout, dialogFragment, FRAGMENT_DIALOG).commit();

    }

    @Override
    public void onFragmentInteraction(int userId) {
        android.support.v4.app.FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        messageFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_MESSAGE);
        if (messageFragment == null) {
            MyLog.log("null in DialogsFragment");
            messageFragment = MessageFragment.newInstance(userId);
        }
        fTran.replace(R.id.frame_layout, messageFragment, FRAGMENT_MESSAGE).commit();


    }
}
