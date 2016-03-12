package sjdev.walls.dash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import sjdev.walls.dash.fragments.HomeFragment;
import sjdev.walls.dash.fragments.NewWallsFragment;
import sjdev.walls.dash.fragments.AboutFragment;
import sjdev.walls.dash.fragments.CategoriesFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Prefer mPrefs;
    private boolean firstrun, enable_features;
    private static final boolean WITH_LICENSE_CHECKER = false;
    private static final String MARKET_URL = "https://play.google.com/store/apps/details?id=";

    private int[] tabIcons = {
            R.drawable.tab_home,
            R.mipmap.new_walls,
            R.drawable.tab_cat,
            R.mipmap.about
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrefs = new Prefer(MainActivity.this);
        enable_features = mPrefs.isFeaturesEnabled();
        firstrun = mPrefs.isFirstRun();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_long_name);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        runLicenseChecker();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(),"Home");
        adapter.addFrag(new NewWallsFragment(), "New Wallpapers");
        adapter.addFrag(new CategoriesFragment(), "Categories");
        adapter.addFrag(new AboutFragment(), "About");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    private void showChangelog() {
        new MaterialDialog.Builder(this)
                .title(R.string.changelog)
                .adapter(new ChangelogAdapter(this, R.array.fullchangelog), null)
                .positiveText("Cool")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        mPrefs.setNotFirstrun();
                    }
                }).show();
    }
    private void runLicenseChecker() {
        if (firstrun) {
            if (WITH_LICENSE_CHECKER) {

            } else {
                mPrefs.setFeaturesEnabled(true);

                showChangelogDialog();
            }
        }
        else {
            if (WITH_LICENSE_CHECKER) {
                if (!enable_features) {

                } else {

                    showChangelogDialog();
                }
            } else {

                showChangelogDialog();
            }
        }
    }

    private void showChangelogDialog() {
        String launchinfo = getSharedPreferences("PrefsFile", MODE_PRIVATE).getString("version", "0");
        if (launchinfo != null && !launchinfo.equals(Util.getAppVersion(this)))
            showChangelog();
        storeSharedPrefs();
    }

    @SuppressLint("CommitPrefEdits")
    private void storeSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        sharedPreferences.edit().putString("version", Util.getAppVersion(this)).commit();
    }

}