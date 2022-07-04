package developer.abdusamid.poems

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_sherlar.view.*
import kotlinx.android.synthetic.main.fragment_sms_jonatish_uchun.view.*
import developer.abdusamid.poems.adapters.AdapterRV
import android.util.Log
import java.util.ArrayList
import developer.abdusamid.poems.chatch.MySharePreference


class Sherlar : Fragment() {
    var arrayListSaqlanganlar = ArrayList<DataClassIkki>()


    private lateinit var key: String
    lateinit var root: View
    lateinit var adapterRV: AdapterRV
    lateinit var arrayList: ArrayList<DataClass>


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        root = inflater.inflate(R.layout.fragment_sherlar, container, false)


        key = arguments?.getString("key").toString()


        when (key) {

            "sevgi_sherlar" -> {


                root.sher_yolanishi_nomi.text = "Sevgi\nSherlar"

                loadSevgiSherlar()

                adapterRV = AdapterRV(root.context, object : AdapterRV.RvItemClick {
                    override fun onClick(dataClass: DataClass, position: Int) {

                        val bottomSheetDialog =
                            BottomSheetDialog(root.context, R.style.CustomBottomSheetDialogTheme)
                        bottomSheetDialog.setContentView(
                            layoutInflater.inflate(
                                R.layout.fragment_dialog,
                                null,
                                false
                            )
                        )


                        bottomSheetDialog.sherNomiToliq.text = arrayList[position].shernomi
                        bottomSheetDialog.toliqSher.text = arrayList[position].toliqSher

                        MySharePreference.init(root.context)

                        arrayListSaqlanganlar = ArrayList()
                        arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                        for (dataClassIkki in arrayListSaqlanganlar) {
                            if (dataClassIkki.type == "Sevgi Sherlar" && dataClassIkki.shernomi == arrayList[position].shernomi &&
                                dataClassIkki.boshti
                            ) {
                                bottomSheetDialog.saqlash.isChecked = true

                            }
                        }



                        bottomSheetDialog.smss.setOnClickListener {

                            val alertDialog = AlertDialog.Builder(root.context)
                            val dialog = alertDialog.create()

                            val dialogView = layoutInflater.inflate(
                                R.layout.fragment_sms_jonatish_uchun,
                                null,
                                false
                            )
                            dialog.setView(dialogView)

                            dialog.show()

                            dialogView.send.setOnClickListener {

                                val text = dialogView.telephone.text.toString()

                                val textToSend = arrayList[position].toliqSher


                                val sms = SmsManager.getDefault()
                                val msgStringArray = sms.divideMessage(textToSend)

                                for (s in msgStringArray) {

                                    Log.d(TAG, "STRING LENGTH: " + s.length + " " + s)
                                }



                                try {

                                    sms.sendMultipartTextMessage(
                                        text,
                                        null,
                                        msgStringArray,
                                        null,
                                        null
                                    )
                                    Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        "Failed to send message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }



                                dialog.dismiss()
                            }


                        }

                        bottomSheetDialog.saqlash.setOnCheckedChangeListener { _, isChecked ->

                            if (isChecked) {
                                Toast.makeText(root.context, "Saqlandi", Toast.LENGTH_SHORT).show()

                                val sherNomi = arrayList[position].shernomi
                                val sher = arrayList[position].sher
                                val toliqSher = arrayList[position].toliqSher

                                val dataClass1 =
                                    DataClassIkki(sherNomi, sher, toliqSher, "Sevgi Sherlar", true)

                                arrayListSaqlanganlar.add(dataClass1)

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)



                                root.rv.adapter = adapterRV


                            } else {
                                Toast.makeText(root.context, "Saqlanmadi", Toast.LENGTH_SHORT)
                                    .show()


                                val sherNomi = arrayList[position].shernomi




                                MySharePreference.init(root.context)
                                arrayListSaqlanganlar = ArrayList()
                                arrayListSaqlanganlar.addAll(MySharePreference.contactList)

                                println(arrayListSaqlanganlar)

                                for (dataClassIkki in arrayListSaqlanganlar) {
                                    if (dataClassIkki.type == "Sevgi Sherlar" && dataClassIkki.shernomi == sherNomi &&
                                        dataClassIkki.boshti
                                    ) {

                                        val index = arrayListSaqlanganlar.indexOf(dataClassIkki)

                                        arrayListSaqlanganlar.removeAt(index)

                                        break
                                    }
                                }

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)




                                root.rv.adapter = adapterRV


                            }
                        }


                        bottomSheetDialog.show()


                    }

                }, arrayList)


                root.rv.adapter = adapterRV


            }

