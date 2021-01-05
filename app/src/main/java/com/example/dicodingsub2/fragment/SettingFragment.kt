package com.example.dicodingsub2.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat
import com.example.dicodingsub2.AlarmReceiver
import com.example.dicodingsub2.R


class SettingFragment : Fragment(R.layout.fragment_setting) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            getActivity()?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.settings, SettingsFragment())
                ?.commit()
        }

    }

    class SettingsFragment : PreferenceFragmentCompat(),
        SharedPreferences.OnSharedPreferenceChangeListener {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.setting_preference, rootKey)
        }

        override fun onResume() {
            super.onResume()
            preferenceScreen.sharedPreferences
                .registerOnSharedPreferenceChangeListener(this)
        }

        override fun onPause() {
            super.onPause()
            preferenceScreen.sharedPreferences
                .unregisterOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(preference: SharedPreferences, key: String) {
            val onAlarm = preference.getBoolean(key, true)
            val alarmManager = AlarmReceiver()
            if (onAlarm) {
                alarmManager.setRepeatingAlarm(requireActivity(), AlarmReceiver.TYPE_REPEATING, getString(R.string.alarm))
            } else {
                alarmManager.cancelAlarm(requireActivity(), AlarmReceiver.TYPE_REPEATING)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }


}