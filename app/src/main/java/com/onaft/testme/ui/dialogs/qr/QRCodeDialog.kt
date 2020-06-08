package com.onaft.testme.ui.dialogs.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.onaft.testme.R
import com.onaft.testme.utils.Utils

class QRCodeDialog : DialogFragment() {

    private lateinit var ivQR: ImageView
    private lateinit var btnDismiss: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_qr, container, false)
        bindViews(view)
        initUI()
        return view
    }
    private fun bindViews(view: View) {
        ivQR = view.findViewById(R.id.ivQr)
        btnDismiss = view.findViewById(R.id.btnDismiss)
    }
    private fun initUI() {
        encodeAsBitmap(Utils.generateLessonId(), imageView = ivQR)
        btnDismiss.setOnClickListener {
            dismiss()
        }
    }

    @Throws(WriterException::class)
    fun encodeAsBitmap(str: String?, imageView: ImageView) {
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix =
                multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            imageView.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}