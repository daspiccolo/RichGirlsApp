package com.example.richgirls

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.richgirls.models.Contato
import com.example.richgirls.models.TransferenciaConfirmacao
import com.example.richgirls.service.RetrofitInitialize
import kotlinx.android.synthetic.main.activity_transferir.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransferirActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transferir)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
        supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão

        val contato : Contato = intent.extras?.getSerializable("contato") as Contato

        btn_confirmar.setOnClickListener({
            fazerTransferencia()
        })

        carregaDadosContato(contato)

    }

    private fun carregaDadosContato(contato: Contato) {
        lbl_nome_contato.text = contato.nome
        lbl_banco_contato.text = contato.banco
        lbl_conta_contato.text = resources.getString(R.string.agencia_conta,  contato.agencia, contato.conta)
    }


    private fun fazerTransferencia() {
        val call = RetrofitInitialize().myService().fazerTransferencia()
        call.enqueue(object : Callback<TransferenciaConfirmacao?> {
            override fun onResponse(
                call: Call<TransferenciaConfirmacao?>?,
                response: Response<TransferenciaConfirmacao?>?
            ) {

                response?.body()?.let {
                    abrirComprovante(it)
                }

                Log.i("techgirl", "transferencia feita com sucesso")

            }

            override fun onFailure(
                call: Call<TransferenciaConfirmacao?>?,
                t: Throwable?
            ) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun abrirComprovante(comprovante: TransferenciaConfirmacao) {
        val intent = Intent(this, ComprovanteActivity::class.java)
            .putExtra("comprovante", comprovante)
            .apply {  }

        startActivity(intent)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.getItemId()) {
            android.R.id.home  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
            -> {
                startActivity(
                    Intent(
                        this,
                        ListaContatosActivity::class.java
                    )
                )  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity()  //Método para matar a activity e não deixa-lá indexada na pilhagem
            }
            else -> {
            }
        }
        return true
    }
}