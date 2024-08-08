package com.nizamsetiawan.app.fasttrackedu.source.remote.response

import com.google.gson.annotations.SerializedName

data class VideoLessonResponse(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("mentor")
	val mentor: Mentor? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("discount_price")
	val discountPrice: Int? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("discount")
	val discount: Int? = null,

	@field:SerializedName("video")
	val video: String? = null,

	@field:SerializedName("reviews")
	val reviews: List<String?>? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("enrolled_members")
	val enrolledMembers: List<Any?>? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("curriculums")
	val curriculums: List<CurriculumsItem?>? = null,

	@field:SerializedName("_id")
	val id: String? = null
)

data class SubcurriculumsItem(

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("curriculum")
	val curriculum: String? = null
)

data class CurriculumsItem(

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("video_lesson")
	val videoLesson: String? = null,

	@field:SerializedName("subcurriculums")
	val subcurriculums: List<SubcurriculumsItem?>? = null
)

data class Mentor(

	@field:SerializedName("video_lessons")
	val videoLessons: List<String?>? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("about")
	val about: String? = null,

	@field:SerializedName("experience_duration")
	val experienceDuration: Int? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("linkedin")
	val linkedin: String? = null
)
