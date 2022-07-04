package developer.abdusamid.poems

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_dialog.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DialogFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_dialog, container, false)

        val sherNomi = arguments?.getString("sherNomi", "Malumot Kelmadi")
        val toliqSher = arguments?.getString("toliqSher", "Malumot Kelmadi")

        root.sherNomiToliq.text = sherNomi
        root.toliqSher.text = toliqSher

        ulashish.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "type/palin"
            val mySharedBody = "You are body"
            val shareSub = "You busject here"
            myIntent.putExtra(Intent.EXTRA_SUBJECT, mySharedBody)
            myIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(myIntent, "Share your App"))
        }
        nushaOlish.setOnClickListener {
            val myClipboard =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val myClip: ClipData =
                ClipData.newPlainText(sherNomi, toliqSher)
            myClipboard.setPrimaryClip(myClip)
            Toast.makeText(
                requireContext(),
                "Скопировано",
                Toast.LENGTH_SHORT
            ).show()
        }
        smss.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", toliqSher)
            startActivity(intent)
        }
        return root
    }
}