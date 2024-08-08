package com.nizamsetiawan.app.fasttrackedu.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivitySearchResultBinding

class SearchResultActivity : CoreActivity<ActivitySearchResultBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun setupBinding(layoutInflater: LayoutInflater): ActivitySearchResultBinding =
        ActivitySearchResultBinding.inflate(layoutInflater)
}
