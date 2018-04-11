package ru.roma.vkchart.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.roma.vkchart.R;
import ru.roma.vkchart.ui.fragment.DialogsFragment;
import ru.roma.vkchart.ui.fragment.MessageFragment;
import ru.roma.vkchart.utils.MyLog;

public class MainActivity extends AppCompatActivity implements DialogsFragment.OnFragmentInteractionListener {

    private static final String FRAGMENT_DIALOG = "fragmentDialogs";
    private static final String FRAGMENT_MESSAGE = "fragmentMessage";
    private DialogsFragment dialogFragment;
    private MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            loadFragment();
        }
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

    private void loadFragment() {

        FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        dialogFragment = (DialogsFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_DIALOG);
        if (dialogFragment == null) {
            MyLog.log("null in DialogsFragment");
            dialogFragment = new DialogsFragment();

        }
        fTran.replace(R.id.frame_layout, dialogFragment, FRAGMENT_DIALOG).commit();

    }

    @Override
    public void onFragmentInteraction(Bundle user) {
        FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        messageFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_MESSAGE);
        if (messageFragment == null) {
            MyLog.log("null in MessageFragment");
            messageFragment = MessageFragment.newInstance(user);
        }
        fTran.addToBackStack(FRAGMENT_MESSAGE);
        fTran.replace(R.id.frame_layout, messageFragment, FRAGMENT_MESSAGE).commit();
    }

}
