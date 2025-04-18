package org.apache.commons.io;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.apache.commons.io.FileUtils.copyFile;

public class Main {
    public static void main(String[] args) {
        try {
            File src = new File("source.txt");
            File destNotExist = new File("dest_new.txt");
            File destExists = new File("dest_exist.txt");

            // 初始化文件
            Files.write(src.toPath(), "Source Content".getBytes());
            Files.write(destExists.toPath(), "Existing Content".getBytes());

            // 测试1：目标文件不存在
            copyFile(src, destNotExist);
            System.out.println("测试1（目标不存在）: " +
                    (destNotExist.exists() ? "文件创建成功" : "失败"));

            // 测试2：改为复制内容相等性验证
            File destContentChecka = new File("dest_exist.txt");
            copyFile(src, destContentChecka);
            boolean isContentMatcha = readFile(src).equals(readFile(destContentChecka));
            System.out.println("测试2（相等性验证）: " +
                    (isContentMatcha ? "两者文件内容相等" : "内容不一致"));

            // 测试3：复制内容是否正确
            String complexContent = "复杂内容\n第二行\n!@#$%^&";
            Files.write(src.toPath(), complexContent.getBytes());
            File destContentCheck = new File("dest_content.txt");
            copyFile(src, destContentCheck);

            String actualContent = Files.readString(destContentCheck.toPath());
            boolean isContentMatch = actualContent.equals("复杂内容\n第二行\n!@#$%^&");
            System.out.println("测试3（内容验证）: " +
                    (isContentMatch ? "内容复制正确" : "内容不正确"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
