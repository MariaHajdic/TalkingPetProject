package com.example.talkingpetproject

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import java.nio.ByteBuffer

@Composable
fun AudioRecorder() {
    val isRecording = remember { mutableStateOf(false) }
    val audioData = remember { mutableStateListOf<Byte>() }
    val sampleRate = 44100
    val bufferSize = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)

    val audioRecord = remember {
        AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )
    }

    LaunchedEffect(isRecording.value) {
        if (isRecording.value) {
            val buffer = ByteBuffer.allocateDirect(bufferSize)
            audioRecord.startRecording()

            while (isRecording.value) {
                buffer.clear()
                val read = audioRecord.read(buffer, bufferSize)
                if (read > 0) {
                    audioData.addAll(buffer.array().take(read))
                }
            }

            audioRecord.stop()
        }
    }

    Column {
        Button(onClick = { isRecording.value = !isRecording.value }) {
            Text(if (isRecording.value) "Stop recording" else "Start recording")
        }
        Text("Recording: ${if (isRecording.value) "Yes" else "No"}")
        Text("Data size: ${audioData.size}")
    }
}
