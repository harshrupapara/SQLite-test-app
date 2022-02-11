package com.example.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var helper = MyHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM CRYPTO", null)
        btnPrev.setOnClickListener {
            if (rs.moveToPrevious()) {
                cur.setText(rs.getString(1))
                abb.setText(rs.getString(2))
            } else
                Toast.makeText(applicationContext, "No Data Found", Toast.LENGTH_SHORT).show()
        }

        btnNext.setOnClickListener {
            when {
                rs.moveToNext() -> {
                    cur.setText(rs.getString(1))
                    abb.setText(rs.getString(2))
                }
                rs.moveToFirst() -> {
                    cur.setText(rs.getString(1))
                    abb.setText(rs.getString(2))
                }
                else -> Toast.makeText(applicationContext, "No Data Found!", Toast.LENGTH_SHORT)
                    .show()
            }


        }

        btnInsert.setOnClickListener {
            var cv = ContentValues()
            cv.put("NAME", cur.text.toString())
            cv.put("SHORT_TERM", abb.text.toString())
            db.insert("CRYPTO", null, cv)
            rs.requery()

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Add Data")
            ad.setMessage("Data Added Successfully!");
            ad.setPositiveButton("OK", null)
            ad.show()

        }

        btnUpdate.setOnClickListener {
            var cv = ContentValues()
            cv.put("NAME", cur.text.toString())
            cv.put("SHORT_TERM", abb.text.toString())
            db.update("CRYPTO", cv, "_id = ?", arrayOf(rs.getString(0)))
            rs.requery()

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Update Record")
            ad.setMessage("Record Updated!")
            ad.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                if (rs.moveToFirst()) {
                    cur.setText(rs.getString(1))
                    abb.setText(rs.getString(2))
                }

            })
            ad.show()
        }
        btnCls.setOnClickListener {
            cur.setText("")
            abb.setText("")
            cur.requestFocus()
        }
        btnDel.setOnClickListener {
            db.delete("CRYPTO", "_id = ?", arrayOf(rs.getString(0)))
            rs.requery()

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Delete Record")
            ad.setMessage("Record Deleted!")
            ad.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                if (rs.moveToFirst()) {
                    cur.setText(rs.getString(1))
                    abb.setText(rs.getString(2))
                } else{
                    cur.setText("No Data Found!")
                abb.setText("No Data Found!")}

            })
            ad.show()


        }
    }
}