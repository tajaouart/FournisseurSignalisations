package fr.aylan.testtemp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson


class OtherActivity : AppCompatActivity() {

    var recycleView: RecyclerView? = null
    var myDataset = emptyArray<FAQQuestion>()

    var viewAdapter: VoyantsAdapter? = null
    var viewManager: LinearLayoutManager? = null
    var searchView: SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        recycleView = findViewById<RecyclerView>(R.id.lv2)
        searchView = findViewById<SearchView>(R.id.searchView)
        myDataset = Gson().fromJson(
            _faq,
            Array<FAQQuestion>::class.java
        )

        viewManager = LinearLayoutManager(applicationContext)
        viewAdapter = VoyantsAdapter(myDataset, applicationContext)


        recycleView?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isBlank()) {
                    viewAdapter = VoyantsAdapter(myDataset, applicationContext)

                    recycleView?.apply {
                        setHasFixedSize(true)
                        layoutManager = viewManager
                        adapter = viewAdapter
                    }
                    return true
                } else {
                    var items = myDataset.getFiltered(query = newText)
                    viewAdapter = VoyantsAdapter(items, applicationContext)

                    recycleView?.apply {
                        setHasFixedSize(true)
                        layoutManager = viewManager
                        adapter = viewAdapter
                    }
                    return true
                }
                return false
            }
        })


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isBlank()) {
                        viewAdapter = VoyantsAdapter(myDataset, applicationContext)

                        recycleView?.apply {
                            setHasFixedSize(true)
                            layoutManager = viewManager
                            adapter = viewAdapter
                        }
                        return true
                    } else {
                        var items = myDataset.getFiltered(query = newText)
                        viewAdapter = VoyantsAdapter(items, applicationContext)

                        recycleView?.apply {
                            setHasFixedSize(true)
                            layoutManager = viewManager
                            adapter = viewAdapter
                        }
                        return true
                    }
                    return false
                }
            })
            isIconifiedByDefault = false
        }

        return true
    }
}

private fun Array<FAQQuestion>.getFiltered(query: String): Array<FAQQuestion> {
    var items = ArrayList<FAQQuestion>()
    this.forEach {
        if (it.question.toLowerCase().contains(query.toLowerCase())) {
            items.add(it)
        }
    }
    return items.toTypedArray()
}

private fun Array<FAQQuestion>.containsQuery(query: String): Boolean {
    var bool = false
    this.forEach {
        if (it.question.toLowerCase().contains(query.toLowerCase())) {
            bool = true
            return true
        }
    }
    return bool

}
