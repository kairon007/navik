package hk.gavin.navik.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.common.base.Optional;
import hk.gavin.navik.R;
import hk.gavin.navik.application.NKApplication;
import hk.gavin.navik.core.wear.NKWearManager;
import hk.gavin.navik.injection.DaggerHomeComponent;
import hk.gavin.navik.injection.HomeComponent;
import hk.gavin.navik.injection.HomeModule;
import hk.gavin.navik.ui.fragmentcontroller.HomeFragmentController;
import hk.gavin.navik.util.SkobblerUtility;
import org.jdeferred.DoneCallback;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity
        implements AbstractNavikActivity<HomeComponent>, DoneCallback<Boolean> {

    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Inject HomeFragmentController mController;
    @Inject NKWearManager mNKWearManager;
    private Optional<HomeComponent> mComponent = Optional.absent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().inject(this);

        setContentView(R.layout.activity_home);
        onViewCreated();
    }

    @Override
    protected void onDestroy() {
        mNKWearManager.onDestroy();
        super.onDestroy();
    }

    protected void onViewCreated() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        SkobblerUtility
                .prepareAndInitializeLibrary()
                .done(this);
    }

    @Override
    public void onDone(Boolean result) {
        if (result) {
            mController.initialize();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
        else {
            mController.getCurrentFragment().onBackPressed();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                return mController.getCurrentFragment().onOptionsItemSelected(item);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public HomeComponent component() {
        if (!mComponent.isPresent()) {
            mComponent = Optional.of(
                    DaggerHomeComponent.builder()
                            .applicationComponent(NKApplication.getInstance().component())
                            .homeModule(new HomeModule(this))
                            .build()
            );
            mComponent.get().inject(this);
        }

        return mComponent.get();
    }
}
