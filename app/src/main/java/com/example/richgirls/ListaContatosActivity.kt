package com.example.richgirls

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.richgirls.adapter.ListaContatoAdapter
import com.example.richgirls.models.Contato
import com.example.richgirls.service.RetrofitInitialize
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_lista_contatos.*
import java.util.ArrayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaContatosActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_contatos)

        buscaListaContatos()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
        supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão



    }

    private fun buscaListaContatos() {

        val call = RetrofitInitialize().myService().listarContatos()
        call.enqueue(object : Callback<List<Contato>?> {
            override fun onResponse(
                call: Call<List<Contato>?>?,
                response: Response<List<Contato>?>?
            ) {

                response?.body()?.let {
                    var listaContatos : List<Contato> = it
                    exibirLista(listaContatos)
                }
                Log.i("techgirl", "lista retornada com sucesso")
            }

            override fun onFailure(
                call: Call<List<Contato>?>?,
                t: Throwable?
            ) {
                Log.e("onFailure error", t?.message)
            }
        })

    }

    private fun exibirLista(listaContatos : List<Contato>) {
        rv_contatos.adapter = ListaContatoAdapter(listaContatos, this
        ) { item: Contato, position: Int ->
            Log.e("ListaContatos", "Clicked on item  ${item.nome} at position $position")
            abrirTelaTransferencia(item)
        }

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rv_contatos.layoutManager = layoutManager
    }

    private fun abrirTelaTransferencia(item: Contato) {
        val intent = Intent(this, TransferirActivity::class.java)
            .putExtra("contato", item)
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