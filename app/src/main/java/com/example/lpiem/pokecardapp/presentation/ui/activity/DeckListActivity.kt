package com.example.lpiem.pokecardapp.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView

import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.manager.api.PokemonTCGApiImpl
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.presentation.ui.adapter.DeckListAdapter
import kotlinx.android.synthetic.main.activity_deck_list.*

class DeckListActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var tvCard: TextView
    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var name: String
    var email: String = ""

    val isOnline: Boolean
        get() {
            val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        return super.onCreateView(name, context, attrs)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deck_list)

        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        findViewById<View>(R.id.btSignOut).setOnClickListener(this)
        name = intent.getStringExtra("name")
        email = intent.getStringExtra("email")
        if (!name.isEmpty() && !email.isEmpty()) {
            tvName.text = "$name,"
            tvEmail.text = email
        }
        if (isOnline) {
            Log.d("commMgr", "Network connected")
            PokemonTCGApiImpl().getDecks().subscribe { listDeck ->
                Log.d("fzef", "test")
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = DeckListAdapter(listDeck, this)
                Log.d("fzef", listDeck.toString())
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btSignOut -> {
                startActivityForResult(Intent(this, LoginActivity::class.java), SIGN_OUT)
            }
        }
    }

    companion object {

        private val SIGN_IN_FB = 9002
        private val SIGN_IN_GOOGLE = 9003
        private val SIGN_OUT = 8000
    }
}
