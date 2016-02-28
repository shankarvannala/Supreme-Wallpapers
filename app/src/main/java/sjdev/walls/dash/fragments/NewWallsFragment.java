package sjdev.walls.dash.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.afollestad.assent.Assent;
import com.afollestad.assent.AssentCallback;
import com.afollestad.assent.PermissionResultSet;
import sjdev.walls.dash.wallfilez.DetailedWallpaperActivity;
import sjdev.walls.dash.wallfilez.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import sjdev.walls.dash.R;
import sjdev.walls.dash.wallfilez.WallsGridAdapter;

public class NewWallsFragment extends Fragment {
    public NewWallsFragment() {
        // Required empty public constructor
    }
    private static final int DEFAULT_COLUMNS_PORTRAIT = 2;
    private static final int DEFAULT_COLUMNS_LANDSCAPE = 4;
    public static final String NAME = "name";
    public static final String WALL = "wall";
    public static final String AUTHOR = "author";

    private ArrayList<HashMap<String, String>> arraylist;
    private ViewGroup root;
    private static ProgressBar mProgress;
    private int mColumnCount;
    private int numColumns = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.section_wallpapers, container, false);
        setHasOptionsMenu(false);
        // Updates the card_activity when the Activity is first created
        // That way you can request permissions from within onCreate()
        Assent.setActivity(getActivity(), getActivity());
        if (!Assent.isPermissionGranted(Assent.WRITE_EXTERNAL_STORAGE)) {
            // The if statement checks if the permission has already been granted before
            Assent.requestPermissions(new AssentCallback() {
                @Override
                public void onPermissionResult(PermissionResultSet result) {
                    // Permission granted or denied
                }
            }, 69, Assent.WRITE_EXTERNAL_STORAGE, Assent.READ_EXTERNAL_STORAGE);
        }

        mProgress = (ProgressBar) root.findViewById(R.id.progress);
        final boolean isLandscape = isLandscape();
        int mColumnCountPortrait = DEFAULT_COLUMNS_PORTRAIT;
        int mColumnCountLandscape = DEFAULT_COLUMNS_LANDSCAPE;
        int newColumnCount = isLandscape ? mColumnCountLandscape : mColumnCountPortrait;
        if (mColumnCount != newColumnCount) {
            mColumnCount = newColumnCount;
            numColumns = mColumnCount;
        }

        new DownloadJSON().execute();
        return root;
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground (Void...params){
            // Create an array
            arraylist = new ArrayList<>();
            // Retrieve JSON Objects from the given URL address
            JSONObject json = JSONParser
                    .getJSONfromURL(getResources().getString(R.string.json_file_url));
            if (json != null) {
                try {
                    // Locate the array name in JSON
                    JSONArray jsonarray = json.getJSONArray("sw");

                    for (int i = 0; i < jsonarray.length(); i++) {
                        HashMap<String, String> map = new HashMap<>();
                        json = jsonarray.getJSONObject(i);
                        // Retrieve JSON Objects
                        map.put("name", json.getString("name"));
                        map.put("author", json.getString("author"));
                        map.put("wall", json.getString("url"));
                        // Set the JSON Objects into the array
                        arraylist.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), getString(R.string.json_error_toast), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), getString(R.string.json_error_toast), Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            final GridView gridView = (GridView) root.findViewById(R.id.gridView);
            gridView.setNumColumns(numColumns);
            final WallsGridAdapter mGridAdapter = new WallsGridAdapter(getActivity(), arraylist, numColumns);
            gridView.setAdapter(mGridAdapter);
            if (mProgress != null)
                mProgress.setVisibility(View.GONE);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final HashMap<String, String> data = arraylist.get(position);
                    final String wallurl = data.get((NewWallsFragment.WALL));
                    final String name=     data.get(NewWallsFragment.NAME);
                    final String author=     data.get(NewWallsFragment.AUTHOR);
                    final Intent intent = new Intent(getActivity(), DetailedWallpaperActivity.class)
                            .putExtra("name",name)
                            .putExtra("author",author)
                            .putExtra("wall", wallurl);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Lets Assent handle permission results, and contact your callbacks
        Assent.handleResult(permissions, grantResults);
    }
}

