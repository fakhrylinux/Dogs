package tech.fakhrylinux.dogs.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import tech.fakhrylinux.dogs.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}