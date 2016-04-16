package hk.gavin.navik.core.map;

import com.google.common.base.Optional;
import hk.gavin.navik.core.directions.NKDirections;
import hk.gavin.navik.core.location.NKLocation;
import hk.gavin.navik.ui.fragment.AbstractUiFragment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
public abstract class NKMapFragment extends AbstractUiFragment {

    @Getter(AccessLevel.PROTECTED) private Optional<MapEventsListener> mMapEventsListener = Optional.absent();

    @Getter @Setter(AccessLevel.PROTECTED) private boolean mMapLoaded = false;

    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED)
    private boolean mPendingMoveToCurrentLocation = false;
    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED)
    private boolean mDisplayMoveToCurrentLocationButton = false;

    public void setMapEventsListener(MapEventsListener listener) {
        mMapEventsListener = Optional.of(listener);
    }

    abstract public NKLocation getMapCenter();

    abstract public void moveToCurrentLocation();
    abstract public void moveToCurrentLocationOnceAvailable();

    abstract public void showMoveToCurrentLocationButton();
    abstract public void hideMoveToCurrentLocationButton();

    abstract public void showRoute(NKDirections directions, boolean zoom);
    abstract public void zoomToCurrentRoute();
    abstract public void clearCurrentRoute();

    public interface MapEventsListener {
        void onMapLoadComplete();
    }
}
