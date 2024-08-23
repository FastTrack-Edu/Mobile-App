package com.nizamsetiawan.app.fasttrackedu.views.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nizamsetiawan.app.fasttrackedu.BuildConfig
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityDetailVideoLessonBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.CurriculumFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.DescriptionFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.FragmentDetailAdapter
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.MentorFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.ReviewFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.videoLesson.BlankFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.viewmodels.DashboardViewModel
import org.koin.android.ext.android.inject
class DetailVideoLessonActivity : CoreActivity<ActivityDetailVideoLessonBinding>() {
    private val detailVideoLessonViewModel: DashboardViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()
        setupToolbar(binding.mToolbar)
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityDetailVideoLessonBinding =
        ActivityDetailVideoLessonBinding.inflate(layoutInflater)

    private fun setupViewPager(data: VideoLessonResponse) {
        val viewPager: ViewPager2 = binding.viewPagerdetaillesson
        val tabLayout: TabLayout = binding.tablayoutdetaillesson

        val fragmentAdapter = FragmentDetailAdapter(this)
        fragmentAdapter.addFragment(DescriptionFragment(), "Deskripsi", data)
        fragmentAdapter.addFragment(CurriculumFragment(), "Kurikulum", data)
        fragmentAdapter.addFragment(MentorFragment(), "Mentor", data)
        fragmentAdapter.addFragment(ReviewFragment(), "Review", data)
        fragmentAdapter.addFragment(BlankFragment(), "Kursus Serupa", data)

        viewPager.adapter = fragmentAdapter

        // Setup TabLayout with ViewPager2 using TabLayoutMediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()
    }

    @SuppressLint("SetTextI18n")
    private fun observeData() {
        val lessonId = intent.getStringExtra(POST_ID)
        lessonId?.let { detailVideoLessonViewModel.getDetailVideoLesson(it) }
        detailVideoLessonViewModel.detailVideoLesson.observe(this) { response ->
            when (response) {
                is ResponseState.Loading -> {}
                is ResponseState.Success -> {
                    val data = response.data
                    binding.apply {
                        tvTitleName.text = data.title
                        Glide.with(root)
                            .load("${BuildConfig.BASE_URL}${data.thumbnail}")
                            .into(playerView)
                        tvRating.text = data.rating.toString()
                        tvPrice.text = data.discountPrice.toString()
                        tvDiscountedPrice.text = data.price.toString()
                        tvTime.text = data.discount.toString()
                        tvJoined.text = data.enrolledMembers?.size.toString() + " Bergabung"
                        tvAdvanced.text = data.level
                        tvMaterial.text = data.curriculums?.size.toString() + " Materi"
                        Glide.with(root)
                            .load("${BuildConfig.BASE_URL}${data.mentor?.photo}")
                            .into(ivProfile)
                        tvName.text = data.mentor?.name
                    }
                    setupViewPager(data) // Set up ViewPager with data
                }
                is ResponseState.Error -> {
                    // Handle error
                }
            }
        }
    }

    companion object {
        const val POST_ID = "post_id"
    }
}

