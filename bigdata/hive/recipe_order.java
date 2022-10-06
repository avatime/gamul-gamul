// ORM class for table 'recipe_order'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Sep 28 10:56:18 KST 2022
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class recipe_order extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        recipe_order.this.id = (Long)value;
      }
    });
    setters.put("desc_order", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        recipe_order.this.desc_order = (Integer)value;
      }
    });
    setters.put("description", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        recipe_order.this.description = (String)value;
      }
    });
    setters.put("recipe_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        recipe_order.this.recipe_id = (Long)value;
      }
    });
    setters.put("image_path", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        recipe_order.this.image_path = (String)value;
      }
    });
  }
  public recipe_order() {
    init0();
  }
  private Long id;
  public Long get_id() {
    return id;
  }
  public void set_id(Long id) {
    this.id = id;
  }
  public recipe_order with_id(Long id) {
    this.id = id;
    return this;
  }
  private Integer desc_order;
  public Integer get_desc_order() {
    return desc_order;
  }
  public void set_desc_order(Integer desc_order) {
    this.desc_order = desc_order;
  }
  public recipe_order with_desc_order(Integer desc_order) {
    this.desc_order = desc_order;
    return this;
  }
  private String description;
  public String get_description() {
    return description;
  }
  public void set_description(String description) {
    this.description = description;
  }
  public recipe_order with_description(String description) {
    this.description = description;
    return this;
  }
  private Long recipe_id;
  public Long get_recipe_id() {
    return recipe_id;
  }
  public void set_recipe_id(Long recipe_id) {
    this.recipe_id = recipe_id;
  }
  public recipe_order with_recipe_id(Long recipe_id) {
    this.recipe_id = recipe_id;
    return this;
  }
  private String image_path;
  public String get_image_path() {
    return image_path;
  }
  public void set_image_path(String image_path) {
    this.image_path = image_path;
  }
  public recipe_order with_image_path(String image_path) {
    this.image_path = image_path;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof recipe_order)) {
      return false;
    }
    recipe_order that = (recipe_order) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.desc_order == null ? that.desc_order == null : this.desc_order.equals(that.desc_order));
    equal = equal && (this.description == null ? that.description == null : this.description.equals(that.description));
    equal = equal && (this.recipe_id == null ? that.recipe_id == null : this.recipe_id.equals(that.recipe_id));
    equal = equal && (this.image_path == null ? that.image_path == null : this.image_path.equals(that.image_path));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof recipe_order)) {
      return false;
    }
    recipe_order that = (recipe_order) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.desc_order == null ? that.desc_order == null : this.desc_order.equals(that.desc_order));
    equal = equal && (this.description == null ? that.description == null : this.description.equals(that.description));
    equal = equal && (this.recipe_id == null ? that.recipe_id == null : this.recipe_id.equals(that.recipe_id));
    equal = equal && (this.image_path == null ? that.image_path == null : this.image_path.equals(that.image_path));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id = JdbcWritableBridge.readLong(1, __dbResults);
    this.desc_order = JdbcWritableBridge.readInteger(2, __dbResults);
    this.description = JdbcWritableBridge.readString(3, __dbResults);
    this.recipe_id = JdbcWritableBridge.readLong(4, __dbResults);
    this.image_path = JdbcWritableBridge.readString(5, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id = JdbcWritableBridge.readLong(1, __dbResults);
    this.desc_order = JdbcWritableBridge.readInteger(2, __dbResults);
    this.description = JdbcWritableBridge.readString(3, __dbResults);
    this.recipe_id = JdbcWritableBridge.readLong(4, __dbResults);
    this.image_path = JdbcWritableBridge.readString(5, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(desc_order, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(description, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(recipe_id, 4 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(image_path, 5 + __off, 12, __dbStmt);
    return 5;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(desc_order, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(description, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(recipe_id, 4 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(image_path, 5 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.desc_order = null;
    } else {
    this.desc_order = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.description = null;
    } else {
    this.description = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.recipe_id = null;
    } else {
    this.recipe_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.image_path = null;
    } else {
    this.image_path = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.desc_order) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.desc_order);
    }
    if (null == this.description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, description);
    }
    if (null == this.recipe_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.recipe_id);
    }
    if (null == this.image_path) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, image_path);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.desc_order) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.desc_order);
    }
    if (null == this.description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, description);
    }
    if (null == this.recipe_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.recipe_id);
    }
    if (null == this.image_path) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, image_path);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(desc_order==null?"null":"" + desc_order, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(description==null?"null":description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(recipe_id==null?"null":"" + recipe_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(image_path==null?"null":image_path, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(desc_order==null?"null":"" + desc_order, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(description==null?"null":description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(recipe_id==null?"null":"" + recipe_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(image_path==null?"null":image_path, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.desc_order = null; } else {
      this.desc_order = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.description = null; } else {
      this.description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.recipe_id = null; } else {
      this.recipe_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.image_path = null; } else {
      this.image_path = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.desc_order = null; } else {
      this.desc_order = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.description = null; } else {
      this.description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.recipe_id = null; } else {
      this.recipe_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.image_path = null; } else {
      this.image_path = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    recipe_order o = (recipe_order) super.clone();
    return o;
  }

  public void clone0(recipe_order o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("desc_order", this.desc_order);
    __sqoop$field_map.put("description", this.description);
    __sqoop$field_map.put("recipe_id", this.recipe_id);
    __sqoop$field_map.put("image_path", this.image_path);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("desc_order", this.desc_order);
    __sqoop$field_map.put("description", this.description);
    __sqoop$field_map.put("recipe_id", this.recipe_id);
    __sqoop$field_map.put("image_path", this.image_path);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
