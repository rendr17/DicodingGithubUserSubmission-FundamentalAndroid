package com.rend.bangkit2022.mygithubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.rend.bangkit2022.mygithubuser.databinding.ActivityDetailUserBinding


class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var binding : ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        binding.imgUser.setImageResource(user.avatar)
        binding.tvUsername.text = user.username
        binding.tvName.text = user.name
        binding.tvCompany.text = user.company
        binding.tvLocation.text = user.location
        binding.tvRepository.text = user.repository
        binding.tvFollowers.text = user.followers
        binding.tvFollowing.text = user.following
    }
}