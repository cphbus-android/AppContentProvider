package app

import android.app.Activity
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.util.Log

class ActivityMain : Activity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val PCP = PetContentProvider()

        val cv = ContentValues()
        cv.put("petName", "George")
        cv.put("petAge", "34")
        PCP.insert(Uri.parse("content://PetContentProvider/pets"), cv)

        val PCPCursor = PCP.query(
                Uri.parse("content://PetContentProvider/pets"),
                null,
                null,
                null,
                null
        )

        PCPCursor.moveToFirst()
        do {
            Log.d("DebugContentProvider", "" + PCPCursor.getString(0).toInt() + " " + PCPCursor.getString(1) + " " + PCPCursor.getString(2).toInt())
        } while (PCPCursor.moveToNext())
    }
}
