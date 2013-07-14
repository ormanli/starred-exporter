package com.starredexporter.exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Stores items exported from JSON and prints to a HTML file. Singleton
 * @author ormanli
 */
public class HTMLStash {

    private static HTMLStash ourInstance = new HTMLStash();
    private StringBuffer output = new StringBuffer();
    private boolean isPrinted = false;

    /**
     * 
     * @return Instance of HTMLStash
     */
    public static HTMLStash getInstance() {
        return ourInstance;
    }

    private HTMLStash() {
    }

    /**
     * Gets string and stores in stash.
     * @param input String to store
     */
    public synchronized void put(String input) {
        output.append(input);
    }

    /**
     * Prints stored elements to HTML file.
     * @throws IOException
     */
    public void printOutput() throws IOException {
        if (!isPrinted) {
            isPrinted = false;
            StringBuffer result = new StringBuffer();
            
            result.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                    + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<head>\n"
                    + "\t<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n"
                    + "\t<title></title>\n"
                    + "<style type=\"text/css\">\n"
                    + "html, body, div, span, applet, object, iframe,\n"
                    + "h1, h2, h3, h4, h5, h6, p, blockquote, pre,\n"
                    + "a, abbr, acronym, address, big, cite, code,\n"
                    + "del, dfn, em, font, img, ins, kbd, q, s, samp,\n"
                    + "small, strike, strong, sub, sup, tt, var,\n"
                    + "b, u, i, center,\n"
                    + "dl, dt, dd, ol, ul, li,\n"
                    + "fieldset, form, label, legend,\n"
                    + "table, caption, tbody, tfoot, thead, tr, th, td {\n"
                    + "\tmargin: 0;\n"
                    + "\tpadding: 0;\n"
                    + "\tborder: 0;\n"
                    + "\toutline: 0;\n"
                    + "\tfont-size: 100%;\n"
                    + "\tvertical-align: baseline;\n"
                    + "\tbackground: transparent;\n"
                    + "}\n"
                    + "ol, ul {\n"
                    + "\tlist-style: none;\n"
                    + "margin-top: 1em;\n"
                    + "}\n"
                    + "blockquote, q {\n"
                    + "\tquotes: none;\n"
                    + "}\n"
                    + ":focus {\n"
                    + "\toutline: 0;\n"
                    + "}\n"
                    + "ins {\n"
                    + "\ttext-decoration: none;\n"
                    + "}\n"
                    + "del {\n"
                    + "\ttext-decoration: line-through;\n"
                    + "}\n"
                    + "table {\n"
                    + "\tborder-collapse: collapse;\n"
                    + "\tborder-spacing: 0;\n"
                    + "}\n"
                    + "body {\n"
                    + "\tfont: 12px/18px Arial, Tahoma, Verdana, sans-serif;\n"
                    + "\twidth: 100%;\n"
                    + "}\n"
                    + "a {\n"
                    + "\tcolor: blue;\n"
                    + "\toutline: none;\n"
                    + "\ttext-decoration: underline;\n"
                    + "}\n"
                    + "a:hover {\n"
                    + "\ttext-decoration: none;\n"
                    + "}\n"
                    + "p {\n"
                    + "\tmargin: 0 0 18px\n"
                    + "}\n"
                    + "img {\n"
                    + "\tborder: none;\n"
                    + "}\n"
                    + "input {\n"
                    + "\tvertical-align: middle;\n"
                    + "}\n"
                    + "#wrapper {\n"
                    + "\twidth: 1000px;\n"
                    + "\tmargin: 0 auto;\n"
                    + "}\n"
                    + "#header {\n"
                    + "\theight: 150px;\n"
                    + "\tbackground: #FFE680;\n"
                    + "}\n"
                    + "#content {\n"
                    + "}\n"
                    + "#footer {\n"
                    + "\theight: 100px;\n"
                    + "\tbackground: #BFF08E;\n"
                    + "}\n"
                    + "\n"
                    + "</style>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "\n"
                    + "<div id=\"wrapper\">\n"
                    + "\n"
                    + "\t\n"
                    + "\n"
                    + "\t<div id=\"content\">\n"
                    + "<ul>");

            result.append(output);

            result.append("</div><!-- #content-->\n"
                    + "\n"
                    + "\n"
                    + "</div><!-- #wrapper -->\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");


            FileWriter fstream = new FileWriter("starred.html");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(result.toString());
            out.flush();
            out.close();
            System.out.println("Finished.");
        }
    }
}
