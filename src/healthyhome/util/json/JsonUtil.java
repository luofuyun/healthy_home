package healthyhome.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JsonUtil {
    private static JsonConfig config = null;

    public static JsonConfig getJsonConfig() {
        if (config == null) {
            initProcessor();
        }
        return config;
    }

    /**
     * 初始化工作，使用JSON-LIB转换带有DATE类型的对象需要额外的一些设置
     * 
     * @author wendy
     */
    public static void initProcessor() {
        config = new JsonConfig();
        // 处理日期时返回 yyyy-MM-dd HH:mm:ss 格式
        config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
        config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
        config.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor());
    }

    /**
     * 设置json日期到java日期的转换格式
     * 
     * @author zengjun
     */
    public static void formatDate() {
        // 设定日期转换格式
        JSONUtils.getMorpherRegistry().registerMorpher(
                new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" , "yyyy-MM-dd HH:mm" ,
                        "yyyy-MM-dd" }));
    }

    /**
     * 把json格式转化为对象
     * 
     * @param json Json格式字符串
     * @return json Json的Java格式对象
     */
    public static Object Json2Obj(String json) {
        return Json2Obj(json, null);
    }

    /**
     * 把json格式转化为Java对象
     * 
     * @param json JSON格式字符串
     * @param beanCls 调用者(类)
     * @return Json的Java格式对象
     */
    @SuppressWarnings( "unchecked" )
    public static <T> T Json2Obj(String json, Class<T> beanCls) {
        if (json == null || json.equals("")) {
            return null;
        }
        T jsonObj = null;
        formatDate();
        if (json.startsWith("{")) {
            if (beanCls != null) {
                jsonObj = (T) JSONObject.toBean(JSONObject.fromObject(json), beanCls);
            } else {
                jsonObj =
                        (T) JSONObject
                                .toBean(JSONObject.fromObject(json), JsonUtil.getJsonConfig());
            }
        }
        if (json.startsWith("[")) {
            jsonObj = (T) JSONArray.toArray(JSONArray.fromObject(json), JsonUtil.getJsonConfig());
        }
        return jsonObj;
    }

    @SuppressWarnings( "unchecked" )
    public static <T> List<T> json2Array(String json, Class<T> clazz) {
        if (json == null || json.equals("")) {
            return null;
        }
        List<T> jsonList = new ArrayList<T>();
        formatDate();
        if (json.startsWith("[")) {
            T obj = null;
            try {
                obj = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            jsonList = JSONArray.toList(JSONArray.fromObject(json), obj, JsonUtil.getJsonConfig());
        }
        return jsonList;
    }

    /**
     * 把消息列表转为json格式
     * 
     * @param msgList
     * @return
     * @author wendy
     */
    public static <T> String msg2Json(List<T> msgList) {
        formatDate();
        JSONArray json = JSONArray.fromObject(msgList, JsonUtil.getJsonConfig());
        return json.toString();
    }

    /**
     * @param msgList 消息列表
     * @param msgName 消息名
     * @return
     */
    public static <T> String getJsonString(boolean flag, List<T> msgList, String msgName) {
        // return json string
        StringBuffer json = new StringBuffer("{");
        json.append("\"success\":");
        json.append(flag);
        json.append(",");
        json.append("\"" + msgName + "\":");
        json.append(msg2Json(msgList));
        json.append("}");
        return json.toString();
    }

    public static String getJsonString(boolean isSuccess, String message) {
        StringBuffer buffer = new StringBuffer("{");
        buffer.append("\"success\":");
        buffer.append(isSuccess);
        buffer.append(",");
        buffer.append("\"message\":\"");
        buffer.append(message);
        buffer.append("\"}");
        return buffer.toString();
    }

    public static String getJsonString(boolean isSuccess, String messageName, String message) {
        StringBuffer buffer = new StringBuffer("{");
        buffer.append("\"success\":");
        buffer.append(isSuccess);
        buffer.append(",");
        buffer.append("\"" + messageName + "\":\"");
        buffer.append(message);
        buffer.append("\"}");
        return buffer.toString();
    }

    public static <E> String getJsonString(boolean isSuccess, E obj, String message) {
        StringBuffer buffer = new StringBuffer("{");
        buffer.append("\"success\":");
        buffer.append(isSuccess);
        buffer.append(",");
        buffer.append("\"data\":");
        buffer.append(getJsonObject(obj));
        buffer.append(",");
        buffer.append("\"message\":\"");
        buffer.append(message);
        buffer.append("\"}");
        return buffer.toString();
    }

    public static <T> String getJsonString(boolean isSuccess, String name, List<T> datas) {
        StringBuffer json = new StringBuffer("{");
        json.append("\"success\":");
        json.append(isSuccess);
        json.append(",");
        json.append("\"" + name + "\":");
        JSONArray jsonArray = JSONArray.fromObject(datas, getJsonConfig());
        json.append(jsonArray.toString());
        json.append("}");
        return json.toString();
    }

    /**
     * 对象json格式
     * 
     * @param object
     * @return
     * @author wendy
     */
    public static String getJsonObject(Object object) {
        formatDate();
        JSONObject json = JSONObject.fromObject(object, JsonUtil.getJsonConfig());
        return json.toString();
    }

    /**
     * 列表json格式
     * 
     * @param list
     * @return
     * @author wendy
     */
    public static <T> String getJsonArray(List<T> list) {
        formatDate();
        JSONArray json = JSONArray.fromObject(list, JsonUtil.getJsonConfig());
        return json.toString();
    }

    /**
     * json to map
     * 
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }
}
