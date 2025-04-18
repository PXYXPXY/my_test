package org.apache.commons.io;

import org.junit.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

import static org.junit.Assert.*;

public class FileUtilsCopyTest2 {

    @BeforeClass
    public static void createTestDir() throws IOException {
    }

    @Before
    public void setUp() throws IOException {
    }

    @Test
    public void testCopyPreservesLastModifiedTime() throws IOException {
    }
}
