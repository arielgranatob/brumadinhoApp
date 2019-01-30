package ndn.noceli.com.brumadinhoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_formulario_cadastro_pessoas.*
import android.graphics.Bitmap
import android.widget.Toast
import android.R.attr.bitmap
import android.app.Activity
import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.content.Intent
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.widget.RadioButton
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.ByteArrayOutputStream
import java.io.IOException
import com.android.volley.RequestQueue
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RetryPolicy








class FormularioCadastroPessoas : AppCompatActivity() {

    private var bitmap: Bitmap? = null
    private var bitmap2: Bitmap? = null
    private val PICK_IMAGE_REQUEST = 1
    private val PICK_IMAGE_REQUEST2 = 2
    var aux = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_cadastro_pessoas)
        toolbarcadastropessoas.setNavigationOnClickListener {
            finish()
        }


    }

    override fun onStart() {
        super.onStart()
        fotodesaparecido.setOnClickListener {
            showFileChooser()
        }
        fototatuagemdesaparecido.setOnClickListener {
            showFileChooser2()
        }
        btncadastrarpessoa.setOnClickListener {
            if (!edtnomedesaparecido.text.isNullOrBlank() || !nomeDeclarante.text.isNullOrBlank() || !contatoDeclarante.text.isNullOrBlank()) {//retorna true se tiver vazio
                cadastraPessoa()
            } else {
                Toast.makeText(
                    this@FormularioCadastroPessoas,
                    "Há campos em branco, por favor preencha-os!",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }


    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        aux = 1
        startActivityForResult(Intent.createChooser(intent, "Selecione a foto"), PICK_IMAGE_REQUEST)
    }

    private fun showFileChooser2() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        aux = 2
        startActivityForResult(Intent.createChooser(intent, "Selecione a foto"), PICK_IMAGE_REQUEST2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath = data.data

            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                //Setting the Bitmap to ImageView
                fotodesaparecido.setImageBitmap(bitmap)
                // cadastroRep();
            } catch (e: IOException) {
                // bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.logo).toInt()
                // imgrep.setImageBitmap(bitmap)
                Toast.makeText(this@FormularioCadastroPessoas, "Por favor selecione uma foto!", Toast.LENGTH_LONG)
                    .show()

            }

        }
        if (requestCode == PICK_IMAGE_REQUEST2 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath = data.data

            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                //Setting the Bitmap to ImageView
                fototatuagemdesaparecido.setImageBitmap(bitmap2)
                // cadastroRep();
            } catch (e: IOException) {
                // bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.logo).toInt()
                // imgrep.setImageBitmap(bitmap)
                Toast.makeText(this@FormularioCadastroPessoas, "Por favor selecione uma foto!", Toast.LENGTH_LONG)
                    .show()

            }

        }
    }

    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)

    }


    fun cadastraPessoa(){
        val progre=ProgreDialog(this@FormularioCadastroPessoas,"Cadastrando...por favor aguarde")
        val strreq = object : StringRequest(Request.Method.POST,
            "https://brumadinhoapp.arielgranato.com.br/cadastroDesaparecidos.php",
            Response.Listener {

                Log.d("teste",it)
                progre.pararProg()
                Handler().post(Runnable { val builder = AlertDialog.Builder(this@FormularioCadastroPessoas)
                    builder.setTitle("Confirmação")
                    builder.setMessage("Cadastrado com sucesso!")
                    builder.setNeutralButton("OK",null)
                    val dialog: AlertDialog = builder.create()
                    dialog.show() })
            }, Response.ErrorListener { e -> e.printStackTrace()
            Log.e("Erro",e.message)}) {
            public override fun getParams(): Map<String, String> {
                val map = HashMap<String,String>()
                map.put("nomeDesaparecido",edtnomedesaparecido.text.toString() )
                map.put("fotoDesaparecido", getStringImage(bitmap!!))
                map.put("celularDesaparecido", celulardesaparecido.text.toString())
                map.put("caractFisicas", caractdesaparecido.text.toString())
                val radio: RadioButton = findViewById(rgtatuagem.checkedRadioButtonId)
                map.put("tatuagemDesaparecido", radio.text.toString())
                map.put("fotoTatuagem", getStringImage(bitmap2!!))
                val radio2: RadioButton = findViewById(rgcicatriz.checkedRadioButtonId)
                map.put("cicatrizDesaparecido", radio2.text.toString())
                map.put("regiaoCicatriz", regiaocicatriz.text.toString())
                map.put("caractMarcante", caractmarcantes.text.toString())
                map.put("nomeDeclarante", nomeDeclarante.text.toString())
                map.put("contatoDeclarante", contatoDeclarante.text.toString())
                return map
            }
        }
        val socketTimeout = 30000//30 seconds - change to what you want
        val policy = DefaultRetryPolicy(
            socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        strreq.setRetryPolicy(policy)
        val queue = Volley.newRequestQueue(this)
        queue.add(strreq)

    }
}

