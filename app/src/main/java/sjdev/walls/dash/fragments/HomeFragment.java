package sjdev.walls.dash.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import sjdev.walls.dash.BuildConfig;
import sjdev.walls.dash.ChangelogAdapter;
import sjdev.walls.dash.R;

/**
 * Created by sjdev on 2/15/2016.
 */
public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rv = (ViewGroup) inflater.inflate(R.layout.section_home, container, false);
        setHasOptionsMenu(true);
        fab=(FloatingActionButton)rv.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(String.format("https://play.google.com/store/apps/details?id=%s", BuildConfig.APPLICATION_ID))));
            }
        });
        return rv;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate
                (R.menu.menu_main, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // toggle nav drawer on selecting action bar app icon/title
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.changelog:
                showChangelog();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void showChangelog() {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.changelog)
                .adapter(new ChangelogAdapter(getActivity(), R.array.fullchangelog), null)
                .positiveText("Cool")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {

                    }
                }).show();
    }

}
