package com.nizamsetiawan.app.fasttrackedu.utils

import android.content.Context
import android.content.SharedPreferences
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.User

object Prefs {
    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private const val PREFERENCE_NAME = "fasttrackedu.pref"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        editor = prefs.edit()
    }

    private const val KEY_ID = "key_id"
    private const val KEY_NAME = "key_name"
    private const val KEY_EMAIL = "key_email"
    private const val KEY_TOKEN = "key_token"
    private const val KEY_ROLE = "key_role"
    private const val KEY_MEMBER_TYPE = "key_member_type"
    private const val KEY_PASSWORD = "key_password"
    private const val KEY_V = "key_v"

    val getUserId: String
        get() = prefs.getString(KEY_ID, "") ?: ""
    val getName: String
        get() = prefs.getString(KEY_NAME, "") ?: ""
    val getEmail: String
        get() = prefs.getString(KEY_EMAIL, "") ?: ""
    val getToken: String
        get() = prefs.getString(KEY_TOKEN, "") ?: ""
    val getRole: String
        get() = prefs.getString(KEY_ROLE, "") ?: ""
    val getMemberType: String?
        get() = prefs.getString(KEY_MEMBER_TYPE, null)
    val getPassword: String
        get() = prefs.getString(KEY_PASSWORD, "") ?: ""
    val getV: Int
        get() = prefs.getInt(KEY_V, 0)

    fun setLoginPrefs(user: User, token: String) {
        editor.putString(KEY_ID, user.id)
        editor.putString(KEY_NAME, user.name)
        editor.putString(KEY_EMAIL, user.email)
        editor.putString(KEY_TOKEN, token)
        editor.putString(KEY_ROLE, user.role)
        editor.putString(KEY_MEMBER_TYPE, user.memberType?.toString())
        editor.putString(KEY_PASSWORD, user.password)
        editor.putInt(KEY_V, user.v ?: 0)
        editor.apply()
    }

    fun clearAuthPrefs() {
        editor.remove(KEY_ID)
        editor.remove(KEY_NAME)
        editor.remove(KEY_EMAIL)
        editor.remove(KEY_TOKEN)
        editor.remove(KEY_ROLE)
        editor.remove(KEY_MEMBER_TYPE)
        editor.remove(KEY_PASSWORD)
        editor.remove(KEY_V)
        editor.apply()
    }
}
