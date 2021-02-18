package com.yatochk.secure.travelclockx.map

import android.Manifest
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.yatochk.secure.travelclockx.BindingFragment
import com.yatochk.secure.travelclockx.R
import com.yatochk.secure.travelclockx.databinding.FMapBinding
import com.yatochk.secure.travelclockx.location.InfoLocationState
import com.yatochk.secure.travelclockx.location.LocationViewModel
import com.yatochk.secure.travelclockx.location.NeedPermissionLocationState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BindingFragment<FMapBinding>(),
    LocationPermissionDialog.LocationGrantedListener {

    private val mapFragment by lazy { SupportMapFragment() }

    private val locationViewModel: LocationViewModel by viewModels()

    private lateinit var googleMap: GoogleMap

    private val mapController by lazy { MapController(googleMap) }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                childFragmentManager.commit {
                    remove(
                        childFragmentManager.findFragmentByTag(LocationPermissionDialog.TAG)
                            ?: return@commit
                    )
                }
                locationViewModel.onPermissionLocationGranted()
            }
        }

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FMapBinding =
        FMapBinding.inflate(inflater, container, false)

    override fun FMapBinding.onInitView() {
        childFragmentManager.commit {
            replace(R.id.container_map, mapFragment)
        }
        mapFragment.getMapAsync {
            googleMap = it
            locationViewModel.location.observe(viewLifecycleOwner) { locationState ->
                when (locationState) {
                    NeedPermissionLocationState -> handleNeedLocationPermission()
                    is InfoLocationState -> mapController.moveTo(locationState)
                }
            }
        }
    }

    private fun handleNeedLocationPermission() {
        LocationPermissionDialog().show(childFragmentManager, LocationPermissionDialog.TAG)
    }

    override fun onApproveLocationGranted() {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun onDismissLocationGranted() {
        activity?.finish()
    }
}