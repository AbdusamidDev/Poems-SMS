package developer.abdusamid.poems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_asosiy_ekran_fragmenti.view.*
import developer.abdusamid.poems.chatch.MySharePreference
import java.util.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AsosiyEkranFragmenti : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var root: View
    private lateinit var arrayList: ArrayList<DataClassIkki>

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
        root = inflater.inflate(R.layout.fragment_asosiy_ekran_fragmenti, container, false)

        root.sevgi_sherlar.setOnClickListener {

            root.findNavController().navigate(R.id.sherlar, bundleOf("key" to "sevgi_sherlar"))


        }
        root.soginch_armon.setOnClickListener {

            root.findNavController().navigate(R.id.sherlar, bundleOf("key" to "soginch_armon"))


        }
        root.tabrik_sherlar.setOnClickListener {

            root.findNavController().navigate(R.id.sherlar, bundleOf("key" to "tabrik_sherlar"))


        }
        root.ota_ona.setOnClickListener {

            root.findNavController().navigate(R.id.sherlar, bundleOf("key" to "ota_ona"))


        }
        root.dostlik_sherlar.setOnClickListener {

            root.findNavController().navigate(R.id.sherlar, bundleOf("key" to "dostlik_sherlar"))


        }
        root.hazil.setOnClickListener {

            root.findNavController().navigate(R.id.sherlar, bundleOf("key" to "hazil"))


        }
        root.yangilar.setOnClickListener {

            root.findNavController().navigate(R.id.sherlar, bundleOf("key" to "yangilar"))


        }
        root.saqlanganlar.setOnClickListener {

            root.findNavController().navigate(R.id.sherlar, bundleOf("key" to "saqlanganlar"))

        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        MySharePreference.init(root.context)

        arrayList = ArrayList()
        arrayList.addAll(MySharePreference.contactList)

        root.saqlanganlar_soni.text = arrayList.size.toString()
        root.yangilar_soni.text = "13"
        super.onViewCreated(view, savedInstanceState)
    }
}