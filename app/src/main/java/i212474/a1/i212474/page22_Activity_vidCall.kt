package i212474.a1.i212474

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import i212474.a1.i212474.databinding.ActivityPage22VidCallBinding
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.RtcEngineConfig
import io.agora.rtc2.video.VideoCanvas
import kotlin.concurrent.thread

class page22_Activity_vidCall : AppCompatActivity() {
    private lateinit var binding:ActivityPage22VidCallBinding
    private val appID= "9dd4f8ef62a6460e86c69e2255776232"
    private val channelName= "calling"
    private val token="007eJxTYCg4JCzwsaxk3f/3rkGhnw18Gxjm7JlVOsX5v0PDRu683iwFBsuUFJM0i9Q0M6NEMxMzg1QLs2Qzy1QjI1NTc3MzI2Oj0gv/UhsCGRn4tq9lZWSAQBCfnSE5MScnMy+dgQEAy3Ag+Q=="
    private lateinit var user: String
    private lateinit var personID:String

    private var isJoined= false
    private var agoraEngin:RtcEngine?=null
    private var localSurfaceview: SurfaceView?=null
    private var remoteSurfaceView: SurfaceView?=null

    private val PERMISSION_ID=12
    private val REQUESTED_PERMISSION= arrayOf(
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.CAMERA
    )

    private fun checkpermission():Boolean{
        return !(ContextCompat.checkSelfPermission(
            this,REQUESTED_PERMISSION[0])!= PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission( this, REQUESTED_PERMISSION[1])!= PackageManager.PERMISSION_GRANTED)
    }
    private fun showMsg(msg:String)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page22_vid_call)

        val userID = intent.getStringExtra("USER_ID")
        val mentorID = intent.getStringExtra("PROFILE")
        user =userID.toString()
        personID=mentorID.toString()

        if(!checkpermission()){
            ActivityCompat
                .requestPermissions(
                    this, REQUESTED_PERMISSION, PERMISSION_ID
                )
        }

        setVidEngin()

        if(checkpermission())
        {
            val option= ChannelMediaOptions()
            option.channelProfile=Constants.CHANNEL_PROFILE_COMMUNICATION
            option.clientRoleType=Constants.CLIENT_ROLE_BROADCASTER
            setupLocalVid()
            localSurfaceview!!.visibility= VISIBLE
            agoraEngin!!.startPreview()
            agoraEngin!!.joinChannel(token,channelName,1, option)

        }
        else {
            showMsg("No Permission")
            ActivityCompat
                .requestPermissions(
                    this, REQUESTED_PERMISSION, PERMISSION_ID
                )
        }

        val leave = findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.leave)
        leave.setOnClickListener{
            agoraEngin!!.leaveChannel()
            remoteSurfaceView!!.visibility= GONE
            localSurfaceview!!.visibility= GONE
            isJoined=false
            finish()
        }
        val back1 = findViewById<LinearLayout>(R.id.cross)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
    }

    private fun setVidEngin(){
        try {
            val config = RtcEngineConfig()
            config.mContext = baseContext
            config.mAppId = appID
            config.mEventHandler = mRtcEventHandler
            agoraEngin = RtcEngine.create(config)
            agoraEngin!!.enableVideo()
        }
        catch (e:Exception)
        {
            showMsg(e.message.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        agoraEngin!!.stopPreview()
        agoraEngin!!.leaveChannel()

        thread {
            RtcEngine.destroy()
            agoraEngin=null
        }.start()
    }

    private val mRtcEventHandler: IRtcEngineEventHandler=object:IRtcEngineEventHandler(){

        override fun onUserJoined(uid: Int, elapsed: Int) {
            //super.onUserJoined(uid, elapsed)
            showMsg("User Joined")
            runOnUiThread {setupRemoteVid(uid)}
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            //super.onJoinChannelSuccess(channel, uid, elapsed)
            isJoined=true
            showMsg("Joined Channel")
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            //super.onUserOffline(uid, reason)
            showMsg("User offline")
            runOnUiThread{remoteSurfaceView!!.visibility= GONE}
        }
    }

    private fun setupRemoteVid(vid: Int)
    {
        remoteSurfaceView= SurfaceView(baseContext)
        remoteSurfaceView!!.setZOrderMediaOverlay(true)
        binding.remortUser.addView(remoteSurfaceView)

        agoraEngin!!.setupRemoteVideo(
            VideoCanvas(
                remoteSurfaceView, VideoCanvas.RENDER_MODE_FIT,vid
            )
        )
    }
    private fun setupLocalVid()
    {
        localSurfaceview= SurfaceView(baseContext)
        binding.sendervid.addView(localSurfaceview)

        agoraEngin!!.setupLocalVideo(
            VideoCanvas(
                remoteSurfaceView, VideoCanvas.RENDER_MODE_FIT,0
            )
        )
    }

}