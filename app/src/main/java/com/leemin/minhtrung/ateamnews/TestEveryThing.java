package com.leemin.minhtrung.ateamnews;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class TestEveryThing extends AppCompatActivity {
    private Document htmlDocument;
    private String htmlContentInStringFormat;
    private TextView parsedHtmlNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_every_thing);

        //Test hiển thị nội dung trang web
        /*WebView webView = (WebView) findViewById(R.id.webView );
        Intent intent = getIntent();
        String url = intent.getStringExtra("noiDung");
        webView.loadData(url, "text/html; charset=UTF-8", null);
        webView.setWebViewClient(new WebViewClient());*/
        parsedHtmlNode = (TextView) findViewById(R.id.html_content);

        String htmlFilename = "filename.html";
        AssetManager mgr = getBaseContext().getAssets();
        try {
            InputStream in = mgr.open(htmlFilename, AssetManager.ACCESS_BUFFER);
            htmlContentInStringFormat = StreamToString(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button htmlTitleButton = (Button) findViewById(R.id.button);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (htmlContentInStringFormat.equals("")) {
                    Toast.makeText(TestEveryThing.this, "There is no HTML file to parse", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    htmlDocument = Jsoup.parse(htmlContentInStringFormat);
                    //String pageTitle = htmlDocument.title();
                    Elements title = htmlDocument.select("div[class=text-conent]");
                    Elements p = title.select("p");
                   // parsedHtmlNode.setText(p.html());
                    WebView webView = (WebView) findViewById(R.id.webViewTest );
                    String html = "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "\t<head>\n" +
                            "\t\t<meta charset=\"UTF-8\">\n" +
                            "\t\t<title>Document</title>\n" +
                            "\t\t<style type=\"text/css\">\n" +
                            "\t\t\t\n" +
                            "\t\t\t.baiviet-title {\n" +
                            "\t\tcolor: #5ca038;\n" +
                            "\t\tfont-size: 16px;\n" +
                            "\t\tfont-weight: bold;\n" +
                            "\t\tmargin: 0;\n" +
                            "\t\tpadding-bottom: 5px;\n" +
                            "\t\t}\n" +
                            "\t\t.baiviet-sapo {\n" +
                            "\t\tfont-weight: bold;\n" +
                            "\t\ttext-align: justify;\n" +
                            "\t\tpadding-right: 10px;\n" +
                            "\t\tmargin: 0;\n" +
                            "\t\t}\n" +
                            "\t\t.content .colCenter p {\n" +
                            "\t\tfont-size: 13px;\n" +
                            "\t\t}\n" +
                            "\t\tp {\n" +
                            "\t\tline-height: 1.4;\n" +
                            "\t\t}\n" +
                            "\t\t</style>\n" +
                            "\t</head>\n" +
                            "\t<body>"+
                            title.html()
                            +"</body>\n" +
                            "</html>";
                    webView.loadData(html, "text/html; charset=UTF-8", null);
                    webView.setWebViewClient(new WebViewClient());
//                    for(Element el :p){
//                        parsedHtmlNode.setText(el.html());
//                    }
//                    String pageTitle = title.text();
//                    if (pageTitle != null) {
//                        parsedHtmlNode.setText(pageTitle);
//                    }
                }
            }
        });
    }

    public static String StreamToString(InputStream in) throws IOException {
        if (in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
        }
        return writer.toString();
    }
}
