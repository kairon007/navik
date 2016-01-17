package hk.gavin.navik.core.location;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.skobbler.ngx.SKCoordinate;
import com.skobbler.ngx.routing.SKViaPoint;

import java.util.ArrayList;
import java.util.List;

public class NKLocationUtil {

    public static List<SKCoordinate> toSKCoordinateList(List<NKLocation> locationList) {
        return Lists.transform(locationList, toSKCoordinateFunction);
    }

    public static List<SKViaPoint> toSKViaPointList(List<NKLocation> locationList) {
        List<SKViaPoint> viaPointList = new ArrayList<>();
        for (int i = 0; i < locationList.size(); i++) {
            viaPointList.add(new SKViaPoint(i, locationList.get(i).toSKCoordinate()));
        }
        return viaPointList;
    }

    public final static Function<NKLocation, SKCoordinate> toSKCoordinateFunction =
            new Function<NKLocation, SKCoordinate>() {

                @Override
                public SKCoordinate apply(NKLocation input) {
                    return input.toSKCoordinate();
                }
            };
}