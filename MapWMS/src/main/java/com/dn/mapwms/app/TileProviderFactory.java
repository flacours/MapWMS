package com.dn.mapwms.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import com.dn.mapwms.app.WMSTileProvider;

import android.util.Log;

public class TileProviderFactory {
	
	public static WMSTileProvider getOsgeoWmsTileProvider() {

        // http://dnmap:8080/geoserver/oneida/wms
		final String OSGEO_WMS =
				//"http://vmap0.tiles.osgeo.org/wms/vmap0" +
                "http://dnmap:8080/geoserver/oneida/wms" +
	    		"?service=WMS" +
	    		"&version=1.1.1" +  			
	    		"&request=GetMap" +
	    		"&layers=oneida:oneida" +
	    		"&bbox=%f,%f,%f,%f" +
	    		"&width=256" +
	    		"&height=256" +
	    		"&srs=EPSG:900913" +
	    		"&format=image/png" +				
	    		"&transparent=true";
		
		
		WMSTileProvider tileProvider = new WMSTileProvider(256,256) {
        	
	        @Override
	        public synchronized URL getTileUrl(int x, int y, int zoom) {
	        	double[] bbox = getBoundingBox(x, y, zoom);
	            String s = String.format(Locale.US, OSGEO_WMS, bbox[MINX], 
	            		bbox[MINY], bbox[MAXX], bbox[MAXY]);
	            Log.d("WMSDEMO", s);
	            URL url = null;
	            try {
	                url = new URL(s);
	            } catch (MalformedURLException e) {
	                throw new AssertionError(e);
	            }
	            return url;
	        }
		};
		return tileProvider;
	}
}
