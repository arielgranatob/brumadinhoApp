package ndn.noceli.com.brumadinhoapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCenter.start(application, "82995e17-ffd7-45f9-96ae-625a6a0e7c66", Analytics::class.java, Crashes::class.java)
    }

    override fun onStart() {
        super.onStart()
        btncadastropessoa.setOnClickListener {
            launchActivity<FormularioCadastroPessoas>()
        }
        btncadastroanimais.setOnClickListener {
            launchActivity<FormularioCadastroAnimais>()
        }
    }
}
