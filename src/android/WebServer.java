package com.plugin.mbtileserver;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.File;

import fi.iki.elonen.NanoHTTPD;

public class WebServer extends NanoHTTPD {
    private String mFilePath = null;

    public WebServer(int port, String filePath) {
        super(port);
        this.mFilePath = filePath;   
    }

    @Override
    public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();
        Response res;
        // uri = /favicon.ico
        // uri = /1/1/1.png
        String[] params =  uri.split("/");
        if (params.length < 4) {
            res = newFixedLengthResponse(Response.Status.BAD_REQUEST, NanoHTTPD.MIME_PLAINTEXT, "Bad request");
            res.addHeader("Access-Control-Allow-Origin", "*");
            return res;
        }
        String z = params[1];
        String x = params[2];
        String y = params[3].split("\\.")[0];
        String path = this.mFilePath;
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(path, null);
        String[] selections = {
                z,
                x,
                y
        };
        Cursor cursor = database.rawQuery("SELECT tile_data FROM tiles WHERE zoom_level = ? AND tile_column = ? AND tile_row = ?", selections);
        boolean found = false;
        byte[] image = null;
        while(cursor.moveToNext()) {
            found = true;
            image = cursor.getBlob(cursor.getColumnIndex("tile_data"));
        }
        cursor.close();
        if (found) {
            res = newFixedLengthResponse(Response.Status.OK, "image/png", new ByteArrayInputStream(image) ,image.length);
            res.addHeader("Access-Control-Allow-Origin", "*");
            return res;
        }
        res = newFixedLengthResponse(Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "Not Found");
        res.addHeader("Access-Control-Allow-Origin", "*");
        return res;
    }
}
