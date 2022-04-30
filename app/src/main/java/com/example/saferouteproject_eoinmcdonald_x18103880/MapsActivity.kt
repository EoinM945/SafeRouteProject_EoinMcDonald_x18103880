package com.example.saferouteproject_eoinmcdonald_x18103880

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap






class MapsActivity : AppCompatActivity(), OnMapReadyCallback
//, LocationListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraIdleListener
{

    private var mMap: GoogleMap? = null

    lateinit var mapView: MapView

    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"

    private val DEFAULT_ZOOM = 15f

    lateinit var B_search: Button

    lateinit var tvCurrentAddress: TextView

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null

    var end_latitude = 0.0

    var end_longitude = 0.0

    lateinit var origin: MarkerOptions

    lateinit var destination: MarkerOptions

    var latitude = 0.0

    var longitude = 0.0

    private val options = MarkerOptions()

    private val latlngs: ArrayList<LatLng> = ArrayList()

    override fun onMapReady(googleMap: GoogleMap) {

        mapView.onResume()
        mMap = googleMap

        latlngs.add(LatLng(53.61281, -6.188607))
        latlngs.add(LatLng(53.597129,-6.16165))
        latlngs.add(LatLng(53.416024,-6.178003))
        latlngs.add(LatLng(53.427707,-6.229529))
        latlngs.add(LatLng(53.401188,-6.400307))
        latlngs.add(LatLng(53.398081,-6.332871))
        latlngs.add(LatLng(53.367585,-6.272903))
        latlngs.add(LatLng(53.333802,-6.244927))
        latlngs.add(LatLng(53.326326,-6.278169))
        latlngs.add(LatLng(53.285648,-6.365907))
        latlngs.add(LatLng(53.338384,-6.34787))
        latlngs.add(LatLng(53.402639,-6.39721))
        latlngs.add(LatLng(53.350146,-6.233991))
        latlngs.add(LatLng(53.521953,-6.147831))
        latlngs.add(LatLng(53.485324,-6.150638))
        latlngs.add(LatLng(53.389558,-6.197874))
        latlngs.add(LatLng(53.359736,-6.200128))
        latlngs.add(LatLng(53.390784,-6.319665))
        latlngs.add(LatLng(53.364481,-6.229493))
        latlngs.add(LatLng(53.371854,-6.331091))
        latlngs.add(LatLng(53.360746,-6.272876))
        latlngs.add(LatLng(53.354469,-6.273307))
        latlngs.add(LatLng(53.349935,-6.267989))
        latlngs.add(LatLng(53.348989,-6.251547))
        latlngs.add(LatLng(53.348965,-6.251631))
        latlngs.add(LatLng(53.346875,-6.290776))
        latlngs.add(LatLng(53.323302,-6.352401))
        latlngs.add(LatLng(53.322829,-6.279177))
        latlngs.add(LatLng(53.316961,-6.326202))
        latlngs.add(LatLng(53.29159,-6.357379))
        latlngs.add(LatLng(53.291007,-6.404592))
        latlngs.add(LatLng(53.284252,-6.378243))
        latlngs.add(LatLng(53.455111,-6.347932))
        latlngs.add(LatLng(53.556766,-6.576495))
        latlngs.add(LatLng(53.621305,-6.424435))
        latlngs.add(LatLng(53.538837,-6.092235))
        latlngs.add(LatLng(53.383016,-6.205129))
        latlngs.add(LatLng(53.403251,-6.281973))
        latlngs.add(LatLng(53.397285,-6.24499))
        latlngs.add(LatLng(53.574541,-6.205068))
        latlngs.add(LatLng(53.363677,-6.224829))
        latlngs.add(LatLng(53.379515,-6.31058))
        latlngs.add(LatLng(53.38322,-6.393322))
        latlngs.add(LatLng(53.35894,-6.422798))
        latlngs.add(LatLng(53.342264,-6.349423))
        latlngs.add(LatLng(53.322587,-6.279304))
        latlngs.add(LatLng(53.30205,-6.283911))
        latlngs.add(LatLng(53.296688,-6.203905))



        for (point in latlngs) {
            options.position(point)
            options.position(point).title("CCTV")
            options.position(point).snippet("Garda Traffic Cam")
            googleMap.addMarker(options.position(point)
            )
        }

        /*val traffic1 = LatLng(53.61281, -6.188607)
        googleMap.addMarker(
            MarkerOptions()
                .position(traffic1)
                .title("Marker in Sydney")
        )*/


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap!!.setMyLocationEnabled(true)
        mMap!!.isTrafficEnabled = true
//        mMap!!.setOnCameraMoveListener (this)
//        mMap!!.setOnCameraMoveStartedListener(this)
//        mMap!!.setOnCameraIdleListener(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_maps)

        mapView = findViewById<MapView>(R.id.map1)

        tvCurrentAddress = findViewById<TextView>(R.id.tvAdd)

        B_search = findViewById(R.id.B_search)

        //askPermissionLocation()

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }

        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)


        B_search.setOnClickListener {

            searchArea()
        }

    }

    private fun searchArea() {
        val tf_location =
            findViewById<View>(R.id.TF_location) as EditText

        val location = tf_location.text.toString()

        var addressList: List<Address>? = null

        val markerOptions = MarkerOptions()

        if (location != "") {
            val geocoder = Geocoder(applicationContext)
            try {
                addressList = geocoder.getFromLocationName(location, 5)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (addressList != null) {
                for (i in addressList.indices) {
                    val myAddress = addressList[i]
                    val latLng =
                        LatLng(myAddress.latitude, myAddress.longitude)
                    markerOptions.position(latLng)
                    mMap!!.addMarker(markerOptions)
                    end_latitude = myAddress.latitude
                    end_longitude = myAddress.longitude

                    mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))

                    val mo = MarkerOptions()
                    mo.title("Distance")

                    val results = FloatArray(10)
                    Location.distanceBetween(
                        latitude,
                        longitude,
                        end_latitude,
                        end_longitude,
                        results
                    )

                    val s =
                        String.format("%.1f", results[0] / 1000)


                    //Setting marker to draw route between these two points
                    origin = MarkerOptions().position(LatLng(latitude, longitude))
                        .title("HSR Layout").snippet("origin")
                    destination =
                        MarkerOptions().position(LatLng(end_latitude, end_longitude))
                            .title(tf_location.text.toString())
                            .snippet("Distance = $s KM")
                    mMap!!.addMarker(destination)
                    mMap!!.addMarker(origin)

                    Toast.makeText(
                        this@MapsActivity,
                        "Distance = $s KM",
                        Toast.LENGTH_SHORT
                    ).show()


                    tvCurrentAddress!!.setText("Distance = $s KM")

                    //getting URL to the google Directions API
                    val url: String =
                        getDirectionsUrl(origin!!.getPosition(), destination!!.getPosition())!!

                    val downloadTask = DownloadTask()

                    //start downloading the json data from google directions API
                    downloadTask.execute(url)
                }
            }
        }
    }



    @Throws(IOException::class)
    private fun downloadUrl(strUrl: String): String? {
        var data = ""
        var iStream: InputStream? = null
        var urlConnection: HttpURLConnection? = null
        try {
            val url = URL(strUrl)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connect()
            iStream = urlConnection!!.inputStream
            val br =
                BufferedReader(InputStreamReader(iStream))
            val sb = StringBuffer()
            var line: String? = ""
            while (br.readLine().also { line = it } != null) {
                sb.append(line)
            }
            data = sb.toString()
            br.close()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        } finally {
            iStream!!.close()
            urlConnection!!.disconnect()
        }
        return data
    }





    //parsing into JSON format
    inner class ParserTask :
        CoroutineAsyncTask<String?, Int?, List<List<HashMap<String, String>>>?>() {
        override fun doInBackground(vararg jsonData: String?): List<List<HashMap<String, String>>>? {
            val jObject: JSONObject
            var routes: List<List<HashMap<String, String>>>? =
                null
            try {
                jObject = JSONObject(jsonData[0])
                val parser = DataParser()
                routes = parser.parse(jObject)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return routes
        }


        override fun onPostExecute(result: List<List<HashMap<String, String>>>?) {
            val points = ArrayList<LatLng?>()
            val lineOptions = PolylineOptions()
            for (i in result!!.indices) {
                val path =
                    result[i]
                for (j in path.indices) {
                    val point = path[j]
                    val lat = point["lat"]!!.toDouble()
                    val lng = point["lng"]!!.toDouble()
                    val position = LatLng(lat, lng)
                    points.add(position)
                }
                lineOptions.addAll(points)
                lineOptions.width(8f)
                lineOptions.color(Color.RED)
                lineOptions.geodesic(true)
            }

            if (points.size != 0) mMap!!.addPolyline(lineOptions)
        }

    }




    inner class DownloadTask :
        CoroutineAsyncTask<String?, Void?, String>(){

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val parserTask = ParserTask()
            parserTask.execute(result)
        }

        override fun doInBackground(vararg params: String?): String {
            var data = ""
            try{
                data = downloadUrl(params[0].toString()).toString()
            } catch (e: java.lang.Exception) {
                Log.d("Background Task", e.toString())
            }
            return data
        }

    }


    private fun getDirectionsUrl(origin: LatLng, dest: LatLng): String? {
        //Origin of route
        val str_origin = "origin=" + origin.latitude + "," + origin.longitude

        //Destination of Route
        val str_destination = "destination" + dest.latitude + "," + dest.longitude

        //transportation mode
        val mode = "mode=walking"

        //building parameters of webservice
        val parameters = "$str_origin&$str_destination&$mode"

        //output format
        val output = "json"

        //building the url to the web service
        return "https://maps.googleapis.com/maps/api/directions/$output?$parameters&key=AIzaSyCgraKSwPfUIyZLOmEDh_ptAbfRRAj7y1g"
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        askPermissionLocation()
        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null){
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }

        mapView.onSaveInstanceState(mapViewBundle)
    }

    private fun askPermissionLocation(){
        askPermission()
    }

    private fun askPermission() {
        Manifest.permission.ACCESS_FINE_LOCATION
        Manifest.permission.ACCESS_COARSE_LOCATION
    }


    private fun getCurrentLocation() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this@MapsActivity)

        try {
            @SuppressLint ("MissingPermission")
            val location =
                fusedLocationProviderClient!!.getLastLocation()

            location.addOnCompleteListener(object : OnCompleteListener<Location> {
                override fun onComplete(loc: Task<Location>) {
                    if (loc.isSuccessful) {

                        val currentLocation = loc.result as Location?
                        if (currentLocation != null) {
                            moveCamera(
                                LatLng(currentLocation.latitude, currentLocation.longitude),
                                DEFAULT_ZOOM
                            )

                            latitude = currentLocation.latitude
                            longitude = currentLocation.longitude

                        }
                    } else {
                        askPermissionLocation()
                    }
                }
            })
        } catch (se: Exception) {
            Log.e("TAG", "Security Exception")
        }
    }

    private fun moveCamera(latLng: LatLng, zoom: Float) {
        // mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }


    /*override fun onLocationChanged(location: Location?) {
       val geocoder = Geocoder (this, Locale.getDefault())
       var addresses: List<Address>? = null
     try {
           addresses = geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
       } catch (e: IOException) {
           e.printStackTrace()
       }
       setAddress(addresses!![0])
    }

   private fun setAddress(addresses: Address) {
       if (addresses != null) {

           if (addresses.getAddressLine(0) != null) {
               tvCurrentAddress!!.setText(addresses.getAddressLine(0))
           }
            if (addresses.getAddressLine(1) != null) {
                tvCurrentAddress!!.setText(
                       tvCurrentAddress.getText().toString() + addresses.getAddressLine(1)
               )
           }
        }
    }



    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    override fun onCameraMove() {

    }

    override fun onCameraMoveStarted(p0: Int) {

    }

    override fun onCameraIdle() {
        var addresses: List<Address>? = null
        val geocoder = Geocoder (this, Locale.getDefault())
        try {
            addresses = geocoder.getFromLocation( mMap!!.getCameraPosition().target.latitude, mMap!!.getCameraPosition().target.longitude, 1)

            setAddress(addresses!![0])

       } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
       } catch (e: IOException) {
            e.printStackTrace()
        }
    }*/


}