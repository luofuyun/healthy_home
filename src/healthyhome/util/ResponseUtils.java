package healthyhome.util;

import healthyhome.util.json.JsonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跟servlet的响应对象对应的一些工具类
 * 
 */
public class ResponseUtils {
    
    private static final int DEFAULT_COOKIES_EXPIRE_TIME = 600;
    /**
     * servlet输出
     * 
     * @param response
     * @param result
     * @throws IOException
     */
    public static void print(HttpServletResponse response, String result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
     * 输出plain文本
     * 
     * @param response
     * @param result
     * @throws IOException
     */
    public static void write(HttpServletResponse response, String result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
     * 输出plain文本
     * 
     * @param response
     * @param object
     * @throws IOException
     */
    public static void write(HttpServletResponse response, Object object) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.getJsonObject(object));
        out.flush();
        out.close();
    }

    public static <T> void printArrayList(HttpServletResponse response, List<T> list)
            throws IOException {
        print(response, JsonUtil.getJsonArray(list));
    }

    public static void printObjToJson(HttpServletResponse response, Object object)
            throws IOException {
        print(response, JsonUtil.getJsonObject(object));
    }

    /*public static <E> void print(HttpServletResponse response, PageInfo<E> page) throws IOException {
        GridData<E> data = new GridData<E>();
        data.setPage(page.getCurrentPage());
        data.setRecords(page.getTotal());
        data.setRows(page.getRecords());
        data.setTotal(page.getTotalPage());
        printObjToJson(response, data);
    }*/

    public static <T> void printPageList(HttpServletResponse response, List<T> list)
            throws IOException {
        GridData<T> data = new GridData<T>();
        data.setPage(0);
        data.setRecords(list.size());
        data.setRows(list);
        data.setTotal(list.size());
        printObjToJson(response, data);
    }


    public static void writeExcel(HttpServletResponse response, String filepath, String filename) {
        String contentType = "application/vnd.ms-excel;charset=gb2312";
        writeFile(response, filepath, filename, contentType);
    }

    public static void writeZip(HttpServletResponse response, String filePath) {
        String contentType = "application/x-zip-compressed;charset=gb2312";
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        writeFile(response, filePath, fileName, contentType);
    }

    public static void writeZip(HttpServletResponse response, String filePath, String fileName) {
        String contentType = "application/x-zip-compressed;charset=gb2312";
        writeFile(response, filePath, fileName, contentType);
    }

    /**
     * 输出文件
     * 
     * @param response
     * @param filepath
     * @param filename
     * @param contentType
     */
    public static void writeFile(HttpServletResponse response, String filepath, String filename,
            String contentType) {
        try {
            InputStream in = new FileInputStream(filepath);
            writeFile(response, in, filename, contentType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出文件
     * 
     * @param response
     * @param in
     * @param filename
     * @param contentType
     */
    public static void writeFile(HttpServletResponse response, InputStream in, String filename,
            String contentType) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType(contentType);
            filename = new String(filename.getBytes("gbk"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);

            int n = 0;
            byte b[] = new byte[1024];
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public static void setCookie(HttpServletResponse response, String key, Object value) {
        setCookieWithTimeOut(response, key, value, DEFAULT_COOKIES_EXPIRE_TIME);
    }

    /**
     * 设置cookie并设置其过期时间
     * 
     * @param response
     * @param key cookie key
     * @param value cookie 内容
     * @param timeout cookie 过期时间
     */
    public static void setCookieWithTimeOut(HttpServletResponse response, String key, Object value,
            int timeout) {
        Cookie cookie = new Cookie(key, value.toString());
        cookie.setMaxAge(timeout);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 根据key取得相应的cookie的值
     * 
     * @param request
     * @param key 要取得的cookie值的key
     * @return 对应key的cookie值，或null
     */
    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie cookie = getCookie(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    public static void clearCookie(HttpServletResponse response, Cookie cookie) {
        cookie.setMaxAge(0);
        cookie.setValue(null);
        response.addCookie(cookie);
    }

    public static String getCookieAndClear(HttpServletRequest request,
            HttpServletResponse response, String key) {
        Cookie cookie = getCookie(request, key);
        if (cookie != null) {
            String value = cookie.getValue();
            clearCookie(response, cookie);
            return value;
        }
        return null;
    }

    public static void clearValidate(HttpServletResponse response, String key) {
        setCookie(response, key, 0);
    }

}
