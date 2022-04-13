package com.rend.bangkit2022.githubuserapp2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rend.bangkit2022.githubuserapp2.R
import com.rend.bangkit2022.githubuserapp2.databinding.ActivityDetailUserBinding
import com.rend.bangkit2022.githubuserapp2.ui.setting.SettingActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL )
        val bundle= Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        showLoading(true)
        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this) {
            if (it!=null){
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvRepository.text = it.public_repos
                    tvCompany.text = it.company
                    tvLocation.text = it.location
                    tvFollowers.text  = resources.getString(R.string.follower, it.followers)
                    tvFollowing.text = resources.getString(R.string.following, it.following)
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
                showLoading(false)
            }
        }

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if(count != null){
                    if(count > 0){
                        binding.toggleFavorite.isChecked = true
                        _isChecked = true
                    }else{
                        binding.toggleFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toggleFavorite.setOnClickListener{
            _isChecked = !_isChecked
            if(_isChecked){
                viewModel.addFavorite(username.toString(), id, avatarUrl.toString())
            }else{
                viewModel.removeFavorite(id)
            }
            binding.toggleFavorite.isChecked = _isChecked
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter =sectionPagerAdapter
            tabsLayout.setupWithViewPager(viewPager)
        }



    }

    private fun showLoading(state: Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility  = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite_menu -> {
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }

            R.id.setting_menu -> {
                Intent(this, SettingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }
}