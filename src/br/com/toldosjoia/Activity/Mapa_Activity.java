package br.com.toldosjoia.Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.toldosjoia.R;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.mapa.endereco;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;



public class Mapa_Activity  extends FragmentActivity implements android.location.LocationListener{
	
    private GoogleMap mMap;
    private ArrayList<Cliente> cliente = new ArrayList<Cliente>();
    private Operacoes_no_banco_local banco_local;
    Marker eu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_layout);
        banco_local = new Operacoes_no_banco_local(this);
        banco_local.getCliente(cliente);
        setUpMapIfNeeded();
        markerCliente();
 
        getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this, null);
    }

    private LocationManager getLocationManager() {
		// TODO Auto-generated method stub
    	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		return locationManager;
	}

	@Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    public void markerCliente(){
    	List<endereco> en = endereco.buscar(this, cliente);
    	if(en != null){
    		for(int x = 0; x < en.size();x++)
    		{
    			mMap.addMarker(new MarkerOptions().position(new LatLng(en.get(x).getLatitude(), en.get(x).getLongitude())).title(en.get(x).getNome()));
    		}
    	}
    	else
    		Log.e("sem", "sem endered");
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
    	
    Location loc = getLocationManager().getLastKnownLocation(LocationManager.GPS_PROVIDER);
      if(loc != null){
        	     eu = mMap.addMarker(new MarkerOptions()
        	       .position(new LatLng(loc.getLatitude(), loc.getLongitude()))
        	       .title("minha localizacao"));
          }
      else{
    	    eu = mMap.addMarker(new MarkerOptions()
 	       .position(new LatLng(0, 0))
 	       .title("minha localizacao"));
    	    eu.setVisible(false);
      }
  
    }

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		//mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(arg0.getLatitude(), arg0.getLongitude())));
	    eu.setPosition(new LatLng(arg0.getLatitude(), arg0.getLongitude()));
		if(!eu.isVisible())eu.setVisible(true);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getLocationManager().removeUpdates(this);
	}
}
