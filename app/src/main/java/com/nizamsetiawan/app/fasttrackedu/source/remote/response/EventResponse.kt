package com.nizamsetiawan.app.fasttrackedu.source.remote.response

import com.google.gson.annotations.SerializedName

data class EventResponse(

	@field:SerializedName("term_conditions")
	val termConditions: List<TermConditionsItem?>? = null,

	@field:SerializedName("audience")
	val audience: String? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("registration_fee")
	val registrationFee: String? = null,

	@field:SerializedName("guide_book")
	val guideBook: String? = null,

	@field:SerializedName("timelines")
	val timelines: List<TimelinesItem?>? = null,

	@field:SerializedName("organizer")
	val organizer: String? = null,

	@field:SerializedName("event_date")
	val eventDate: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)

data class TermConditionsItem(

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("event")
	val event: String? = null
)

data class TimelinesItem(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("event")
	val event: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
)
