package com.example.vihaan.testbooknotetaking

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.vihaan.testbooknotetaking.ui.PagerAdapter
import com.example.vihaan.testbooknotetaking.ui.notesScreen.NotesActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.vihaan.testbooknotetaking.models.questions.Question
import com.example.vihaan.testbooknotetaking.ui.noteDetail.NoteDetailActivity
import com.example.vihaan.testbooknotetaking.ui.questsions.*
import com.google.gson.Gson
import com.yalantis.ucrop.UCrop
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions


@RuntimePermissions
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val window = getWindow();
//            val background = getResources().getDrawable(R.drawable.toolbar_gradient);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
//            window.setNavigationBarColor(getResources().getColor(android.R.color.transparent));
//            window.setBackgroundDrawable(background);
//        }
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//            takeScreenShot()
            takeScreenShotWithPermissionCheck()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        initViews()
    }

    private fun initViews(){
        initViewPager()
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun takeScreenShot(){

        val now = Date()
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)

        try {
            // image naming and path  to include sd card  appending name you choose for file
            val mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg"

            // create bitmap screen capture
            val v1 = window.decorView.rootView
            v1.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(v1.drawingCache)
            v1.isDrawingCacheEnabled = false

            val imageFile = File(mPath)

            val outputStream = FileOutputStream(imageFile)
            val quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.flush()
            outputStream.close()

//            openScreenshot(imageFile)
            openCropper(imageFile)
        } catch (e: Throwable) {
            // Several error may come out with file handling or DOM
            e.printStackTrace()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun storagePermissionDenied(){
        Toast.makeText(this, "Please allow storage permissions", Toast.LENGTH_LONG).show()
    }

//    private fun openScreenshot(file: File)
//    {
//
//
//        val intent = Intent()
//        intent.action = Intent.ACTION_VIEW
//        val uri = Uri.fromFile(file)
//        intent.setDataAndType(uri, "image/*")
//        startActivity(intent)
//    }

    private fun openCropper(file: File){
        val sourceUri = Uri.fromFile(file)
        val destinationUri = Uri.fromFile(getDestinationUri())
        val maxHeight  = 500
        val maxWidth =  500
//                .withAspectRatio(16.0f, 9.0f)
        UCrop.of(sourceUri, destinationUri)
                .useSourceImageAspectRatio()
                .start(this);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            if(data!=null)
            {
                openNotesActivity(data)
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            if(data!=null)
            {
                val throwable = UCrop.getError(data);
            }
        }
    }

    private fun openNotesActivity(data : Intent){
        val uri = UCrop.getOutput(data);
        val intent = Intent(this, NoteDetailActivity::class.java)
//        data.putExtra(NoteDetailActivity.KEY_NOTE, )
        intent.putExtras(data)
        startActivity(intent)
    }

    private fun getDestinationUri(): File? {
        val now = Date()
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)
        val mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg"
        val imageFile = File(mPath)
        return imageFile
    }

    lateinit var adapter: PagerAdapter
    private fun initViewPager()
    {
        adapter = PagerAdapter(supportFragmentManager,getFragments())
        viewPager.adapter = adapter
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun getFragments(): ArrayList<Fragment> {
        val fragments = arrayListOf<Fragment>()

        fragments.add(QuestionFragment1.newInstance(Bundle()))
        fragments.add(QuestionFragment2.newInstance(Bundle()))
        fragments.add(QuestionFragment3.newInstance(Bundle()))
        fragments.add(QuestionFragment4.newInstance(Bundle()))
        fragments.add(QuestionFragment5.newInstance(Bundle()))
        return fragments
    }

    private fun getJson(): String? {
        var json: String? = null
        try {
            val inputStream = getAssets().open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    private fun getQuestions(): List<Question>? {
        val gson = Gson()
        var json = getJson()
        val type = object : TypeToken<List<Question>>() { }.type
//        val questions = gson.fromJson<List<Question>>(json, type)
        val jsonObject = JSONArray(json)
        json = jsonObject.toString()
        val questions = gson.fromJson<List<Question>>(json, type)
        return questions
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_questions -> {
                // Handle the camera action
            }
            R.id.nav_notes -> {
//                showFragment(R.id.container, NotesFragment.newInstance(Bundle()))
                val intent = Intent(this, NotesActivity::class.java)
                startActivity(intent)
            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_manage -> {
//
//            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
