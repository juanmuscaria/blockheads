package com.juanmuscaria.blockheads;

import com.sun.jna.Platform;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class NativeHelper {

  public static void loadLibrary(String libraryName) {
    String libraryFileName = null;

    if (Platform.isLinux()) {
      if (Platform.is64Bit() && Platform.isIntel()) {
        libraryFileName = STR."\{libraryName}_amd64.so";
      }
    } else if (Platform.isMac()) {
      if (Platform.is64Bit()) {
        if (Platform.isIntel()) {
          libraryFileName = STR."\{libraryName}_amd64.dylib";
        } else if (Platform.isARM()) {
          libraryFileName = STR."\{libraryName}_aarch64.dylib";
        }
      }
    }

    if (libraryFileName == null) {
      throw new UnsupportedOperationException(STR."Unsupported operating system or arch: \{System.getProperty("os.name")}-\{System.getProperty("os.arch")}");
    }

    try (InputStream is = NativeHelper.class.getResourceAsStream(STR."/\{libraryFileName}")) {
      if (is == null) {
        throw new IOException(STR."Library file not found: \{libraryFileName}");
      }
      Path tempFilePath = Files.createTempFile(libraryName, null);
      tempFilePath.toFile().deleteOnExit();

      Files.copy(is, tempFilePath, StandardCopyOption.REPLACE_EXISTING);

      System.load(tempFilePath.toString());
    } catch (IOException e) {
      throw new RuntimeException(STR."Error loading library: \{libraryName}", e);
    }
  }
}
