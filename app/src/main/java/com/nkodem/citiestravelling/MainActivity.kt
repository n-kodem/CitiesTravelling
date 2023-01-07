package com.nkodem.citiestravelling

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nkodem.citiestravelling.algorithms.Edge
import com.nkodem.citiestravelling.algorithms.Graph
import com.nkodem.citiestravelling.algorithms.TravellingMerchantProblem
import java.io.File
import java.io.FileOutputStream
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random
import kotlin.random.nextInt

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

            MediaScannerConnection.scanFile(baseContext, arrayOf(imageFile.toString()),
                null, null)
        }

        fun calcDegree(startPoint: Pair<Float, Float>, endPoint: Pair<Float, Float>): Double {
            var startRadians = atan((endPoint.second - startPoint.second) / (endPoint.first - startPoint.first))
            startRadians += ((if (endPoint.first >= startPoint.first) 90 else -90) * Math.PI / 180).toFloat()
            return Math.toDegrees(startRadians.toDouble())
        }

        fun arr(canvas: Canvas, startPoint: Pair<Float, Float>, endPoint: Pair<Float, Float>){
            // Create a paint object with default values
            val paint = Paint().apply {
                color = Color.BLUE
                strokeWidth = 8f
            }
            var degree1 = calcDegree(startPoint,endPoint)

            var endX11 = (endPoint.first + ((10) * Math.cos(Math.toRadians((degree1-30)+90)))).toFloat();
            var endY11 = (endPoint.second + ((10) * Math.sin(Math.toRadians(((degree1-30)+90))))).toFloat();

            var endX22 = (endPoint.first + ((10) * Math.cos(Math.toRadians((degree1-60)+180)))).toFloat();
            var endY22 = (endPoint.second + ((10) * Math.sin(Math.toRadians(((degree1-60)+180))))).toFloat();

            canvas.drawLine(endPoint.first,endPoint.second,endX11,endY11,paint);
            canvas.drawLine(endPoint.first,endPoint.second,endX22,endY22,paint);

        }

        fun createPNG(verticles: List<String>, edges: List<Edge>, citiesTS: List<String>): Bitmap? {
            // Create a new Bitmap and Canvas to draw on
            val bitmap = Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            // cities painter
            val paint = Paint()
            paint.color = Color.GRAY
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
                canvas.drawText("  $verticle",pos.first, pos.second, labelPaint)
            }

            // Draw Travelling Salesman path
            for (verticleNum in 1 until verticles.size){
                positions[citiesTS[verticleNum-1]]?.let { startPoint ->
                    positions[citiesTS[verticleNum]]?.let { endPoint ->
                        canvas.drawLine(startPoint.first,startPoint.second,endPoint.first,endPoint.second,Paint().apply {
                            color = Color.BLUE
                            strokeWidth = 5f
                        })
                    }
                }
            }
            for (verticleNum in 0..verticles.size-2){
                positions[verticles[verticleNum]]?.let { startPoint ->
                    positions[verticles[verticleNum+1]]?.let { endPoint ->
                        arr(
                            canvas,
                            startPoint,
                            endPoint
                        )
                    }
                }
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
            val names = listOf("A${Random.nextLong()}","B${Random.nextLong()}","C${Random.nextLong()}","D${Random.nextLong()}","E${Random.nextLong()}","F${Random.nextLong()}","G${Random.nextLong()}","H${Random.nextLong()}")
            for (i in namesId.indices)
                if (namesId[i].text.toString()=="")
                    namesId[i].setText(names[i])

            for (i in roadsId.indices)
                if (roadsId[i].text.toString()=="")
                    roadsId[i].setText(Random.nextInt(1..99).toString())

            val graph = Graph()
            var counter:Int = 0
            for(i in 0..7){
                for(j in i+1..7){
                    graph.addNewEdge(getName(i), getName(j), getRoad(counter).toInt())
                    // second dir
                    graph.addNewEdge(getName(j), getName(i), getRoad(counter++).toInt())
                }
            }





            val solution = TravellingMerchantProblem().solve(getNames(),graph)

            // creating, saving and showing image also notifying user that image is saved
            createPNG(getNames()
                , graph.GetAllEdges(),solution.first)?.let { result ->
                saveImageToGallery(result)
                showNotification("Graph app","Image saved!")
                showBitmapPopup(this.applicationContext,this.findViewById(R.id.parentScroll),
                    result
                )
            }

        }


    }

}