            "soginch_armon" -> {

                root.sher_yolanishi_nomi.text = "Sog'inch\nArmon"


                loadSevgiSherlar()

                adapterRV = AdapterRV(root.context, object : AdapterRV.RvItemClick {
                    override fun onClick(dataClass: DataClass, position: Int) {

                        val bottomSheetDialog =
                            BottomSheetDialog(root.context, R.style.CustomBottomSheetDialogTheme)
                        bottomSheetDialog.setContentView(
                            layoutInflater.inflate(
                                R.layout.fragment_dialog,
                                null,
                                false
                            )
                        )


                        bottomSheetDialog.sherNomiToliq.text = arrayList[position].shernomi
                        bottomSheetDialog.toliqSher.text = arrayList[position].toliqSher

                        MySharePreference.init(root.context)

                        arrayListSaqlanganlar = ArrayList()
                        arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                        for (dataClassIkki in arrayListSaqlanganlar) {
                            if (dataClassIkki.type == "Sog'inch Armon" && dataClassIkki.shernomi == arrayList[position].shernomi &&
                                dataClassIkki.boshti
                            ) {
                                bottomSheetDialog.saqlash.isChecked = true

                            }
                        }



                        bottomSheetDialog.smss.setOnClickListener {

                            val alertDialog = AlertDialog.Builder(root.context)
                            val dialog = alertDialog.create()

                            val dialogView = layoutInflater.inflate(
                                R.layout.fragment_sms_jonatish_uchun,
                                null,
                                false
                            )
                            dialog.setView(dialogView)

                            dialog.show()

                            dialogView.send.setOnClickListener {

                                val text = dialogView.telephone.text.toString()

                                val textToSend = arrayList[position].toliqSher


                                val sms = SmsManager.getDefault()
                                val msgStringArray = sms.divideMessage(textToSend)

                                for (s in msgStringArray) {

                                    Log.d(TAG, "STRING LENGTH: " + s.length + " " + s)
                                }



                                try {

                                    sms.sendMultipartTextMessage(
                                        text,
                                        null,
                                        msgStringArray,
                                        null,
                                        null
                                    )
                                    Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        "Failed to send message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }



                                dialog.dismiss()
                            }


                        }

                        bottomSheetDialog.saqlash.setOnCheckedChangeListener { _, isChecked ->

                            if (isChecked) {
                                Toast.makeText(root.context, "Saqlandi", Toast.LENGTH_SHORT).show()

                                val sherNomi = arrayList[position].shernomi
                                val sher = arrayList[position].sher
                                val toliqSher = arrayList[position].toliqSher

                                val dataClass1 =
                                    DataClassIkki(sherNomi, sher, toliqSher, "Sog'inch Armon", true)

                                arrayListSaqlanganlar.add(dataClass1)

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)



                                root.rv.adapter = adapterRV


                            } else {
                                Toast.makeText(root.context, "Saqlanmadi", Toast.LENGTH_SHORT)
                                    .show()


                                val sherNomi = arrayList[position].shernomi




                                MySharePreference.init(root.context)
                                arrayListSaqlanganlar = ArrayList()
                                arrayListSaqlanganlar.addAll(MySharePreference.contactList)

                                println(arrayListSaqlanganlar)

                                for (dataClassIkki in arrayListSaqlanganlar) {
                                    if (dataClassIkki.type == "Sog'inch Armon" && dataClassIkki.shernomi == sherNomi &&
                                        dataClassIkki.boshti
                                    ) {

                                        val index = arrayListSaqlanganlar.indexOf(dataClassIkki)

                                        arrayListSaqlanganlar.removeAt(index)

                                        break
                                    }
                                }

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)




                                root.rv.adapter = adapterRV


                            }
                        }


                        bottomSheetDialog.show()


                    }

                }, arrayList)


