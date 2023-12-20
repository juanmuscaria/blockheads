package com.juanmuscaria.blockheads;

import com.sun.jna.Platform;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Helper class for loading native libraries.
 */
public class NativeHelper {

  public static void loadLibrary(String libraryName) {
    var libraryFileName = fileNameFor(libraryName);

    if (libraryFileName == null) {
      throw new UnsupportedOperationException(STR."Unsupported operating system or arch: \{System.getProperty("os.name")}-\{System.getProperty("os.arch")}");
    }

    try (var is = NativeHelper.class.getResourceAsStream(STR."/native/\{libraryFileName}")) {
      if (is == null) {
        throw new FileNotFoundException(STR."Embedded library file not found: \{libraryFileName}");
      }

      var tempFilePath = Files.createTempFile(null, libraryName);
      tempFilePath.toFile().deleteOnExit();

      Files.copy(is, tempFilePath, StandardCopyOption.REPLACE_EXISTING);

      System.load(tempFilePath.toString());
    } catch (IOException e) {
      throw new RuntimeException(STR."Error loading library: \{libraryName}", e);
    }
  }

  private static String fileNameFor(String libraryName) {
    String libraryFileName = System.getProperty(STR."native.overwrite.\{libraryName}", null);
    if (libraryFileName != null) {
      return libraryFileName;
    }

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
    } else if (Platform.isWindows()) {
      if (Platform.is64Bit()) {
        if (Platform.isIntel()) {
          libraryFileName = STR."\{libraryName}_amd64.dll";
        } else {
          // TODO: add native lib
          //libraryFileName = STR."\{libraryName}_aarch64.dll";
        }
      } else {
        // TODO: add native lib
        //libraryFileName = STR."\{libraryName}_x86.dll";
      }
    }
    return libraryFileName;
  }
}
