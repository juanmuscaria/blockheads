package com.juanmuscaria.blockheads.network;


import com.dd.plist.BinaryPropertyListParser;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.XMLPropertyListParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import static java.util.Arrays.copyOfRange;

public class BHHelper {
  public static boolean isCompressed(byte[] data) {
    return isCompressed(data, 0);
  }

  public static boolean isCompressed(byte[] data, int offset) {
    if (data.length < offset + 2) {
      return false;
    }
    return (data[offset] == (byte) 0x1F && data[1 + offset] == (byte) 0x8B);
  }

  public static boolean isProbablyCompressed(byte[] data) {
    if (data.length < 2) {
      return false;
    }

    for (int i = 0; i < data.length - 1; i++) {
      if (data[i] == (byte) 0x1F && data[i + 1] == (byte) 0x8B) {
        return true;
      }
    }

    return false;
  }

  public static boolean isBinaryPropertyList(byte[] data) {
    return isBinaryPropertyList(data, 0);
  }

  public static boolean isBinaryPropertyList(byte[] data, int offset) {
    if (data.length < offset + 8) {
      return false;
    }
    String magic = new String(copyOfRange(data, offset, offset + 8), StandardCharsets.US_ASCII);
    return magic.startsWith("bplist") && magic.length() >= 8 && Character.isDigit(magic.charAt(6)) && Character.isDigit(magic.charAt(7));
  }

  /**
   * Helper method to parse a plist.
   * Since mac servers always use bplist and the client and linux server uses xmlplist this method
   * auto-detects the type used
   */
  @SuppressWarnings("unchecked")
  public static <T extends NSObject> T parseProperty(byte[] data) throws PropertyListFormatException, ParserConfigurationException, ParseException, IOException, SAXException {
    if (BHHelper.isBinaryPropertyList(data)) {
      // Mac server, uses bplist
      return (T) BinaryPropertyListParser.parse(data);
    } else {
      // Linux server/client, uses xml
      return (T) XMLPropertyListParser.parse(data);
    }
  }

  public static String toJavaUuid(String bhUuid) {
    return STR."\{bhUuid.substring(0, 8)}-\{bhUuid.substring(8, 12)}-\{bhUuid.substring(12, 16)}-\{bhUuid.substring(16, 20)}-\{bhUuid.substring(20, 32)}";
  }

  public static String toBhUuid(String javaUuid) {
    return javaUuid.replace("-", "");
  }
}
