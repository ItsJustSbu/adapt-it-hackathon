package com.example.httprequest

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HostCardEmulatorService : HostApduService() {

    val auth : FirebaseAuth = Firebase.auth

    companion object {
        val TAG = "Host Card Emulator"
        val STATUS_SUCCESS = "9000"
        val STATUS_FAILED = "6F00"
        val CLA_NOT_SUPPORTED = "6E00"
        val INS_NOT_SUPPORTED = "6D00"
        val AID = "A0000002471001"
        val SELECT_INS = "A4"
        val DEFAULT_CLA = "00"
        val MIN_APDU_LENGTH = 12
    }

//    once NFS tag has been recieved, the apdu (application protocol data unit) will be processed

    override fun processCommandApdu(commandApdu : ByteArray?, p1: Bundle?): ByteArray? {
        if (commandApdu == null) {
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }

        val hexCommandApdu = Utils.toHex(commandApdu)

        if (hexCommandApdu.length < MIN_APDU_LENGTH) {
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }

        if (hexCommandApdu.substring(0, 2) != DEFAULT_CLA) {
            return Utils.hexStringToByteArray(CLA_NOT_SUPPORTED)
        }

        if (hexCommandApdu.substring(2, 4) != SELECT_INS) {
            return Utils.hexStringToByteArray(INS_NOT_SUPPORTED)
        }

//    if the adpu string matches with the receiver it will send the to the reciever the user id
        if (hexCommandApdu.substring(10, 24) == AID)  {
            val user = auth.currentUser
            val Response = user?.uid
            val responseTohex = Response?.let { Utils.stringToHex(it) }
            return responseTohex?.let { Utils.hexStringToByteArray(it) }

        } else {
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }

    }
    override fun onDeactivated(reason : Int) {
        Log.d(TAG, "Deactivated: " + reason)
    }
}