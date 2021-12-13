package com.example.mystudypal;

import android.content.Context;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class FileUtils {
    public static String readFile(Context context, String filename) throws FileNotFoundException{
        return FileUtils.readStream(context.openFileInput(filename));
    }

    public static String readStream(InputStream inputStream) {
        String contents="";
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Err occurred when opening raw file for reading
        }
        return stringBuilder.toString().trim();
    }

    public static void writeFile(Context context, String filename, String data) {
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static String readFromAssets(String name, Context context) throws IOException {
        InputStream inputStream = context.getAssets().open(name);
        return FileUtils.readStream(inputStream);
    }
}
