package org.apache.commons.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsCopyTest {
    private File src;
    private File destNotExist;
    private File destExists;
    private static final String TEST_DIR = "";

    @BeforeEach
    public void setUp() throws IOException {
        Files.createDirectories(Path.of(TEST_DIR));
        src = new File(TEST_DIR + "source.txt");
        destNotExist = new File(TEST_DIR + "dest_new.txt");
        destExists = new File(TEST_DIR + "dest_exist.txt");

        // 初始化文件
        Files.write(src.toPath(), "Source Content".getBytes());
        Files.write(destExists.toPath(), "Existing Content".getBytes());
    }

    @Test
    public void testCopyToNonExistingFile() throws IOException {
        // 测试1：目标文件不存在
        FileUtils.copyFile(src, destNotExist);
        assertTrue(destNotExist.exists(), "文件应被成功创建");
        assertEquals(readFile(src), readFile(destNotExist));
    }

    @Test
    public void testCopyOverwriteExistingFile() throws IOException {
        // 测试2：覆盖已存在文件
        FileUtils.copyFile(src, destExists);
        assertEquals(readFile(src), readFile(destExists), "Existing Content");
    }

    @Test
    public void testCopyComplexContent() throws IOException {
        // 测试3：复杂内容验证
        String complexContent = "复杂内容\n第二行\n!@#$%^&";
        Files.write(src.toPath(), complexContent.getBytes());
        File destContentCheck = new File(TEST_DIR + "dest_content.txt");

        FileUtils.copyFile(src, destContentCheck);
        String actualContent = Files.readString(destContentCheck.toPath());

        assertEquals(complexContent, actualContent, "复杂内容应被正确复制");
    }

    private String readFile(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