                root.rv.adapter = adapterRV


            }

            "tabrik_sherlar" -> {


                root.sher_yolanishi_nomi.text = "Tabrik\nSherlar"

                loadSevgiSherlar()

                adapterRV = AdapterRV(root.context, object : AdapterRV.RvItemClick {
                    override fun onClick(dataClass: DataClass, position: Int) {

                        val bottomSheetDialog =
                            BottomSheetDialog(root.context, R.style.CustomBottomSheetDialogTheme)
                        bottomSheetDialog.setContentView(
                            layoutInflater.inflate(
                                R.layout.fragment_dialog,
                                null,
                                false
                            )
                        )


                        bottomSheetDialog.sherNomiToliq.text = arrayList[position].shernomi
                        bottomSheetDialog.toliqSher.text = arrayList[position].toliqSher

                        MySharePreference.init(root.context)

                        arrayListSaqlanganlar = ArrayList()
                        arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                        for (dataClassIkki in arrayListSaqlanganlar) {
                            if (dataClassIkki.type == "Tabrik Sherlar" && dataClassIkki.shernomi == arrayList[position].shernomi &&
                                dataClassIkki.boshti
                            ) {
                                bottomSheetDialog.saqlash.isChecked = true

                            }
                        }



                        bottomSheetDialog.smss.setOnClickListener {

                            val alertDialog = AlertDialog.Builder(root.context)
                            val dialog = alertDialog.create()

                            val dialogView = layoutInflater.inflate(
                                R.layout.fragment_sms_jonatish_uchun,
                                null,
                                false
                            )
                            dialog.setView(dialogView)

                            dialog.show()

                            dialogView.send.setOnClickListener {

                                val text = dialogView.telephone.text.toString()

                                val textToSend = arrayList[position].toliqSher


                                val sms = SmsManager.getDefault()
                                val msgStringArray = sms.divideMessage(textToSend)

                                for (s in msgStringArray) {

                                    Log.d(TAG, "STRING LENGTH: " + s.length + " " + s)
                                }



                                try {

                                    sms.sendMultipartTextMessage(
                                        text,
                                        null,
                                        msgStringArray,
                                        null,
                                        null
                                    )
                                    Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        "Failed to send message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }



                                dialog.dismiss()
                            }


                        }

                        bottomSheetDialog.saqlash.setOnCheckedChangeListener { _, isChecked ->

                            if (isChecked) {
                                Toast.makeText(root.context, "Saqlandi", Toast.LENGTH_SHORT).show()

                                val sherNomi = arrayList[position].shernomi
                                val sher = arrayList[position].sher
                                val toliqSher = arrayList[position].toliqSher

                                val dataClass1 =
                                    DataClassIkki(sherNomi, sher, toliqSher, "Tabrik Sherlar", true)

                                arrayListSaqlanganlar.add(dataClass1)

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)



                                root.rv.adapter = adapterRV


                            } else {
                                Toast.makeText(root.context, "Saqlanmadi", Toast.LENGTH_SHORT)
                                    .show()


                                val sherNomi = arrayList[position].shernomi




                                MySharePreference.init(root.context)
                                arrayListSaqlanganlar = ArrayList()
                                arrayListSaqlanganlar.addAll(MySharePreference.contactList)

                                println(arrayListSaqlanganlar)

                                for (dataClassIkki in arrayListSaqlanganlar) {
                                    if (dataClassIkki.type == "Tabrik Sherlar" && dataClassIkki.shernomi == sherNomi &&
                                        dataClassIkki.boshti
                                    ) {

                                        val index = arrayListSaqlanganlar.indexOf(dataClassIkki)

                                        arrayListSaqlanganlar.removeAt(index)

                                        break
                                    }
                                }

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)




                                root.rv.adapter = adapterRV


                            }
                        }


                        bottomSheetDialog.show()


                    }

                }, arrayList)


                root.rv.adapter = adapterRV


            }

            "ota_ona" -> {


                root.sher_yolanishi_nomi.text = "Ota Ona Haqida"

                loadSevgiSherlar()

                adapterRV = AdapterRV(root.context, object : AdapterRV.RvItemClick {
                    override fun onClick(dataClass: DataClass, position: Int) {

                        val bottomSheetDialog =
                            BottomSheetDialog(root.context, R.style.CustomBottomSheetDialogTheme)
                        bottomSheetDialog.setContentView(
                            layoutInflater.inflate(
                                R.layout.fragment_dialog,
                                null,
                                false
                            )
                        )


                        bottomSheetDialog.sherNomiToliq.text = arrayList[position].shernomi
                        bottomSheetDialog.toliqSher.text = arrayList[position].toliqSher

                        MySharePreference.init(root.context)

                        arrayListSaqlanganlar = ArrayList()
                        arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                        for (dataClassIkki in arrayListSaqlanganlar) {
                            if (dataClassIkki.type == "Ota Ona Haqida" && dataClassIkki.shernomi == arrayList[position].shernomi &&
                                dataClassIkki.boshti
                            ) {
                                bottomSheetDialog.saqlash.isChecked = true

                            }
                        }



                        bottomSheetDialog.smss.setOnClickListener {

                            val alertDialog = AlertDialog.Builder(root.context)
                            val dialog = alertDialog.create()

                            val dialogView = layoutInflater.inflate(
                                R.layout.fragment_sms_jonatish_uchun,
                                null,
                                false
                            )
                            dialog.setView(dialogView)

                            dialog.show()

                            dialogView.send.setOnClickListener {

                                val text = dialogView.telephone.text.toString()

                                val textToSend = arrayList[position].toliqSher


                                val sms = SmsManager.getDefault()
                                val msgStringArray = sms.divideMessage(textToSend)

                                for (s in msgStringArray) {

                                    Log.d(TAG, "STRING LENGTH: " + s.length + " " + s)
                                }



                                try {

                                    sms.sendMultipartTextMessage(
                                        text,
                                        null,
                                        msgStringArray,
                                        null,
                                        null
                                    )
                                    Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        "Failed to send message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }



                                dialog.dismiss()
                            }


                        }

                        bottomSheetDialog.saqlash.setOnCheckedChangeListener { _, isChecked ->

                            if (isChecked) {
                                Toast.makeText(root.context, "Saqlandi", Toast.LENGTH_SHORT).show()

                                val sherNomi = arrayList[position].shernomi
                                val sher = arrayList[position].sher
                                val toliqSher = arrayList[position].toliqSher

                                val dataClass1 =
                                    DataClassIkki(sherNomi, sher, toliqSher, "Ota Ona Haqida", true)

                                arrayListSaqlanganlar.add(dataClass1)

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)



                                root.rv.adapter = adapterRV


                            } else {
                                Toast.makeText(root.context, "Saqlanmadi", Toast.LENGTH_SHORT)
                                    .show()


                                val sherNomi = arrayList[position].shernomi




                                MySharePreference.init(root.context)
                                arrayListSaqlanganlar = ArrayList()
                                arrayListSaqlanganlar.addAll(MySharePreference.contactList)

                                println(arrayListSaqlanganlar)

                                for (dataClassIkki in arrayListSaqlanganlar) {
                                    if (dataClassIkki.type == "Ota Ona Haqida" && dataClassIkki.shernomi == sherNomi &&
                                        dataClassIkki.boshti
                                    ) {

                                        val index = arrayListSaqlanganlar.indexOf(dataClassIkki)

                                        arrayListSaqlanganlar.removeAt(index)

                                        break
                                    }
                                }

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)




                                root.rv.adapter = adapterRV


                            }
                        }


                        bottomSheetDialog.show()


                    }

                }, arrayList)


                root.rv.adapter = adapterRV


            }

            "dostlik_sherlar" -> {


                root.sher_yolanishi_nomi.text = "Dostlik\nSherlar"

                loadSevgiSherlar()

                adapterRV = AdapterRV(root.context, object : AdapterRV.RvItemClick {
                    override fun onClick(dataClass: DataClass, position: Int) {

                        val bottomSheetDialog =
                            BottomSheetDialog(root.context, R.style.CustomBottomSheetDialogTheme)
                        bottomSheetDialog.setContentView(
                            layoutInflater.inflate(
                                R.layout.fragment_dialog,
                                null,
                                false
                            )
                        )


                        bottomSheetDialog.sherNomiToliq.text = arrayList[position].shernomi
                        bottomSheetDialog.toliqSher.text = arrayList[position].toliqSher

                        MySharePreference.init(root.context)

                        arrayListSaqlanganlar = ArrayList()
                        arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                        for (dataClassIkki in arrayListSaqlanganlar) {
                            if (dataClassIkki.type == "Do'stlik Sherlar" && dataClassIkki.shernomi == arrayList[position].shernomi &&
                                dataClassIkki.boshti
                            ) {
                                bottomSheetDialog.saqlash.isChecked = true

                            }
                        }



                        bottomSheetDialog.smss.setOnClickListener {

                            val alertDialog = AlertDialog.Builder(root.context)
                            val dialog = alertDialog.create()

                            val dialogView = layoutInflater.inflate(
                                R.layout.fragment_sms_jonatish_uchun,
                                null,
                                false
                            )
                            dialog.setView(dialogView)

                            dialog.show()

                            dialogView.send.setOnClickListener {

                                val text = dialogView.telephone.text.toString()

                                val textToSend = arrayList[position].toliqSher


                                val sms = SmsManager.getDefault()
                                val msgStringArray = sms.divideMessage(textToSend)

                                for (s in msgStringArray) {

                                    Log.d(TAG, "STRING LENGTH: " + s.length + " " + s)
                                }



                                try {

                                    sms.sendMultipartTextMessage(
                                        text,
                                        null,
                                        msgStringArray,
                                        null,
                                        null
                                    )
                                    Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        "Failed to send message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }



                                dialog.dismiss()
                            }


                        }

                        bottomSheetDialog.saqlash.setOnCheckedChangeListener { _, isChecked ->

                            if (isChecked) {
                                Toast.makeText(root.context, "Saqlandi", Toast.LENGTH_SHORT).show()

                                val sherNomi = arrayList[position].shernomi
                                val sher = arrayList[position].sher
                                val toliqSher = arrayList[position].toliqSher

                                val dataClass1 = DataClassIkki(
                                    sherNomi,
                                    sher,
                                    toliqSher,
                                    "Do'stlik Sherlar",
                                    true
                                )

                                arrayListSaqlanganlar.add(dataClass1)

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)



                                root.rv.adapter = adapterRV


                            } else {
                                Toast.makeText(root.context, "Saqlanmadi", Toast.LENGTH_SHORT)
                                    .show()


                                val sherNomi = arrayList[position].shernomi




                                MySharePreference.init(root.context)
                                arrayListSaqlanganlar = ArrayList()
                                arrayListSaqlanganlar.addAll(MySharePreference.contactList)

                                println(arrayListSaqlanganlar)

                                for (dataClassIkki in arrayListSaqlanganlar) {
                                    if (dataClassIkki.type == "Do'stlik Sherlar" && dataClassIkki.shernomi == sherNomi &&
                                        dataClassIkki.boshti
                                    ) {

                                        val index = arrayListSaqlanganlar.indexOf(dataClassIkki)

                                        arrayListSaqlanganlar.removeAt(index)

                                        break
                                    }
                                }

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)




                                root.rv.adapter = adapterRV


                            }
                        }


                        bottomSheetDialog.show()


                    }

                }, arrayList)


                root.rv.adapter = adapterRV


            }

            "hazil" -> {


                root.sher_yolanishi_nomi.text = "Hazil"

                loadSevgiSherlar()

                adapterRV = AdapterRV(root.context, object : AdapterRV.RvItemClick {
                    override fun onClick(dataClass: DataClass, position: Int) {

                        val bottomSheetDialog =
                            BottomSheetDialog(root.context, R.style.CustomBottomSheetDialogTheme)
                        bottomSheetDialog.setContentView(
                            layoutInflater.inflate(
                                R.layout.fragment_dialog,
                                null,
                                false
                            )
                        )


                        bottomSheetDialog.sherNomiToliq.text = arrayList[position].shernomi
                        bottomSheetDialog.toliqSher.text = arrayList[position].toliqSher

                        MySharePreference.init(root.context)

                        arrayListSaqlanganlar = ArrayList()
                        arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                        for (dataClassIkki in arrayListSaqlanganlar) {
                            if (dataClassIkki.type == "Hazil" && dataClassIkki.shernomi == arrayList[position].shernomi &&
                                dataClassIkki.boshti
                            ) {
                                bottomSheetDialog.saqlash.isChecked = true

                            }
                        }



                        bottomSheetDialog.smss.setOnClickListener {

                            val alertDialog = AlertDialog.Builder(root.context)
                            val dialog = alertDialog.create()

                            val dialogView = layoutInflater.inflate(
                                R.layout.fragment_sms_jonatish_uchun,
                                null,
                                false
                            )
                            dialog.setView(dialogView)

                            dialog.show()

                            dialogView.send.setOnClickListener {

                                val text = dialogView.telephone.text.toString()

                                val textToSend = arrayList[position].toliqSher


                                val sms = SmsManager.getDefault()
                                val msgStringArray = sms.divideMessage(textToSend)

                                for (s in msgStringArray) {

                                    Log.d(TAG, "STRING LENGTH: " + s.length + " " + s)
                                }



                                try {

                                    sms.sendMultipartTextMessage(
                                        text,
                                        null,
                                        msgStringArray,
                                        null,
                                        null
                                    )
                                    Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        "Failed to send message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }



                                dialog.dismiss()
                            }


                        }

                        bottomSheetDialog.saqlash.setOnCheckedChangeListener { _, isChecked ->

                            if (isChecked) {
                                Toast.makeText(root.context, "Saqlandi", Toast.LENGTH_SHORT).show()

                                val sherNomi = arrayList[position].shernomi
                                val sher = arrayList[position].sher
                                val toliqSher = arrayList[position].toliqSher

                                val dataClass1 =
                                    DataClassIkki(sherNomi, sher, toliqSher, "Hazil", true)

                                arrayListSaqlanganlar.add(dataClass1)

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)



                                root.rv.adapter = adapterRV


                            } else {
                                Toast.makeText(root.context, "Saqlanmadi", Toast.LENGTH_SHORT)
                                    .show()


                                val sherNomi = arrayList[position].shernomi




                                MySharePreference.init(root.context)
                                arrayListSaqlanganlar = ArrayList()
                                arrayListSaqlanganlar.addAll(MySharePreference.contactList)

                                println(arrayListSaqlanganlar)

                                for (dataClassIkki in arrayListSaqlanganlar) {
                                    if (dataClassIkki.type == "Hazil" && dataClassIkki.shernomi == sherNomi &&
                                        dataClassIkki.boshti
                                    ) {

                                        val index = arrayListSaqlanganlar.indexOf(dataClassIkki)

                                        arrayListSaqlanganlar.removeAt(index)

                                        break
                                    }
                                }

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)




                                root.rv.adapter = adapterRV


                            }
                        }


                        bottomSheetDialog.show()


                    }

                }, arrayList)


                root.rv.adapter = adapterRV


            }


            "yangilar" -> {


                root.sher_yolanishi_nomi.text = "Yangilar"

                loadSevgiSherlar()

                adapterRV = AdapterRV(root.context, object : AdapterRV.RvItemClick {
                    override fun onClick(dataClass: DataClass, position: Int) {

                        val bottomSheetDialog =
                            BottomSheetDialog(root.context, R.style.CustomBottomSheetDialogTheme)
                        bottomSheetDialog.setContentView(
                            layoutInflater.inflate(
                                R.layout.fragment_dialog,
                                null,
                                false
                            )
                        )


                        bottomSheetDialog.sherNomiToliq.text = arrayList[position].shernomi
                        bottomSheetDialog.toliqSher.text = arrayList[position].toliqSher

                        MySharePreference.init(root.context)

                        arrayListSaqlanganlar = ArrayList()
                        arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                        for (dataClassIkki in arrayListSaqlanganlar) {
                            if (dataClassIkki.type == "Yangilar" && dataClassIkki.shernomi == arrayList[position].shernomi &&
                                dataClassIkki.boshti
                            ) {
                                bottomSheetDialog.saqlash.isChecked = true

                            }
                        }



                        bottomSheetDialog.smss.setOnClickListener {

                            val alertDialog = AlertDialog.Builder(root.context)
                            val dialog = alertDialog.create()

                            val dialogView = layoutInflater.inflate(
                                R.layout.fragment_sms_jonatish_uchun,
                                null,
                                false
                            )
                            dialog.setView(dialogView)

                            dialog.show()

                            dialogView.send.setOnClickListener {

                                val text = dialogView.telephone.text.toString()

                                val textToSend = arrayList[position].toliqSher


                                val sms = SmsManager.getDefault()
                                val msgStringArray = sms.divideMessage(textToSend)

                                for (s in msgStringArray) {

                                    Log.d(TAG, "STRING LENGTH: " + s.length + " " + s)
                                }



                                try {

                                    sms.sendMultipartTextMessage(
                                        text,
                                        null,
                                        msgStringArray,
                                        null,
                                        null
                                    )
                                    Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        "Failed to send message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }



                                dialog.dismiss()
                            }


                        }

                        bottomSheetDialog.saqlash.setOnCheckedChangeListener { _, isChecked ->

                            if (isChecked) {
                                Toast.makeText(root.context, "Saqlandi", Toast.LENGTH_SHORT).show()

                                val sherNomi = arrayList[position].shernomi
                                val sher = arrayList[position].sher
                                val toliqSher = arrayList[position].toliqSher

                                val dataClass1 =
                                    DataClassIkki(sherNomi, sher, toliqSher, "Yangilar", true)

                                arrayListSaqlanganlar.add(dataClass1)

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)



                                root.rv.adapter = adapterRV


                            } else {
                                Toast.makeText(root.context, "Saqlanmadi", Toast.LENGTH_SHORT)
                                    .show()


                                val sherNomi = arrayList[position].shernomi




                                MySharePreference.init(root.context)
                                arrayListSaqlanganlar = ArrayList()
                                arrayListSaqlanganlar.addAll(MySharePreference.contactList)

                                println(arrayListSaqlanganlar)

                                for (dataClassIkki in arrayListSaqlanganlar) {
                                    if (dataClassIkki.type == "Yangilar" && dataClassIkki.shernomi == sherNomi &&
                                        dataClassIkki.boshti
                                    ) {

                                        val index = arrayListSaqlanganlar.indexOf(dataClassIkki)

                                        arrayListSaqlanganlar.removeAt(index)

                                        break
                                    }
                                }

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)




                                root.rv.adapter = adapterRV


                            }
                        }


                        bottomSheetDialog.show()


                    }

                }, arrayList)


                root.rv.adapter = adapterRV


            }


            "saqlanganlar" -> {

                root.sher_yolanishi_nomi.text = "Saqlanganlar"
                MySharePreference.init(root.context)

                arrayList = ArrayList()

                arrayListSaqlanganlar = ArrayList()
                arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                for (dataClassIkki in arrayListSaqlanganlar) {
                    arrayList.add(
                        DataClass(
                            dataClassIkki.shernomi,
                            dataClassIkki.sher,
                            dataClassIkki.toliqSher
                        )
                    )
                }

                if (arrayListSaqlanganlar.isEmpty())
                    Toast.makeText(root.context, "Bo'sh", Toast.LENGTH_SHORT).show()



                adapterRV = AdapterRV(root.context, object : AdapterRV.RvItemClick {
                    override fun onClick(dataClass: DataClass, position: Int) {

                        val bottomSheetDialog =
                            BottomSheetDialog(root.context, R.style.CustomBottomSheetDialogTheme)
                        bottomSheetDialog.setContentView(
                            layoutInflater.inflate(
                                R.layout.fragment_dialog,
                                null,
                                false
                            )
                        )

                        println(arrayListSaqlanganlar)

                        bottomSheetDialog.sherNomiToliq.text =
                            arrayListSaqlanganlar[position].shernomi
                        bottomSheetDialog.toliqSher.text = arrayListSaqlanganlar[position].toliqSher



                        bottomSheetDialog.smss.setOnClickListener {

                            val alertDialog = AlertDialog.Builder(root.context)
                            val dialog = alertDialog.create()

                            val dialogView = layoutInflater.inflate(
                                R.layout.fragment_sms_jonatish_uchun,
                                null,
                                false
                            )
                            dialog.setView(dialogView)

                            dialog.show()

                            dialogView.send.setOnClickListener {

                                val text = dialogView.telephone.text.toString()

                                val textToSend = arrayListSaqlanganlar[position].toliqSher


                                val sms = SmsManager.getDefault()
                                val msgStringArray = sms.divideMessage(textToSend)

                                for (s in msgStringArray) {
                                    //this will say 67 and then the remainder
                                    Log.d(TAG, "STRING LENGTH: " + s.length + " " + s)
                                }



                                try {

                                    sms.sendMultipartTextMessage(
                                        text,
                                        null,
                                        msgStringArray,
                                        null,
                                        null
                                    )
                                    Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        "Failed to send message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }



                                dialog.dismiss()
                            }


                        }

                        bottomSheetDialog.saqlash.isChecked = true

                        bottomSheetDialog.saqlash.setOnCheckedChangeListener { _, isChecked ->

                            MySharePreference.init(root.context)

                            if (!isChecked) {
                                Toast.makeText(root.context, "O'chirildi", Toast.LENGTH_SHORT)
                                    .show()

                                arrayListSaqlanganlar = ArrayList()
                                arrayListSaqlanganlar.addAll(MySharePreference.contactList)


                                val sherNomi = arrayListSaqlanganlar[position].shernomi
                                val sher = arrayListSaqlanganlar[position].sher
                                val toliqSher = arrayListSaqlanganlar[position].toliqSher


                                for (dataClass1 in arrayListSaqlanganlar) {
                                    if (dataClass1.shernomi == sherNomi && dataClass1.sher == sher && dataClass1.toliqSher == toliqSher) {

                                        val index = arrayListSaqlanganlar.indexOf(dataClass1)

                                        arrayListSaqlanganlar.removeAt(index)

                                        break
                                    }
                                }

                                MySharePreference.contactList = arrayListSaqlanganlar
                                adapterRV.notifyItemInserted(arrayListSaqlanganlar.size)

                                bottomSheetDialog.dismiss()
                                root.rv.adapter = adapterRV


                            }

                        }


                        bottomSheetDialog.show()


                    }

                }, arrayList)


                root.rv.adapter = adapterRV


            }

        }



        root.orqaga_arrow.setOnClickListener {

            findNavController().popBackStack()

        }

        return root
    }


    private fun loadSevgiSherlar() {
        arrayList = ArrayList()
        arrayList.add(
            DataClass(
                "QARO KO'ZUM",
                "Qaro ko'zum, kelu mardumlug' emdi fan qilg'il,\n" +
                        "Ko'zum qarosida mardum kibi vatan qilg'il.",
                "Qaro ko'zum, kelu mardumlug' emdi fan qilg'il,\n" +
                        "Ko'zum qarosida mardum kibi vatan qilg'il.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Yuzung guliga ko'ngul ravzasin yasa gulshan,\n" +
                        "Qading niholig'a jon gulshanin chaman qilg'il.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Takovaringg'a bag'ir qonidin hino bog'la,\n" +
                        "Itingg'a g'amzada jon rishtasin rasan qilg'il.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Firoq tog'ida topilsa tufrog'im, ey charx,\n" +
                        "Xamir etib yana ul tog'da ko'hkan qilg'il.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Yuzung visolig'a yetsun desang ko'ngullarni,\n" +
                        "Sochingni boshdin-ayog' chin ila shikan qilg'il.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Xazon sipohiga, ey bog'bon, emas mone'\n" +
                        "Bu bog' tomida gar ignadin tikan qilg'il.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Yuzida terni ko'rub o'lsam, ey rafiq, meni\n" +
                        "Gulob ila yuvu gul bargidin kafan qilg'il.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Navoiy, anjumani shavq jon aro tuzsang,\n" +
                        "Aning boshog'lig' o'qin sham'i anjuman qilg'il."
            )
        )


        arrayList.add(
            DataClass(
                "ORAZIN YOPQACH KO'ZIMDIN...",
                "Orazin yopqach ko'zumdin sochilur har lahza yosh,\n" +
                        "O'ylakim paydo bo'lur yulduz, nihon bo'lg'ach quyosh.",
                "Orazin yopqach ko'zumdin sochilur har lahza yosh,\n" +
                        "O'ylakim paydo bo'lur yulduz, nihon bo'lg'ach quyosh.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Qut bir bodomu yerim go'shan mehrob edi,\n" +
                        "G'orati din etti nogah bir baloliq ko'zu qosh.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Bu damodam ohim ifsho aylar ul oy ishqini,\n" +
                        "Subhnung bot-bot dami andog'ki aylar mehr fosh.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Bo'sae qilmas muruvvat, asru qattiqdur labing,\n" +
                        "Desam og'zi ichra aytur la'l ham bor nav' tosh.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Novaking ko'nglimga kirgach jon talashmoq bu ekin,\n" +
                        "Kim qilur paykonini ko'nglum bila jonim talosh.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Umri jovid istasang fard o'lki, bo'ston Xizridur,\n" +
                        "Sarvkim da'b ayladi ozodaliq birla maosh.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Qoshi ollinda Navoiy bersa jon, ayb etmangiz,\n" +
                        "Gar budur mehrob, bir-bir qo'ygusidir barcha bosh.\n" +
                        "\n"
            )
        )

        arrayList.add(
            DataClass(
                " O'N SAKKIZ YOSH HAYRATLARI",
                "O`n sakkiz ming olam oshubi agar boshindadur,\n" +
                        "Ne ajab, chun sarvinozim o`n sakkiz yoshindadur.",
                "O`n sakkiz ming olam oshubi agar boshindadur,\n" +
                        "Ne ajab, chun sarvinozim o`n sakkiz yoshindadur.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Desa bo`lg`aykim, yana ham o`n sakkiz yil husni bor,\n" +
                        "O`n sakkiz yoshina muncha fitnakim boshinadur.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "O`n sakkiz yil dema, yuz sakson yil o`lsa, uldurur,\n" +
                        "Husn shohi, ul balolarkim, ko`zu qoshinadur.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Hayrat etmon husni naqshidaki, har hayratki, bor,\n" +
                        "Barchasi ezid taolo sun`i naqqoshinadur.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Tan anga siymu ichina tosh muzmar ko`nglidin,\n" +
                        "Aqlg`a yuz hayrat, ul oyning ichu toshinadur.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "May ketur, ey mug`ki, yuz hayrat aro qolmish Masih,\n" +
                        "Bul ajablarkim, bu eski dayr xuffoshindadur.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "To Navoiy to`kti ul oy furqatidin bahri ashk,\n" +
                        "Har qachon boqsang, quyosh aksi aning yoshindadur."
            )
        )


        arrayList.add(
            DataClass(
                "MUBTALO BO'LDIM SANGA",
                "Ko'rgali husnungni zoru mubtalo bo'ldum sanga,\n" +
                        "Ne balolig' kun edikim, oshno bo'ldum sanga.",
                "Ko'rgali husnungni zoru mubtalo bo'ldum sanga,\n" +
                        "Ne balolig' kun edikim, oshno bo'ldum sanga.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Har necha dedimki kun-kundin uzay sendin ko'ngul,\n" +
                        "Vahki, kun-kundin batarrak mubtalo bo'ldum sanga.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Men qachon dedim: \"Vafo qilg'il manga\" zulm aylading,\n" +
                        "Sen qachon deding: \"Fido bo'lg'il manga\" bo'ldim sanga.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Qay pari paykarga dersen telba bo'ldung bu sifat,\n" +
                        "Ey pari paykar, ne qilsang qil manga, bo'ldum sanga.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ey ko'ngul, tarki nasihat aylading ovora bo'l,\n" +
                        "Yuz balo yetmaski, men ham bir balo bo'ldum sanga.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Jomi Jam birla Xizr suyi nasibimdur mudom,\n" +
                        "Soqiyo, to tarki joh aylab gado bo'ldum sanga.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "G'ussa changidin navoe topmadim ushshoq aro,\n" +
                        "To Navoiydek asiru benavo bo'ldum sanga."
            )
        )

        arrayList.add(
            DataClass(
                "LABLARINGKIM HAYF ERUR...",
                "Lablaringkim hayf erur teng tutmoq oni qand ila,\n" +
                        "Sindirur yuz qand bozorini shakkar xand ila.",
                "Lablaringkim hayf erur teng tutmoq oni qand ila,\n" +
                        "Sindirur yuz qand bozorini shakkar xand ila.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Toki hayronmen senga nomus ila itmish ko'ngul,\n" +
                        "Telba yanglig'kim, qochar el g'ofil o'lg'ach band ila.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Odam ul soatki jannat ichra avlodin ko'rar,\n" +
                        "Ne quvong'ay dam-badam sen nozanin farzand ila.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Qo'y nasihat, zohido, o'tlug' damimdin vahm qil,\n" +
                        "Telba it imkoni yo'qturkim, sog'alg'ay pand ila.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Hojatingni elga arz etmakka hojat bo'lmasun,\n" +
                        "Xush chiqishsang lahzani bu zori hojatmand ila.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Lablaring hajrinda yur parkandkim bo'lmish ko'ngul,\n" +
                        "La'l erur mahlul qon o'rnig'a har parkand ila.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Chun Navoiy ko'ngli sindi, emdi lutfing ne asig'?\n" +
                        "Kim ushatsa shishani bitmas yana payvand ila."
            )
        )
        arrayList.add(
            DataClass(
                "OSHIQ O'LDUM",
                "Oshiq o'ldum, bilmadim yor o'zgalarga yor emish,\n" +
                        "Olloh-olloh, ishq aro mundoq balolar bor emish.",
                "Oshiq o'ldum, bilmadim yor o'zgalarga yor emish,\n" +
                        "Olloh-olloh, ishq aro mundoq balolar bor emish.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Qaddig'a el mayli bo'lg'ondin ko'ngul ozurdadur,\n" +
                        "Ul alifdin zorlarning hosili ozor emish.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Elga novak urdi, men o'ldim erur bu turfakim,\n" +
                        "Jonim etgan resh el bag'rig'a kirgan xor emish.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Rishtakim, muhlik yaram og'zig'a tiktim angladim,\n" +
                        "Kim kafan jinsi qirog'idin suvurgan tor emish.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ko'yi devoridin og'riq tang'a tushgan soyadek,\n" +
                        "Sel g'amidin emdi soya o'rnig'a dildor emish.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Jong'a taxvif ayladim tig'i halokidin aning,\n" +
                        "Bilmadim bu ishdin ul o'lguncha minnatdor emish.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ey Navoiy, xo'blarni ko'rma osonlig' bilan,\n" +
                        "Kim biravkim soldi ko'z, uzmak ko'ngul dushvor emish."
            )
        )
        arrayList.add(
            DataClass(
                "ISTADIM",
                "Qon yutub umri jahon ahlida bir yor istadim,\n" +
                        "Lekin ul kamrak topildi, garchi bisyor istadim.",
                "Qon yutub umri jahon ahlida bir yor istadim,\n" +
                        "Lekin ul kamrak topildi, garchi bisyor istadim.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Kimga kim jonim fido aylab sog'indim dam-badam,\n" +
                        "Ermas erdi yorliqda chun vafodor istadim.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Bilmadim olam elida yo'qturur mutlaq vafo,\n" +
                        "Vahki, umri ulcha yo'qtur sog'inib yor istadim.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ulki, topilmas bashar jinsida vah g'aflat ko'rung,\n" +
                        "Kim pari xaylida men devonai zor istadim.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Sirri ishqimni ko'ngul ko'z birla fosh etmak ne tong,\n" +
                        "Qalbu tardomanni men chun sohib asror istadim.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Shayx birla xonaqahdin chun yorug'luq topmadim,\n" +
                        "Dayr piri xizmatig'a ko'yi xammor istadim.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ey Navoiy, chun rafiqi topmadim, bu g'ussadin,\n" +
                        "O'zni bekaslik balosig'a giriftor istadim."
            )
        )
        arrayList.add(
            DataClass(
                "MENI MEN ISTAGAN KISHI...",
                "Meni men istagan o'z suhbatiga arjumand etmas,\n" +
                        "Meni istar kishining suhbatin ko'nglum pisand etmas.",
                "Meni men istagan o'z suhbatiga arjumand etmas,\n" +
                        "Meni istar kishining suhbatin ko'nglum pisand etmas.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ne bahra topqamen andinki, mendin istagay bahra,\n" +
                        "Chu ulkim bahrai andin tilarmen bahramand etmas.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Netay huru pari bazminki, qatlim yo hayotimg'a,\n" +
                        "Ayon ul zahr chashm aylab nihon, bu no'shxand etmas.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Kerakmas oy ila kun shaklikim, husnu malohatdin,\n" +
                        "Ichim ul chok-chok etmas, tanim ul band-band etmas.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Kerak o'z chobuki majnunvashi qotil shiorimkim,\n" +
                        "Buzug' ko'nglumdin o'zga yerga javloni samand etmas.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ko'ngul uz charx zolidin, firibin yemakim, oxir\n" +
                        "Ajal sarrishtasidin o'zga bo'ynungg'a kamand etmas.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ul oy o'tlug' yuzin ochsa, Navoiy tegmasun deb ko'z,\n" +
                        "Muhabbat tuxmidin o'zga ul o't uzra sipand etmas."
            )
        )
        arrayList.add(
            DataClass(
                "KECHA KELGUMDIR DEBON…",
                "Kecha kelgumdir debon ul sarvi gulro' kelmadi,\n" +
                        "Ko'zlarimga kecha tong otquncha uyqu kelmadi.",
                "Kecha kelgumdir debon ul sarvi gulro' kelmadi,\n" +
                        "Ko'zlarimga kecha tong otquncha uyqu kelmadi.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Lahza-lahza chiqtimu chektim yo'lida intizor,\n" +
                        "Keldi jon og'zimg'avu ul sho'xi badxo' kelmadi.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Orazidek oydin erkanda gar etti ehtiyot,\n" +
                        "Ro'zgorimdek ham o'lg'onda qorong'u kelmadi.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ul parivash hajridinkim yig'ladim devonavor,\n" +
                        "Kimsa bormukim anga ko'rganda kulgu kelmadi.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ko'zlarindin necha suv kelgay deb o'lturmang meni,\n" +
                        "Kim bori qon erdi kelgan bu kecha suv kelmadi.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Tolibi sodiq topilmas, yo'qsakim qo'ydi qadam,\n" +
                        "Yo'lg'akim avval qadam ma'shuqa o'tro' kelmadi.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ey Navoiy, boda birla xurram et ko'nglung uyin,\n" +
                        "Ne uchunkim boda kelgan uyga qayg'u kelmadi."
            )
        )
        arrayList.add(
            DataClass(
                "JONG'A CHUN DERMEN",
                "Jong’a chun dermen: «Ne erdi o’lmakim kayfiyati?»\n" +
                        "Derki: «Bois bo’ldi jism ichra marazning shiddati».",
                "Jong’a chun dermen: «Ne erdi o’lmakim kayfiyati?»\n" +
                        "Derki: «Bois bo’ldi jism ichra marazning shiddati».\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Jismdin so’rsamki: «Bu za’fingg’a ne erdi sabab?»\n" +
                        "Der: «Anga bo’ldi sabab o’tluq bag’irning hirqati».\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Chun bag’irdin so’rdum, aytur: «Andin o’t tushti manga\n" +
                        "Kim, ko’ngulga shu’la soldi ishq barqi ofati».\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ko’ngluma qilsam g’azab, ayturki: «Ko’zdindur gunah,\n" +
                        "Ko’rmayin ul tushmadi bizga bu ishning tuhmati».\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ko’zga chun dermenki: «Ey, tardomani yuzi qaro,\n" +
                        "Sendin o’lmish telba ko’nglumning baloyu vahshati».\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Yig’lab aytur ko’zki: «Yo’q erdi manga ham ixtiyor\n" +
                        "Ki, ko’rundi nogahon ul sho’xi mahvash tal’ati».\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ey Navoiy, barcha o’z uzrin dedi, o’lguncha kuy\n" +
                        "Kim, sanga ishq o’ti-o’q ermish azalning qismati."
            )
        )
        arrayList.add(
            DataClass(
                "XIL'ATIN TO AYLAMISH…",
                "Xil’atin to aylamish jonon qizil, sorig’, yashil,\n" +
                        "Shu’layi ohim chiqar har yon qizil, sorig’, yashil.",
                "Xil’atin to aylamish jonon qizil, sorig’, yashil,\n" +
                        "Shu’layi ohim chiqar har yon qizil, sorig’, yashil.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Gulshan ettim ishq sahrosin samumi ohdin\n" +
                        "Kim, esar ul dasht aro har yon qizil, sorig’, yashil.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Shishadek ko’nglumdadur gulzori husnung yodidin,\n" +
                        "Tobdonning aksidek alvon qizil, sorig’, yashil.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Orazu xoling bila xatting xayolidin erur\n" +
                        "Ko’zlarimning ollida davron qizil, sorig’, yashil.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "La’lgun may tutqil oltun jom birla sabzada\n" +
                        "Kim, bulardin yaxshi yo’q imkon qizil, sorig’, yashil.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Faqr aro berangliq dushvor erur behad, valek\n" +
                        "Xirqada tikmak erur oson qizil, sorig’, yashil.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ey Navoiy, oltinu shingarfu zangor istama,\n" +
                        "Bo’ldi naming rangidin devon qizil, sorig’, yashil."
            )
        )
        arrayList.add(
            DataClass(
                "ISTANGIZ",
                "Istaganlar, bizni sahroi baloda istangiz,\n" +
                        "Vodiyi hijron ila dashti fanoda istangiz.",
                "Istaganlar, bizni sahroi baloda istangiz,\n" +
                        "Vodiyi hijron ila dashti fanoda istangiz.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Vomiqu, Farhodu Majnundeklar ul vodiy aro\n" +
                        "Bo'lsalar paydo, meni ham ul aroda istangiz.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Yuz alarning ishqicha dardu, balou g'ussag'a\n" +
                        "Tolib el boshig'a kelgan mojaroda istangiz.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Eyki, istarsiz savodul vajh fiddorayidin,\n" +
                        "Boxabar bo'lmoq meni yuzi qaroda istangiz.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ko'nglim ul zulf ichradur, zinhor ishqim sharhini\n" +
                        "Istamang men telbada, ul mubtaloda istangiz.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Nuqta yanglig'kim, vafo uzra qilur kotib raqam,\n" +
                        "Ishq o'tining dog'ini ahli vafoda istangiz.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Og'zi shavqidin Navoiy itti, oni istar el,\n" +
                        "Yo adam dashtida, yo mulki fanoda istangiz."
            )
        )
        arrayList.add(
            DataClass(
                "KELGAY", "Ne kun o'lg'ayki, nigorim kelgay,\n" +
                        "Bog'i umrumda bahorim kelgay.", "Ne kun o'lg'ayki, nigorim kelgay,\n" +
                        "Bog'i umrumda bahorim kelgay.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Umr bog'ida bahor uldurkim,\n" +
                        "Sarvqad lolauzorim kelgay.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Il gado jonig'a o't tushkaykim,\n" +
                        "Olg'ali sham'i mazorim kelgay.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ko'yida itti ko'ngul, vah, qachon ul\n" +
                        "Masti devonashiorim kelgay.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Qani maykim, chu ichib mast o'lsam,\n" +
                        "Ko'kka tuz boqqali orim kelgay.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Ey Navoiy, tilamon huru pari,\n" +
                        "Shoyad ul bazmda yorim kelgay."
            )
        )

    }

}