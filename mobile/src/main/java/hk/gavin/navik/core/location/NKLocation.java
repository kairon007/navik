package hk.gavin.navik.core.location;

import com.skobbler.ngx.SKCoordinate;

import java.io.Serializable;

public class NKLocation implements Serializable {

    final double latitude;
    final double longitude;

    public NKLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static NKLocation fromSKCoordinate(SKCoordinate skCoordinate) {
        return new NKLocation(skCoordinate.getLatitude(), skCoordinate.getLongitude());
    }

    public SKCoordinate toSKCoordinate() {
        return new SKCoordinate(longitude, latitude);
    }
}