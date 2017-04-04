package app

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

class PetContentProvider : ContentProvider()
{
    val petsCursor : MatrixCursor = MatrixCursor(arrayOf("petID", "petName", "petAge"))

    init{
        petsCursor.addRow(arrayOf(1, "Billy", 23))
        petsCursor.addRow(arrayOf(2, "Jack", 54))
        petsCursor.addRow(arrayOf(3, "James", 76))
    }

    val uriMatcher : UriMatcher = createUriMatcher()

    fun createUriMatcher() : UriMatcher
    {
        var matcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        matcher.addURI("PetContentProvider", "pets", 1)
        matcher.addURI("PetContentProvider", "pets/#", 2)

        return matcher
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri {
        val pn : String = "" + values?.get("petName")
        val pa : String = "" + values?.get("petAge")

        if(pn.length > 0 && pa.length > 0)
        {
            petsCursor.addRow(arrayOf(petsCursor.count + 1, pn, pa.toInt()))
        }

        return uri!!
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        return petsCursor
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}