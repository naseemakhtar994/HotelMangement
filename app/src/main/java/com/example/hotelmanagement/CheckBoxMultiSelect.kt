package com.example.hotelmanagement

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.google.android.gms.common.util.CollectionUtils.listOf
import java.util.*


class  CheckBoxMultiSelect {

    fun showDiaog(activity: CompareActivity){

        val indices = intArrayOf();
        val aminities = listOf("Chargeable", "CCTV", "ExtraBed", "Chargeable", "Free Lunch", "Onsite parking", "Wedding halls", "Conference Hall", "Dining", "Air conditioning", "Amazon Lockers", "Family rooms Lift access", "Free Wi-Fi", "Accessible")

//        for (i in activity.selectedAminities.indices) {
//
//            if (aminities.indexOf(activity.selectedAminities.get(i).toString())){
//
//            }
//        }



        MaterialDialog(activity).show {
            listItemsMultiChoice(items = aminities ) { _, index, text ->
                // Invoked when the user selects item(s)
                activity.getFilterAMinities(text as ArrayList<*>);
            }
            positiveButton(R.string.Choose)

        }
    }
}

