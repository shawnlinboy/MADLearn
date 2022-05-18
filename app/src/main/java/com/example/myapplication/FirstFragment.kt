package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val ACTION_MANAGE_OVERLAY_PERMISSION =
                "android.settings.action.MANAGE_OVERLAY_PERMISSION";
            val intent = Intent("com.panda.settings.DEVICE_INFO");
            intent.putExtra(":settings:show_fragment_title", "我的设备")
            startActivity(intent)
        }

        binding.requestMic.setOnClickListener {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.RECORD_AUDIO),
                1000)
        }
    }

    override fun onResume() {
        super.onResume()
        val hasPermission = Settings.canDrawOverlays(requireContext())
        binding?.textviewFirst.text = "canDrawOverlays = $hasPermission"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}