/**
 * 
 */
package healthyhome.util.json;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 *
 * @author zengjun
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private DateFormat dateFormat;

    public DateJsonValueProcessor( ) {
        this(DEFAULT_DATE_PATTERN);
    }

    public DateJsonValueProcessor(String datePattern) {
        try {
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception ex) {
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
    }

    /**
     * 
     * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object,
     *      net.sf.json.JsonConfig)
     * @author zengjun
     */
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    /**
     * 
     * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String,
     *      java.lang.Object, net.sf.json.JsonConfig)
     * @author zengjun
     */
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Timestamp) {
            return dateFormat.format((Timestamp) value);
        } else {
            return dateFormat.format((Date) value);
        }
    }

}
