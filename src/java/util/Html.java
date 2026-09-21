/*
 * PBA 2 - Employee Management System
 *
 * Name: SHAFIQ ARIF BIN SHAMSUL ARIF
 * Registration Number: 01DIT24F1203
 *
 * Name: MUHAMMAD AMSYAR ADAM BIN SHARUDIN
 * Registration Number: 01DIT24F1224
 *
 * Name: MUHAMMAD ADAM DANIAL BIN MOHAMMAD RIDZUAN
 * Registration Number: 01DIT24F11995
 *
 * Class: DITP5B
 */
package util;

public final class Html {
    private Html() { }
    public static String escape(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}
