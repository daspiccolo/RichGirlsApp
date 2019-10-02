package com.example.richgirls

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import com.example.richgirls.models.TransferenciaConfirmacao

import kotlinx.android.synthetic.main.activity_comprovante.*
import kotlinx.android.synthetic.main.activity_transferir.*
import kotlinx.android.synthetic.main.activity_transferir.lbl_nome_contato as lbl_nome_contato1

class ComprovanteActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprovante)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
        supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão


        if (intent.extras != null) {
            val comprovante: TransferenciaConfirmacao =
                intent.extras?.getSerializable("comprovante")
                        as TransferenciaConfirmacao

            carregaDadosComprovante(comprovante)

        }
    }

    private fun carregaDadosComprovante(comprovante: TransferenciaConfirmacao) {
        lbl_nome_contato.text = resources.getString(R.string.nome, comprovante.recebedor.nome)
        lbl_banco.text =  comprovante.recebedor.banco
        lbl_conta.text = resources.getString(R.string.agencia_conta,  comprovante.recebedor.agencia,
            comprovante.recebedor.conta)
        lbl_valor.text = resources.getString(R.string.valor_com_label, comprovante.valor)
        lbl_data_tranf.text = resources.getString(R.string.data_transferencia, comprovante.dataTransferencia)
        lbl_comprovante.text = resources.getString(R.string.comprovante_label, comprovante.comprovanteId)
        lbl_mensagem.text = comprovante.mensagem
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.getItemId()) {
            android.R.id.home  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
            -> {
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java
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