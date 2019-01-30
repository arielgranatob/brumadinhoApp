package ndn.noceli.com.brumadinhoapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import com.android.volley.*
import kotlinx.android.synthetic.main.activity_formulario_cadastro_animais.*
import kotlinx.android.synthetic.main.activity_formulario_cadastro_pessoas.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class FormularioCadastroAnimais : AppCompatActivity() {

    private var bitmap: Bitmap? = null
    private val PICK_IMAGE_REQUEST = 1
    var aux = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_cadastro_animais)
        toolbarcadastroanimal.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        imagemanimal.setOnClickListener {
            showFileChooser()
        }
        btncadastraranimal.setOnClickListener {
            if (!tipoanimal.text.isNullOrBlank() || !nomecontato.text.isNullOrBlank() || !telefonecontato.text.isNullOrBlank()) {
                cadastrarAnimais()
            } else {
                Toast.makeText(
                    this@FormularioCadastroAnimais,
                    "Há campos em branco, por favor preencha-os!",
                    Toast.LENGTH_LONG
                )
                    .show()
            }

        }
    }

    private fun cadastrarAnimais(){
        val progre=ProgreDialog(this@FormularioCadastroAnimais,"Cadastrando...por favor aguarde")
        val url = "https://brumadinhoapp.arielgranato.com.br/cadastrarAnimais.php"
        val queue = Volley.newRequestQueue(this)
        queue.add(object :
            StringRequest(Request.Method.POST, url, object : Response.Listener<String> {
                override fun onResponse(response: String) {

                    progre.pararProg()
                    Handler().post(Runnable { val builder = AlertDialog.Builder(this@FormularioCadastroAnimais)
                        builder.setTitle("Confirmação")
                        builder.setMessage("Cadastrado com sucesso!")
                        builder.setNeutralButton("OK",null)
                        val dialog: AlertDialog = builder.create()
                        dialog.show() })

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    progre.pararProg()
                    val builder = AlertDialog.Builder(this@FormularioCadastroAnimais)
                    builder.setTitle("Confirmação")
                    builder.setMessage("Cadastrado com sucesso!")
                    builder.setNeutralButton("OK",null)
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val map = HashMap<String,String>()
                map.put("tipoAnimal", tipoanimal.text.toString())
                map.put("nomeContato", nomecontato.text.toString())
                map.put("telefoneContato", telefonecontato.text.toString())
                map.put("descricaoAnimal", descricaoanimal.text.toString())
                map.put("fotoAnimal", getStringImage(bitmap!!))
                return map
            }
        })
    }


    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        aux = 1
        startActivityForResult(Intent.createChooser(intent, "Selecione a foto"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath = data.data

            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                //Setting the Bitmap to ImageView
                imagemanimal.setImageBitmap(bitmap)
                // cadastroRep();
            } catch (e: IOException) {
                // bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.logo).toInt()
                // imgrep.setImageBitmap(bitmap)
                Toast.makeText(this@FormularioCadastroAnimais, "Por favor selecione uma foto!", Toast.LENGTH_LONG)
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
}
