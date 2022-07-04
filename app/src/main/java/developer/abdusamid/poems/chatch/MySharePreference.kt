package developer.abdusamid.poems.chatch

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import developer.abdusamid.poems.DataClassIkki

object MySharePreference {
    private const val NAME = "my_catch_file"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var contactList: ArrayList<DataClassIkki>
        get() = gsonStringtoList(sharedPreferences.getString("keyList", "[]")!!)
        set(value) = sharedPreferences.edit {
            it.putString("keyList", listToString(value))
        }

    fun gsonStringtoList(gsonString: String): ArrayList<DataClassIkki> {
        val type = object : TypeToken<ArrayList<DataClassIkki>>() {}.type
        return Gson().fromJson(gsonString, type)
    }

    fun listToString(list: ArrayList<DataClassIkki>): String {
        return Gson().toJson(list)
    }
}



