package com.rend.bangkit2022.mygithubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rend.bangkit2022.mygithubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.setHasFixedSize(true)
        list.addAll(listUsers)
        showRecyclerList()

    }

    private val listUsers: ArrayList<User>
    get(){
        val dataUsername = resources.getStringArray(R.array.username)
        val dataName = resources.getStringArray(R.array.name)
        val dataAvatar = resources.obtainTypedArray(R.array.avatar)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataFollower = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val listUser = ArrayList<User>()
        for (i in dataUsername.indices){
            val user = User(dataUsername[i],
                dataName[i],
                dataAvatar.getResourceId(i, -1),
                dataCompany[i],
                dataLocation[i],
                dataRepository[i],
                dataFollower[i],
                dataFollowing[i]
            )
            listUser.add(user)
        }
        return listUser
    }

    private fun showRecyclerList(){
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(list)
        binding.rvUsers.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_USER, data)
                startActivity(intent)
            }
        })
    }

}
