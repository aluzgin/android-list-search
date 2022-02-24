package npo.kib.dictionarysearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import npo.kib.dictionarysearch.ui.DictionaryFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DictionaryFragment.newInstance()).commit()
    }
}