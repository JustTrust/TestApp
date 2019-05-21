package com.test.testapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.test.testapp.R
import com.test.testapp.network.models.Item
import com.test.testapp.ui.adapters.TitleAdapter
import com.test.testapp.view_model.MainActivityVM

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityVM

    private val list: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        subscribeToVM()
        initUI()
    }

    private fun subscribeToVM() {
        viewModel = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        viewModel.getListOfStrings().observe(this, Observer { list -> updateUI(list) })
    }

    private fun updateUI(newList: List<Item>?) {
        list.clear()
        newList?.let { list.addAll(it) }
        titleList?.adapter?.notifyDataSetChanged()
    }

    private fun initUI() {
        titleList.adapter = TitleAdapter(list) { itemWasChosen(it) }
    }

    private fun itemWasChosen(item: Item) {
        Timber.d("Item with title ${item.title} was chosen")
    }

    override fun onResume() {
        super.onResume()
        if (::viewModel.isInitialized) viewModel.updateList()
    }
}
