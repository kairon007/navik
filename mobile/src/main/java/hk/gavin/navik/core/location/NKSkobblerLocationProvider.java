package hk.gavin.navik.core.location;

import android.content.Context;
import com.skobbler.ngx.positioner.SKCurrentPositionListener;
import com.skobbler.ngx.positioner.SKCurrentPositionProvider;
import com.skobbler.ngx.positioner.SKPosition;

public class NKSkobblerLocationProvider extends NKLocationProvider implements SKCurrentPositionListener {

    private SKCurrentPositionProvider mProvider;

    public NKSkobblerLocationProvider(Context context) {
        super(context);

        mProvider = new SKCurrentPositionProvider(getContext());
        mProvider.setCurrentPositionListener(this);
    }

    @Override
    public void onCurrentPositionUpdate(SKPosition skPosition) {
        setLastLocation(NKLocation.fromSKCoordinate(skPosition.getCoordinate()));
        setLastLocationAccuracy(skPosition.getHorizontalAccuracy());
        notifyLocationUpdate();
    }

    @Override
    public boolean addPositionUpdateListener(OnLocationUpdateListener listener) {
        if (getOnLocationUpdateListeners().size() == 0) {
            mProvider.requestLocationUpdates(true, true, true);
        }
        return super.addPositionUpdateListener(listener);
    }

    @Override
    public boolean removePositionUpdateListener(OnLocationUpdateListener listener) {
        boolean result = super.removePositionUpdateListener(listener);
        if (getOnLocationUpdateListeners().size() == 0) {
            mProvider.stopLocationUpdates();
        }
        return result;
    }
}