package com.cathu.samrtscrollbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private val data = arrayListOf<Int>()
	private lateinit var adapter:Adapter
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		repeat(25){
			data.add(it+1)
		}
		adapter = Adapter(data)
		list.adapter = adapter
		list.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

		scrollBar.bindScrollView(list)
		initViews()
	}

	private fun initViews() {
		add.setOnClickListener {
			data.add(data.size+1)
			adapter.notifyDataSetChanged()
		}
		reduce.setOnClickListener {
			if (data.size == 0){
				return@setOnClickListener
			}
			data.removeAt(data.lastIndex)
			adapter.notifyDataSetChanged()
		}
	}


	class Adapter(val list:ArrayList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
			val root = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
			return object:RecyclerView.ViewHolder(root){}
		}

		override fun getItemCount(): Int = list.size

		override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
			holder.itemView.findViewById<TextView>(R.id.itemText).text = list[position].toString()
		}

	}

}