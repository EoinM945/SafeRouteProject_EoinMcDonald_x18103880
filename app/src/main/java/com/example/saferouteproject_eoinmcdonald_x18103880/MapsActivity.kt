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

        //Garda Traffic Camera ArrayList objects
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
        latlngs.add(LatLng(53.4915,-6.203505))
        latlngs.add(LatLng(53.473286,-6.257102))
        latlngs.add(LatLng(53.410807,-6.231246))
        latlngs.add(LatLng(53.402015,-6.205119))
        latlngs.add(LatLng(53.3909,-6.43123))
        latlngs.add(LatLng(53.307215,-6.406321))
        latlngs.add(LatLng(53.322748,-6.406187))
        latlngs.add(LatLng(53.196405,-6.133379))
        latlngs.add(LatLng(53.273502,-6.173658))
        latlngs.add(LatLng(53.322158,-6.21794))
        latlngs.add(LatLng(53.327776,-6.244723))
        latlngs.add(LatLng(53.348239,-6.308347))
        latlngs.add(LatLng(53.364689,-6.28714))
        latlngs.add(LatLng(53.376182,-6.251871))
        latlngs.add(LatLng(53.299367,-6.507026))
        latlngs.add(LatLng(53.433221,-6.176713))
        latlngs.add(LatLng(53.423271,-6.326253))
        latlngs.add(LatLng(53.417611,-6.239268))
        latlngs.add(LatLng(53.3013,-6.352224))
        latlngs.add(LatLng(53.382667,-6.403226))
        latlngs.add(LatLng(53.29304,-6.287856))
        latlngs.add(LatLng(53.289709,-6.340383))
        latlngs.add(LatLng(53.291967,-6.311685))
        latlngs.add(LatLng(53.291189,-6.43748))
        latlngs.add(LatLng(53.337655,-6.385432))
        latlngs.add(LatLng(53.27511,-6.331049))
        latlngs.add(LatLng(53.29954,-6.204867))
        latlngs.add(LatLng(53.355122,-6.37228))
        latlngs.add(LatLng(53.362367,-6.455549))
        latlngs.add(LatLng(53.283491,-6.391536))
        latlngs.add(LatLng(53.280283,-6.214796))
        latlngs.add(LatLng(53.298211,-6.35416))
        latlngs.add(LatLng(53.280241,-6.38401))
        latlngs.add(LatLng(53.316289,-6.316935))
        latlngs.add(LatLng(53.392283,-6.383017))
        latlngs.add(LatLng(53.337892,-6.407036))
        latlngs.add(LatLng(53.299237,-6.219177))
        latlngs.add(LatLng(53.453578,-6.214087))
        latlngs.add(LatLng(53.283163,-6.462068))
        latlngs.add(LatLng(53.210627,-6.117136))
        latlngs.add(LatLng(53.269713,-6.211669))
        latlngs.add(LatLng(53.330089,-6.398097))
        latlngs.add(LatLng(53.282428,-6.348105))
        latlngs.add(LatLng(53.285612,-6.407661))
        latlngs.add(LatLng(53.285685,-6.375346))
        latlngs.add(LatLng(53.34035,-6.431794))
        latlngs.add(LatLng(53.353804,-6.40495))
        latlngs.add(LatLng(53.310412,-6.353529))
        latlngs.add(LatLng(53.341731,-6.413879))
        latlngs.add(LatLng(53.326051,-6.425506))
        latlngs.add(LatLng(53.460502,-6.21396))
        latlngs.add(LatLng(53.281985,-6.451898))
        latlngs.add(LatLng(53.347798,-6.397783))
        latlngs.add(LatLng(53.360776,-6.275115))
        latlngs.add(LatLng(53.378391,-6.229888))
        latlngs.add(LatLng(53.382984,-6.245817))
        latlngs.add(LatLng(53.391202,-6.172365))
        latlngs.add(LatLng(53.38516,-6.143524))
        latlngs.add(LatLng(53.406399,-6.299625))
        latlngs.add(LatLng(53.371224,-6.203606))
        latlngs.add(LatLng(53.378311,-6.182682))
        latlngs.add(LatLng(53.380572,-6.159873))
        latlngs.add(LatLng(53.384104,-6.10018))
        latlngs.add(LatLng(53.389449,-6.110258))
        latlngs.add(LatLng(53.388638,-6.071975))
        latlngs.add(LatLng(53.390746,-6.114323))
        latlngs.add(LatLng(53.454041,-6.210337))
        latlngs.add(LatLng(53.35014,-6.274538))
        latlngs.add(LatLng(53.367233,-6.27063))
        latlngs.add(LatLng(53.408349,-6.266286))
        latlngs.add(LatLng(53.498827,-6.26996))
        latlngs.add(LatLng(53.713189,-6.346235))
        latlngs.add(LatLng(53.347584,-6.291067))
        latlngs.add(LatLng(53.3465,-6.333234))
        latlngs.add(LatLng(53.337614,-6.265797))
        latlngs.add(LatLng(53.337598,-6.265745))
        latlngs.add(LatLng(53.291553,-6.236689))
        latlngs.add(LatLng(53.300545,-6.287307))
        latlngs.add(LatLng(53.293331,-6.246519))
        latlngs.add(LatLng(53.302306,-6.266502))
        latlngs.add(LatLng(53.306194,-6.32011))
        latlngs.add(LatLng(53.342567,-6.348884))
        latlngs.add(LatLng(53.277556,-6.186297))
        latlngs.add(LatLng(53.282068,-6.296999))
        latlngs.add(LatLng(53.279522,-6.317178))
        latlngs.add(LatLng(53.291406,-6.171467))
        latlngs.add(LatLng(53.275318,-6.344791))

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

        //tvCurrentAddress = findViewById<TextView>(R.id.tvAdd)

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