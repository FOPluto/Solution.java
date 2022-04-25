package com.ysucode.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 编译运行代码时的工具类
 */
public class CommandUtil {

    /**
     * 借助这个方法让Java代码能够去执行一个具体的命令
     * 如 javac Test.java
     *
     * @param cmd        要执行的命令
     * @param stdoutFile 表示标准输出结果重定向到那个文件中  如果为null表示不需要重定向
     * @param stderrFile 表示标准错误结果重定向到哪个文件中
     * @throws IOException
     */
    public static int run(String cmd, String stdoutFile, String stderrFile) throws IOException, InterruptedException {
        //1.获取Runtime对象，Runtime对象是一个单例的
        Runtime runtime = Runtime.getRuntime();

        //2.通过Runtime中的 exec 方法来执行一个指令 相当于在命令行中执行cmd命令
        Process process = runtime.exec(cmd);

        //3.针对标准输出进行重定向
        if (stdoutFile != null) {
            InputStream stdoutFrom = process.getInputStream();
            OutputStream stdoutTo = new FileOutputStream(stdoutFile);

            int ch = -1;
            while ((ch = stdoutFrom.read()) != -1) {
                stdoutTo.write(ch);
            }

            stdoutFrom.close();
            stdoutTo.close();
        }

        //4.针对标准错误重定向
        if (stderrFile != null) {
            InputStream stderrFrom = process.getErrorStream();
            OutputStream stderrTo = new FileOutputStream(stderrFile);

            int ch = -1;
            while ((ch = stderrFrom.read()) != -1) {
                stderrTo.write(ch);
            }

            stderrFrom.close();
            stderrTo.close();
        }

        //5.为了确保子进程先执行完  就需要加上进程等待的逻辑
        //父进程会在这里阻塞，直到子进程执行结束，才继续往下执行
        int exitCode = process.waitFor();
        return exitCode;

    }

    /**
     * 把用户输入和测试的Java代码结合起来
     *
     * @param requestCode 用户输入的代码
     * @param testCode    测试用的主函数代码
     * @return 即将编译的代码
     */
    public static String mergeJava(String requestCode, String testCode) {
        //先找到requestCode末尾的 } ，并截取出前面的代码
        int pos = requestCode.lastIndexOf("}");
        if (pos == -1) {
            return null;
        }
        //2.把testCode拼接到后面再拼接上 } 即可
        return requestCode.substring(0, pos) + testCode + "}";
    }

    /**
     * 把用户输入和测试的C++代码结合起来
     *
     * @param requestCode 用户输入的代码
     * @param testCode    测试用的主函数代码
     * @return 即将编译的代码
     */
    public static String mergeC(String requestCode, String testCode) {
        //先找到开头的void截取前面的代码
        int pos = testCode.indexOf("int");
        if (pos == -1) {
            return null;
        }
        //2.把requestCode拼接到void main前面
        StringBuffer stringBuffer = new StringBuffer(testCode);
        stringBuffer.insert(pos, requestCode);
        return stringBuffer.toString();
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        run("javac", "d:/oj/stdout.txt", "d:/oj/stderr.txt");
//    }
}
