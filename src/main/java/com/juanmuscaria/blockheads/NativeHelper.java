package com.juanmuscaria.blockheads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class NativeHelper {

  public static void loadLibrary(String libraryName) {
    String os = System.getProperty("os.name").toLowerCase();
    String libraryFileName;

    if (os.contains("win")) {
      libraryFileName = STR."\{libraryName}.dll";
    } else if (os.contains("nix") || os.contains("nux")) {
      libraryFileName = STR."\{libraryName}" + ".so";
    } else {
      throw new UnsupportedOperationException(STR."Unsupported operating system: \{os}");
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
