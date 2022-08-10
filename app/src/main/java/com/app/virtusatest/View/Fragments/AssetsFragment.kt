package com.app.virtusatest.View.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.virtusatest.Adapter.AssetsAdapter
import com.app.virtusatest.Interface.OnAssetsItemClickListener
import com.app.virtusatest.Model.assets
import com.app.virtusatest.Network.InternetConnection
import com.app.virtusatest.R
import com.app.virtusatest.ViewModel.AssetsViewModel

class AssetsFragment : Fragment(), OnAssetsItemClickListener {

    private var viewModel: AssetsViewModel? = null
    private var assetsadapter: AssetsAdapter? = null
    private var AssetsResponseLiveData: List<assets>? = null
    private var tempAssetsResponseLiveData: List<assets>? = null

    //Variable declaration
    lateinit var recyclerlisting: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var errorlayout: LinearLayout
    lateinit var imgerror: ImageView
    lateinit var txterrormessage: TextView
    lateinit var edtsearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        assetsadapter = AssetsAdapter(this@AssetsFragment.context)
        viewModel = ViewModelProviders.of(this).get(AssetsViewModel::class.java)
        if (viewModel != null) {
            viewModel!!.init()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_assets, container,
            false
        )
        initialization(view)
        ControllClickevent()
        return view
    }

    // Component initializing view with their ids
    @SuppressLint("FragmentLiveDataObserve")
    fun initialization(view: View) {
        recyclerlisting = view.findViewById(R.id.recyclerview)
        assetsadapter!!.onItemClickListener = this
        errorlayout = view.findViewById(R.id.errorlayout)
        imgerror = view.findViewById(R.id.imgerror)
        edtsearch = view.findViewById(R.id.edtsearch)
        txterrormessage = view.findViewById(R.id.txterrormessage)
        recyclerlisting.addItemDecoration(
            DividerItemDecoration(
                this@AssetsFragment.context,
                LinearLayoutManager.VERTICAL
            )
        )
        progress = view.findViewById(R.id.progress)
        // on below line we are initializing our views ith their ids.
        swipeRefreshLayout = view.findViewById(R.id.container)
        progress.visibility = View.VISIBLE
        var layoutManager1 =
            LinearLayoutManager(this@AssetsFragment.context, LinearLayoutManager.VERTICAL, false)
        recyclerlisting.apply {
            layoutManager = layoutManager1
            adapter = assetsadapter
        }


    }

    // Create showerror method to display error message to user
    fun showerror(errormessage: String, errorimage: Int, imagelink: String) {
        recyclerlisting.visibility = View.GONE
        if (imagelink.toString() == null && imagelink.length == 0) {
            errorlayout.visibility = View.VISIBLE
            txterrormessage.text = errormessage
        } else {
            errorlayout.visibility = View.VISIBLE
            imgerror.setImageResource(errorimage)
            txterrormessage.text = errormessage
        }
    }

    // Component initializing view with its Click event
    fun ControllClickevent() {
        if (InternetConnection().isNetworkAvailable(this@AssetsFragment.requireContext())) {
            loaddata()
        } else {
            showerror(
                getString(R.string.no_internet),
                R.drawable.ic_no_internet_connection_icon,
                ""
            )
        }
        // on below line we are adding refresh listener
        // for our swipe to refresh method.
        swipeRefreshLayout.setOnRefreshListener {
            // on below line we are setting is refreshing to false.
            swipeRefreshLayout.isRefreshing = false
            if (InternetConnection().isNetworkAvailable(this@AssetsFragment.requireContext())) {
                loaddata()
            } else {
                showerror(
                    getString(R.string.no_internet),
                    R.drawable.ic_no_internet_connection_icon,
                    ""
                )
            }
        }

        edtsearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length > 0 && AssetsResponseLiveData!!.size > 0) {
                    tempAssetsResponseLiveData =
                        AssetsResponseLiveData!!.filter { AssetsResponseLiveData ->
                            AssetsResponseLiveData.name!!.lowercase()
                                ?.contains(s.toString().lowercase())
                        }
                    if (tempAssetsResponseLiveData!!.size > 0) {
                        assetsadapter!!.setResults(tempAssetsResponseLiveData!!)
                    } else {
                        showerror(
                            getString(R.string.no_result_found),
                            R.drawable.ic_server_error,
                            ""
                        )
                    }

                } else {
                    recyclerlisting.visibility = View.VISIBLE
                    errorlayout.visibility = View.GONE
                    progress.visibility = View.GONE
                }
            }
        })

    }

    fun loaddata() {
        viewModel!!.GetAssetsList()?.observe(
            this,
            Observer<Any?> { assetlivedata ->
                if (assetlivedata != null) {
                    AssetsResponseLiveData = assetlivedata as List<assets>?
                    assetsadapter!!.setResults(AssetsResponseLiveData!!)
                    recyclerlisting.visibility = View.VISIBLE
                    errorlayout.visibility = View.GONE
                    progress.visibility = View.GONE
                } else {
                    showerror(
                        getString(R.string.server_error),
                        R.drawable.ic_server_error,
                        ""
                    )
                    progress.visibility = View.GONE
                }
            })
    }

    override fun onItemClick(item: assets) {
        Toast.makeText(this.requireContext(), item.name, Toast.LENGTH_SHORT).show()
    }
}