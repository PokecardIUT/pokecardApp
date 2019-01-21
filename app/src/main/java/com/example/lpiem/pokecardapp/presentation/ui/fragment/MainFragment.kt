package com.example.lpiem.pokecardapp.presentation.ui.fragment

class MainFragment {
/*
    lateinit var name: String
    lateinit var email: String
    lateinit var adapter: DeckListAdapter
    var viewModel: DeckListPresenter = DeckListPresenter(this);

    val isOnline: Boolean
        get() {
            val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deck_list)

        findViewById<View>(R.id.btSignOut).setOnClickListener(this)

        name = intent.getStringExtra("name")
        email = intent.getStringExtra("email")

        if (!name.isEmpty() && !email.isEmpty()) {
            tvName.text = "$name,"
            tvEmail.text = email
        }


        if (isOnline) {
            viewModel.getDecks()
            adapter = DeckListAdapter(viewModel.listDeck, this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btSignOut -> {
                if (viewModel.isLoggedFb()) {
                    AccessToken.setCurrentAccessToken(null)
                    LoginManager.getInstance().logOut()
                }
                startActivityForResult(Intent(this, LoginActivity::class.java), SIGN_OUT)
            }
        }
    }

    override fun notifyDataChange(listDeck: List<SetsItem?>) {
        adapter.items = listDeck
        adapter.notifyDataSetChanged()
    }

    companion object {
        private val SIGN_OUT = 8000
    }
    */
}
