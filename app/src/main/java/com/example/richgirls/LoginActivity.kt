package com.example.richgirls

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.richgirls.adapter.MyPreferences
import com.example.richgirls.models.LoginResponse
import com.example.richgirls.service.RetrofitInitialize
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    var login : String = ""
    var senha : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_acessar.setOnClickListener {
            //exemplo para pegar dados preenchidos
            login = edt_login.text.toString()
            senha = edt_senha.text.toString()

            fazerRequisicaoLogin()
        }
    }

    private fun fazerRequisicaoLogin() {
        val call = RetrofitInitialize().myService().login()
        call.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>?,
                response: Response<LoginResponse?>?
            ) {

                response?.body()?.let {
                    val login: LoginResponse = it
                    salvaNome(login.nome)
                    mostraMensagem(login.mensagem)
                    abrirListaContatos()
                }

                Log.i("techgirl", "login feito com sucesso")


            }

            override fun onFailure(
                call: Call<LoginResponse?>?,
                t: Throwable?
            ) {
                mostraMensagem("Login com problema! tente novamente mais tarde!")
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun abrirListaContatos() {
        val intent = Intent(this, HomeActivity::class.java).apply{}
        startActivity(intent)
    }

    private fun mostraMensagem(mensagem: String) {
        lbl_mensagem.setText(mensagem)
        lbl_mensagem.visibility = View.VISIBLE
    }

    private fun salvaNome(nome: String) {
        MyPreferences(this).salvarNome(nome)
    }
}