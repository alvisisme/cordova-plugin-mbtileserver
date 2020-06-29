package com.plugin.mbtileserver;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fi.iki.elonen.NanoHTTPD;

public class WebServer extends NanoHTTPD {
    private String mFilePath = null;
    private SQLiteDatabase mDatabase = null;

    public WebServer(int port, String filePath) {
        super(port);
        this.mFilePath = filePath;   
    }

    @Override
    public void start() throws IOException {
        this.mDatabase = SQLiteDatabase.openDatabase(this.mFilePath, null, SQLiteDatabase.OPEN_READONLY, null);
        super.start();
    }

    @Override
    public void stop() {
        if (this.mDatabase != null) {
            this.mDatabase.close();
            this.mDatabase = null;
        }
        super.stop();
    }

    @Override
    public Response serve(IHTTPSession session) {
        // uri = /favicon.ico
        // uri = /1/1/1.png
        // uri = /1/1/1
        String uri = session.getUri();
        Response res;
        String[] params =  uri.split("/");
        if (params.length != 4) {
            res = newFixedLengthResponse(Response.Status.BAD_REQUEST, NanoHTTPD.MIME_PLAINTEXT, "Bad request");
            res.addHeader("Access-Control-Allow-Origin", "*");
            return res;
        }
        String format = "png";
        String z = params[1];
        String x = params[2];
        String y = params[3];
        String[] imageStrArray = y.split("\\.");
        if (imageStrArray.length > 1) {
            y = imageStrArray[0];
            format = imageStrArray[1];
        }
        String[] selections = {
                z,
                x,
                y
        };
        Cursor cursor = this.mDatabase.rawQuery("SELECT tile_data FROM tiles WHERE zoom_level = ? AND tile_column = ? AND tile_row = ?", selections);
        boolean found = false;
        byte[] image = null;
        while(cursor.moveToNext()) {
            found = true;
            image = cursor.getBlob(cursor.getColumnIndex("tile_data"));
        }
        cursor.close();
        if (found) {
            res = newFixedLengthResponse(Response.Status.OK, "image/" + format, new ByteArrayInputStream(image) ,image.length);
            res.addHeader("Access-Control-Allow-Origin", "*");
            return res;
        }
        res = newFixedLengthResponse(Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "Not Found");
        res.addHeader("Access-Control-Allow-Origin", "*");
        return res;
    }
}
