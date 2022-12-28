package com.nkodem.citiestravelling

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nkodem.citiestravelling.algorithms.Graph
import com.nkodem.citiestravelling.algorithms.TravellingMerchantProblem
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PointF
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.*
import android.widget.*
import androidx.core.app.NotificationCompat
import com.nkodem.citiestravelling.algorithms.Edge
import java.io.File
import java.io.FileOutputStream
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val namesId = listOf<EditText>(
            findViewById(R.id.cityName1),
            findViewById(R.id.cityName2),
            findViewById(R.id.cityName3),
            findViewById(R.id.cityName4),
            findViewById(R.id.cityName5),
            findViewById(R.id.cityName6),
            findViewById(R.id.cityName7),
            findViewById(R.id.cityName8),
        )
        val roadsId = listOf<EditText>(
            findViewById(R.id.from1to2),
            findViewById(R.id.from1to3),
            findViewById(R.id.from1to4),
            findViewById(R.id.from1to5),
            findViewById(R.id.from1to6),
            findViewById(R.id.from1to7),
            findViewById(R.id.from1to8),
            findViewById(R.id.from2to3),
            findViewById(R.id.from2to4),
            findViewById(R.id.from2to5),
            findViewById(R.id.from2to6),
            findViewById(R.id.from2to7),
            findViewById(R.id.from2to8),
            findViewById(R.id.from3to4),
            findViewById(R.id.from3to5),
            findViewById(R.id.from3to6),
            findViewById(R.id.from3to7),
            findViewById(R.id.from3to8),
            findViewById(R.id.from4to5),
            findViewById(R.id.from4to6),
            findViewById(R.id.from4to7),
            findViewById(R.id.from4to8),
            findViewById(R.id.from5to6),
            findViewById(R.id.from5to7),
            findViewById(R.id.from5to8),
            findViewById(R.id.from6to7),
            findViewById(R.id.from6to8),
            findViewById(R.id.from7to8),

        )

        fun getNames(): List<String>{
            val names: MutableList<String> = mutableListOf()
            for(name in namesId)
                names+=name.text.toString()
            return names
        }
        fun getName(number: Int): String{
            return namesId[number].text.toString()
        }
        fun getRoad(number: Int): String{
            return roadsId[number].text.toString()
        }
        fun getRoads():List<String>{
            val roads: MutableList<String> = mutableListOf()
            for(road in roadsId)
                roads+=road.text.toString()
            return roads
        }

        fun showNotification(title: String, message: String) {
            // Create notification
            val notificationManager = baseContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationId = System.currentTimeMillis().toInt()
            val notificationChannelId = "graph_notification"
            val notificationChannelName = "Graph notification"

            // Create a notification channel if needed
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(notificationChannelId, notificationChannelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
            }

            // Create notification
            val notification = NotificationCompat.Builder(baseContext, notificationChannelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .build()

            // Show notification
            notificationManager.notify(notificationId, notification)
        }

        fun saveImageToGallery(bitmap: Bitmap) {
            // Get the external storage directory for pictures
            val storageDir: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

            // Create a subdirectory in the pictures directory
            val appDirectory = File("${storageDir.absolutePath}/${baseContext.packageManager.getApplicationLabel(baseContext.packageManager.getApplicationInfo(baseContext.packageName, PackageManager.GET_META_DATA))}")
            appDirectory.mkdirs()

            // Generate a unique file name
            val fileName = "Graph_${System.currentTimeMillis()}.jpg"

            // Create the file
            val imageFile = File(appDirectory, fileName)
            imageFile.createNewFile()

            // Save the bitmap to the file
            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.close()

            // Add the image to the system photo gallery
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val contentUri = Uri.fromFile(imageFile)
            mediaScanIntent.data = contentUri
            baseContext.sendBroadcast(mediaScanIntent)
        }


        fun createPNG(verticles: List<String>, edges: List<Edge>): Bitmap? {
            // Create a new Bitmap and Canvas to draw on
            val bitmap = Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            // cities painter
            val paint = Paint()
            paint.color = Color.BLACK
            paint.strokeWidth = 8f
            paint.textSize = 20f

            // labels painter
            val labelPaint = Paint()
            labelPaint.color = Color.WHITE
            labelPaint.strokeWidth = 16f
            labelPaint.textSize = 20f

            // roads painter
            val roadPaint = Paint()
            roadPaint.color = Color.RED
            roadPaint.strokeWidth = 8f
            roadPaint.textSize = 20f

            // Calculate the radius and center of the circular layout
            val radius = 200f
            val centerX = 400f
            val centerY = 300f

            // Create a map of verticle positions
            val positions = mutableMapOf<String, Pair<Float, Float>>()
            for (i in verticles.indices) {
                val angle = 2 * Math.PI * i / verticles.size
                val x = centerX + (radius * cos(angle))
                val y = centerY + (radius * sin(angle))
                positions[verticles[i]] = x.toFloat() to y.toFloat()
            }

            // Draw the edges on the canvas
            for (edge in edges) {
                val fromPos = positions[edge.from.i.toString()] ?: continue
                val toPos = positions[edge.to.i.toString()] ?: continue

                canvas.drawLine(fromPos.first, fromPos.second, toPos.first, toPos.second, roadPaint) // Drawing roads

                // Calculate the label position
                val x = (fromPos.first + toPos.first) / 2
                val y = (fromPos.second + toPos.second) / 2

                // Draw the label
                canvas.drawText("${edge.distance}", x, y, labelPaint)
            }

            // Draw the verticles on the canvas
            for ((verticle, pos) in positions) {
                canvas.drawCircle(pos.first, pos.second, 20f, paint)
            }
            return bitmap
        }

        fun showBitmapPopup(context: Context, anchorView: View, bitmap: Bitmap) {
            val frameLayout = FrameLayout(context).apply {
                setBackgroundColor(Color.parseColor("#212121"))
                setPadding(16, 16, 16, 16)
            }

            val imageView = ImageView(context).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            }
            var scale = 1F
            val scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    val scaleFactor = detector.scaleFactor
                    scale *= scaleFactor
                    scale = 0.1f.coerceAtLeast(scale.coerceAtMost(5.0f))

                    imageView.imageMatrix = Matrix().apply {
                        setScale(scale, scale)
                    }
                    imageView.scaleX = scale
                    imageView.scaleY = scale
                    println(scale)
                    return true
                }
            })
            imageView.setOnTouchListener { _, event ->
                scaleGestureDetector.onTouchEvent(event)
                true
            }
            frameLayout.addView(imageView)

            val button = Button(context).apply {
                text = "Close"
                layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.BOTTOM or Gravity.END
                }
            }
            frameLayout.addView(button)

            val popupWindow = PopupWindow(frameLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            imageView.setImageBitmap(bitmap)

            button.setOnClickListener {
                popupWindow.dismiss()
            }

            popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0)
        }



        findViewById<FloatingActionButton>(R.id.run).setOnClickListener {
            val graph = Graph()

            // NORMAL
            graph.addNewEdge(getName(0),getName(1),getRoad(0).toInt())
            graph.addNewEdge(getName(0),getName(2),getRoad(1).toInt())
            graph.addNewEdge(getName(0),getName(3),getRoad(2).toInt())
            graph.addNewEdge(getName(0),getName(4),getRoad(3).toInt())
            graph.addNewEdge(getName(0),getName(5),getRoad(4).toInt())
            graph.addNewEdge(getName(0),getName(6),getRoad(5).toInt())
            graph.addNewEdge(getName(0),getName(7),getRoad(6).toInt())

            graph.addNewEdge(getName(1),getName(2),getRoad(7).toInt())
            graph.addNewEdge(getName(1),getName(3),getRoad(8).toInt())
            graph.addNewEdge(getName(1),getName(4),getRoad(9).toInt())
            graph.addNewEdge(getName(1),getName(5),getRoad(10).toInt())
            graph.addNewEdge(getName(1),getName(6),getRoad(11).toInt())
            graph.addNewEdge(getName(1),getName(7),getRoad(12).toInt())

            graph.addNewEdge(getName(2),getName(3),getRoad(13).toInt())
            graph.addNewEdge(getName(2),getName(4),getRoad(14).toInt())
            graph.addNewEdge(getName(2),getName(5),getRoad(15).toInt())
            graph.addNewEdge(getName(2),getName(6),getRoad(16).toInt())
            graph.addNewEdge(getName(2),getName(7),getRoad(17).toInt())

            graph.addNewEdge(getName(3),getName(4),getRoad(18).toInt())
            graph.addNewEdge(getName(3),getName(5),getRoad(19).toInt())
            graph.addNewEdge(getName(3),getName(6),getRoad(20).toInt())
            graph.addNewEdge(getName(3),getName(7),getRoad(21).toInt())

            graph.addNewEdge(getName(4),getName(5),getRoad(22).toInt())
            graph.addNewEdge(getName(4),getName(6),getRoad(23).toInt())
            graph.addNewEdge(getName(4),getName(7),getRoad(24).toInt())

            graph.addNewEdge(getName(5),getName(6),getRoad(25).toInt())
            graph.addNewEdge(getName(5),getName(7),getRoad(26).toInt())

            graph.addNewEdge(getName(6),getName(7),getRoad(27).toInt())


            // Second dir
            graph.addNewEdge(getName(1),getName(0),getRoad(0).toInt())
            graph.addNewEdge(getName(2),getName(0),getRoad(1).toInt())
            graph.addNewEdge(getName(3),getName(0),getRoad(2).toInt())
            graph.addNewEdge(getName(4),getName(0),getRoad(3).toInt())
            graph.addNewEdge(getName(5),getName(0),getRoad(4).toInt())
            graph.addNewEdge(getName(6),getName(0),getRoad(5).toInt())
            graph.addNewEdge(getName(7),getName(0),getRoad(6).toInt())

            graph.addNewEdge(getName(2),getName(1),getRoad(7).toInt())
            graph.addNewEdge(getName(3),getName(1),getRoad(8).toInt())
            graph.addNewEdge(getName(4),getName(1),getRoad(9).toInt())
            graph.addNewEdge(getName(5),getName(1),getRoad(10).toInt())
            graph.addNewEdge(getName(6),getName(1),getRoad(11).toInt())
            graph.addNewEdge(getName(7),getName(1),getRoad(12).toInt())

            graph.addNewEdge(getName(3),getName(2),getRoad(13).toInt())
            graph.addNewEdge(getName(4),getName(2),getRoad(14).toInt())
            graph.addNewEdge(getName(5),getName(2),getRoad(15).toInt())
            graph.addNewEdge(getName(6),getName(2),getRoad(16).toInt())
            graph.addNewEdge(getName(7),getName(2),getRoad(17).toInt())

            graph.addNewEdge(getName(4),getName(3),getRoad(18).toInt())
            graph.addNewEdge(getName(5),getName(3),getRoad(19).toInt())
            graph.addNewEdge(getName(6),getName(3),getRoad(20).toInt())
            graph.addNewEdge(getName(7),getName(3),getRoad(21).toInt())

            graph.addNewEdge(getName(5),getName(4),getRoad(22).toInt())
            graph.addNewEdge(getName(6),getName(4),getRoad(23).toInt())
            graph.addNewEdge(getName(7),getName(4),getRoad(24).toInt())

            graph.addNewEdge(getName(6),getName(5),getRoad(25).toInt())
            graph.addNewEdge(getName(7),getName(5),getRoad(26).toInt())

            graph.addNewEdge(getName(7),getName(6),getRoad(27).toInt())




            val solution = TravellingMerchantProblem().solve(getNames(),graph)

            // creating, saving and showing image also notifying user that image is saved
            createPNG(solution.first, graph.GetAllEdges())?.let { result ->
                saveImageToGallery(result)
                showNotification("Graph app","Image saved!")
                showBitmapPopup(this.applicationContext,this.findViewById(R.id.parentScroll),
                    result
                )
            }

        }


    }

}