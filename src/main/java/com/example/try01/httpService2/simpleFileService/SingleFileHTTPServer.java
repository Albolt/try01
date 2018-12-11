package com.example.try01.httpService2.simpleFileService;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleFileHTTPServer extends Thread {


    private byte[] content;
    private byte[] header;
    private int port = 80;

    private SingleFileHTTPServer(String data, String encoding, String MIMEType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, MIMEType, port);
    }

    public SingleFileHTTPServer(byte[] data, String encoding, String MIMEType, int port) throws UnsupportedEncodingException {
        this.content = data;
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n" +
                "Server: OneFile 1.0\r\n" +
                "Content-length: " + this.content.length + "\r\n" +
                "Content-type: " + MIMEType + "\r\n\r\n";
        this.header = header.getBytes("ASCII");
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Accepting connections on port " + server.getLocalPort());
            System.out.println("Data to be sent:");
            System.out.write(this.content);

            while (true) {
                Socket connection = null;
                try {
                    connection = server.accept();
                    OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                    InputStream in = new BufferedInputStream(connection.getInputStream());

                    StringBuffer request = new StringBuffer();
                    while (true) {
                        int c = in.read();
                        if (c == '\r' || c == '\n' || c == -1) {
                            break;
                        }
                        request.append((char) c);
                    }


                    //如果检测到是HTTP/1.0及以后的协议，按照规范，需要发送一个MIME首部
                    if (request.toString().indexOf("HTTP/") != -1) {
                        out.write(this.header);
                    }

                    out.write(this.content);
                    out.flush();

                } catch (IOException e) {

                } finally {
                    if (connection != null)
                        connection.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server. Port Occupied");
        }

    }

    public static void main(String[] args) {
        try {
            //如果请求结尾是html。。。
            String contentType = "text/plain";
            if (args[0].endsWith(".html") || args[0].endsWith("/htm")) {
                contentType = "text/html";
            }

            //命令行读取指定的文件，并以字符流赋给b
            InputStream in = new FileInputStream(args[0]);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            byte[] data = out.toByteArray();

            //设置端口号
            int port;
            try {
                port = Integer.parseInt(args[1]);
                if (port < 1 || port > 0xFFFF) {
                    port = 8088;
                }
            } catch (Exception e) {
                port = 80;
            }

            //设置编码格式
            String encoding = "ASCII";
            if (args.length > 2) {
                encoding = args[2];
            }

            //以线程
            Thread t = new SingleFileHTTPServer(data, encoding, contentType, port);
            t.start();


        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage:java SingleFileHTTPServer filename port encoding");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
